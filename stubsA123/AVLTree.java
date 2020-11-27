// Class: Height balanced AVL Tree
// Binary Search Tree
//10.36-
public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.
    
    public AVLTree Insert(int address, int size, int key) 
    { 
        return null;
    }

    public boolean Delete(Dictionary e)
    {
        return false;
    }
        
    // public AVLTree Find(int k, boolean exact)
    // { 
    //     return null;
    // }

    // public AVLTree getFirst()
    // { 
    //     return null;
    // }

    // public AVLTree getNext()
    // {
    //     return null;
    // }

    public boolean sanity()
    { 
        if(!this.rootforsanity()){//checks for path to the root
            return false;
        }
        AVLTree current=this.getSent();
        if(current.address!=-1||current.key!=-1||current.size!=-1){//invalid sentinel
            return false;
        }
        if(current.left!=null){
            return false;
        }
        if(current.right==null){
            return true;
        }

        current=current.right;
        //now we have non sentinel node
        AVLTree min=new AVLTree(Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE);
        AVLTree max=new AVLTree(Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE);
        
        return (pccheck(current))&&(bstproperty(current,min, max));
        //pccheck ensures acyclic so that bstproperty can work after it
    }
    private boolean hbalance(){
        /*
        1. checks negative heights
        2. checks leaf node with height 1
        3. checks max difference lies between 0 and 1
        4. returns left height balance and right height balance
         */
        if(getheight(this)<0){
            return false;
        }
        if(this.left==null&&this.right==null&&getheight(this)==1){
            return true;
        }
        if(this.left==null&&this.right!=null&&this.right.height<=1){
            return this.right.hbalance();
        }
        if(this.right==null&&this.left!=null&&this.left.height<=1){
            return this.left.hbalance();
        }
        if(this.left!=null&this.right!=null){
            if(this.left.height>=this.right.height&&this.left.height-this.right.height<=1){
                return this.right.hbalance()&&this.left.hbalance();
            }
            if(this.left.height<this.right.height&&this.right.height-this.left.height<=1){
                return this.right.hbalance()&&this.left.hbalance();
            }
        }
        return false;
        /* Alternate code - test it
        if(getheight(this.left)>=getheight(this.right)&&getheight(this.left)-getheight(this.right)<=1){
            return true;
        }
        if(getheight(this.left)<getheight(this.right)&&getheight(this.right)-getheight(this.left)<=1){
            return true;
        }
        return false;
        */
    }

    private boolean rootforsanity(){//floyds like algo
        if(this.parent==null){
            return true;
        }
        AVLTree p1=this;
        AVLTree p2=this;
        while(p1!=null&&p2!=null&&p2.parent!=null){
            //System.out.println("P1 "+p1.key);
            //System.out.println("P2 "+p2.key);
            p1=p1.parent;
            p2=p2.parent.parent;
            if(p1==p2){
                return false;
            }
        }
        return true;
    }
    private boolean pccheck(AVLTree root){//checks if everyone follows child parent relationship
        
        if(root==null){
            return true;
        }
        if(root.left!=null&&root.right!=null){
            return (root.right.parent==root)&&(root.left.parent==root)&&pccheck(root.right)&&pccheck(root.left);
        }
        if(root.left==null&&root.right!=null){
            return (root.right.parent==root)&&pccheck(root.right)&&pccheck(root.left);
        }
        if(root.left!=null&&root.right==null){
            return pccheck(root.right)&&pccheck(root.left)&&(root.left.parent==root);
        }
        return true;
        
    }
    private boolean bstproperty(AVLTree current,AVLTree min, AVLTree max){//checks for bst

        if(current==null){
            return true;
        }
       // System.out.println("cu "+current.address+" "+current.key);
        //System.out.println("MIN "+min.address+" "+min.key);
        if(current.key<min.key || current.key>max.key){
            return false;
        }
        if(current.key==min.key&&current.address<=min.address){//nodes with same data should be at left child hence <=
            return false;
        }
        if(current.key==max.key&&current.address>max.address){
            return false;
        }

        return bstproperty(current.left,min, current)&&bstproperty(current.right, current, max);
    }
    private AVLTree getSent(){
        AVLTree current=this;
        while(!current.isSentinal()){
            current=current.parent;
        }
        return current;
    }
    private boolean isSentinal(){
        if(this.parent==null){
            return true;
        }
        return false;
    }
    private int getheight(AVLTree node){
        if(node!=null){
            return node.height;
        }
        return 0;
    }
}


