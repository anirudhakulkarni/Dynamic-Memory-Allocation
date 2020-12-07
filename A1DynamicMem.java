// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }
    // uncommment this to print contents of the lists
    public void printBlk(){
        System.out.print("\nfreeBlk is : ");
        Dictionary first = this.freeBlk.getFirst();
        while(first!=null){
          System.out.print(" ("+first.address+", "+first.size+") ");
          if(this.freeBlk==first) System.out.print(" <-- ");
          first = first.getNext();
        }
  
        System.out.print("\nallocBlk is : ");
        first = this.allocBlk.getFirst();
        while(first!=null){
          System.out.print(" ("+first.address+", "+first.size+") ");
          if(this.allocBlk==first) System.out.print(" <-- ");
          first = first.getNext();
        }
        System.out.print("\n");
      }



    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).

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
        //System.out.println(this.freeBlk.sanity());
        //System.out.println(this.allocBlk.sanity());
        if(blockSize<=0){
            return -1;
        }
        Dictionary loc=this.freeBlk.Find(blockSize, false);
        if(loc!=null){
            if(loc.size==blockSize){
                this.allocBlk.Insert(loc.address, loc.size, loc.address);
                int loc_address=loc.address;
                this.freeBlk.Delete(loc);
                return loc_address;
            }
            this.allocBlk.Insert(loc.address, blockSize, loc.address);
            this.freeBlk.Insert(loc.address+blockSize, loc.size-blockSize, loc.size-blockSize);
            this.freeBlk.Delete(loc);
            return loc.address;
            
        }
    
        return -1;
    } 
    
    public int Free(int startAddr) {
        // System.out.println(this.freeBlk.sanity());
        // System.out.println(this.allocBlk.sanity());
        Dictionary loc=this.allocBlk.Find(startAddr, true);
        if(loc!=null){
            this.freeBlk.Insert(loc.address, loc.size, loc.size);
            //System.out.println("Freed and added size "+loc.size);
            this.allocBlk.Delete(loc);
            return 0;
        }
        return -1;
    }
}