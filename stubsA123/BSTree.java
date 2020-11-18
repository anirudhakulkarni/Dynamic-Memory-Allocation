// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }

    public BSTree Insert(int address, int size, int key) 
    { 
        BSTree new_node=new BSTree(address,size,key);
        BSTree current=this;
        

        if(current.isSentinal()){
            if(current.right==null){
                current.right=new_node;
                new_node.parent=current;
                return new_node;
            }
            else{
                return current.right.Insert(address, size, key);
            }
        }
        if(this.key>key){
            if(this.right==null){
                this.right=new_node;
                new_node.parent=this;
                return new_node;
            }
            else{
                return this.right.Insert(address, size, key);
            }
        }
        if(this.key>key){
            if(this.left==null){
                this.left=new_node;
                new_node.parent=this;
                return new_node;
            }
            else{
                return this.left.Insert(address, size, key);
            }
        }
        return null;
    }

    public boolean Delete(Dictionary e)
    { 

        return false;
    }
        
    public BSTree Find(int key, boolean exact)
    { 
        if(!this.isSentinal()){
            return this.parent.Find(key, exact);
        }

        if(exact==true){
            if(this==null){
                return null;
            }
            if(this.key==key){
                return this;
            }
            if(this.key>key){
                return this.right.Find(key, true);
            }
            if(this.key<key){
                return this.left.Find(key, true);
            }
            
        }
        if(exact==false){
            if(this==null){
                return null;
            }
            if(this.key>=key){
                return this;
            }
            if(this.key<key){
                return this.left.Find(key, false);
            }
        }
        return null;
    }

    public BSTree getFirst()
    { 
        if(this==null){
            return null;
        }
        if(!this.isSentinal()){
            return this.parent.getFirst();
        }
        return null;

    }

    public BSTree getNext()
    { 
        if(this==null){
            return null;
        }
        return null;
    }

    public boolean sanity()
    { 
        return false;
    }

    private boolean isSentinal(){
        if(this.parent==null){
            return true;
        }
        return false;
    }

}


 


