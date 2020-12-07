// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 
//OVERRIDEN
    public int Allocate(int blockSize) {
        /*Dictionary loc=this.freeBlk.Find(blockSize, true);
        if(loc!=null){
            this.allocBlk.Insert(loc.address, loc.size, loc.address);
            this.freeBlk.Delete(loc);
            return loc.address;
        }
        else{
            loc=this.freeBlk.Find(blockSize, false);
            if(loc!=null){
                this.allocBlk.Insert(loc.address, blockSize, loc.address);
                this.freeBlk.Insert(loc.address+blockSize, loc.size-blockSize, loc.size-blockSize);
                this.freeBlk.Delete(loc);
                return loc.address;
            }
        }*/
        // System.out.println(this.freeBlk.sanity());
        // System.out.println(this.allocBlk.sanity());
        //System.out.println("ALLOCATING");
        if(blockSize<=0){
            return -1;
        }
        Dictionary loc=this.freeBlk.Find(blockSize, false);
        
        if(loc!=null){
            //System.out.println("Found this "+loc.address+" key "+loc.key);
            if(loc.size==blockSize){
                this.allocBlk.Insert(loc.address, loc.size, loc.address);
                int loc_address=loc.address;
                this.freeBlk.Delete(loc);
                return loc_address;
            }
            this.allocBlk.Insert(loc.address, blockSize, loc.address);
            this.freeBlk.Insert(loc.address+blockSize, loc.size-blockSize, loc.size-blockSize);
            int loc_address=loc.address;//CHNAGE HERE
            this.freeBlk.Delete(loc);
            return loc_address;//CHANGE HERE
//bug here ask for change            
        }
    
        return -1;
    } 
    public void Defragment() {
        // System.out.println(this.freeBlk.sanity());
        // System.out.println(this.allocBlk.sanity());
        Dictionary tempTree;
        if(this.type==2){
            tempTree=new BSTree();
        }
        else{
            tempTree=new AVLTree();
        }
        Dictionary orig=this.freeBlk;
        //System.out.println("5555555555555");
        if(orig.getFirst()==null){//when tree is empty
            return;
        }
        //System.out.println("222222222222222222");
        for (Dictionary d = orig.getFirst(); d != null; d = d.getNext()){
            tempTree.Insert(d.address, d.size, d.address);
        }
        Dictionary d=tempTree.getFirst();
        while (d!=null&&d.getNext()!=null){
            if(d.address+d.size==d.getNext().address){//can there be overlapping address?    
                //System.out.println("aaaaaaaaaaa" +d.address+" "+d.size);
                Dictionary todelt1=new BSTree(d.address,d.size,d.address);
                Dictionary todelt2=new BSTree(d.getNext().address,d.getNext().size,d.getNext().address);
                Dictionary todelo1=new BSTree(d.address,d.size,d.size);
                Dictionary todelo2=new BSTree(d.getNext().address,d.getNext().size,d.getNext().size);
                int dadd=d.address;
                int dsize=d.size;
                int nadd=d.getNext().address;
                int nsize=d.getNext().size;
                orig.Delete(todelo1);
                orig.Delete(todelo2);
                orig.Insert(dadd,dsize+nsize,dsize+nsize);
                tempTree.Delete(todelt1);
                tempTree.Delete(todelt2);
                
                d=tempTree.Insert(dadd,dsize+nsize,dadd);
                //System.out.println("This is after " +d.address+" "+d.size);
                if(d.getNext()!=null)
                //System.out.println("This is after next " +d.getNext().address+" "+d.getNext().size);
                continue;
                
                
            }
            d=d.getNext();
        }
        tempTree=null;
        
        return ;
    }
}