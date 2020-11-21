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
        if(current.key>key){
            if(current.right==null){
                current.right=new_node;
                new_node.parent=current;
                return new_node;
            }
            else{
                return current.right.Insert(address, size, key);
            }
        }
        if(current.key>key){
            if(current.left==null){
                current.left=new_node;
                new_node.parent=current;
                return new_node;
            }
            else{
                return current.left.Insert(address, size, key);
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
        BSTree current=this;
        if(!current.isSentinal()){
            current=current.parent;
        }
        current=current.right;
        if(exact==true){
            while(current!=null){
                if(current.key==key){
                    return current;
                }
                if(current.key>key){
                    current=current.left;
                    continue;
                }
                if(current.key<key){
                    current=current.right;
                    continue;
                }   
            }
            return null;
        }
        if(exact==false){
            int flag=0;
            while(current!=null){
                if(current.key==key){
                    return current;
                }
                if(current.key<key){
                    current=current.right;
                    continue;
                }
                if(current.key>key){
                    flag=1;
                    break;
                }
            }
            if(flag==1){
                return current.predesessor();
            }
            return null;
        }
        return null;
    }

    public BSTree getFirst()
    { 
        if(this==null){
            return null;
        }
        BSTree current=this;
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

    private BSTree successor(){
        BSTree current=this;
        if(current.right!=null){
            current=current.right;
            while(current.left!=null){
                current=current.left;
            }
            return current;
        }
        if(current.parent==null){
            return null;
        }
        if(current.parent.left==current){
            return current.parent;
        }
        return null;

    }
    private BSTree predesessor(){
        BSTree current=this;
        if(current.left!=null){
            current=current.left;
            while(current.right!=null){
                current=current.right;
            }
            return current;
        }
        if(current.parent==null){
            return null;
        }
        if(current.parent.right==current){
            return current.parent;
        }
        return null;

    }
}


 


