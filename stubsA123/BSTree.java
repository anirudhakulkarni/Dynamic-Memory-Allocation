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
        BSTree current=this.getSent();
        //current is at head sentinal now
        if(current.right==null){
            current.right=new_node;
            new_node.parent=current;
            return new_node;
        }
        //handled if node to be inserted is root and initial tree is empty with only sentinel node
        current=current.right;
        //traverse till place is not found. 
        while(true){
            if(current.key<key||(current.key==key && current.address<address)){
                if(current.right==null){
                    current.right=new_node;
                    new_node.parent=current;
                    return new_node;
                }
                current=current.right;
                continue;
            }
            if(current.key>key||(current.key==key && current.address>address)){
                if(current.left==null){
                    current.left=new_node;
                    new_node.parent=current;
                    return new_node;
                }
                current=current.left;
                continue;
            }
            
            // if(current.key==key){
            //     if(current.address<address){
            //         if(current.right==null){
            //             current.right=new_node;
            //             new_node.parent=current;
            //             return new_node;
            //         }
            //         current=current.right;
            //         continue;
            //     }
            //     if(current.address>address){
            //         if(current.left==null){// this condition might not neccessary. it will be always null. but will fail if we add more than 2 keys with same key
            //             current.left=new_node;
            //             new_node.parent=current;
            //             return new_node;
            //         }
            //         current=current.left;
            //         continue;
            //     }
            // }
        }
        

    }

    public boolean Delete(Dictionary e)
    { 
        BSTree current=this;
        current=current.getSent();
        //System.out.println("TEST"+current.address);
        if(current.right==null){//empty tree
            return false;
        }
        current=current.right;
        //System.out.println("TEST"+current.address);
        while(current!=null){
            if(current.key==e.key&&current.address==e.address && current.size==e.size){
                //System.out.println("Printing this");
                //System.out.println("Printting This right before deletion address "+current.right.address+" key"+current.right.key);
                //System.out.println("deleting "+current.address);
                current.delHelper();
                //System.out.println("Printting This right address "+current.right.address+" key"+current.right.key);

                return true;
            }

            if(current.key>e.key || (current.key==e.key&&current.address>e.address)){
                current=current.left;
                continue;
            }
            if(current.key<e.key || (current.key==e.key&&current.address<e.address)){
                current=current.right;
                continue;
            }   
        }
        
        
        return false;
    }
        
    public BSTree Find(int key, boolean exact)
    { 
        BSTree current=this;
        current=current.getSent();
        if(current.right==null){//empty tree
            return null;
        }
        current=current.right;
        if(exact==true){
            while(current!=null){
                if(current.key==key){
                    return current.findTrue(key);
                    //it is assumed that we are taking key as first preference and address as second preference. Current code will give min key among nodes with equal key.
                    //replace return curren.findTrue(key); with return current; to get first occurance
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
                    return current.findTrue(key);
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
//BUG DECTECTED//covers no children in left
                return current.findFalse(key);
                /*more efficient implementation
                return current.left.findFalse(key);
                */
            }
            return null;
        }
        return null;
    }

    public BSTree getFirst()
    { 
        BSTree current=this.getSent();
        if(current.right==null){
            return null;
        }
        //checked empty tree condition
        current=current.right;
        while(current.left!=null){
            current=current.left;
        }

        return current;
    }

    public BSTree getNext()
    { 
        return this.successor();
    }

    public boolean sanity()
    { 
        return false;
    }
    //helper functions ahead
    private BSTree findTrue(int key){
        BSTree current=this;
        if(current.key==key && current.left==null){
            //System.out.println("THis is "+current.address);
            return current;//no need to return smallest. it is the smallest
        }
        if(current.key<key){
            while(current.right!=null && current.key<key){
                current=current.right;
            }
            return current;//
        }
        if(current.key>=key){
            if(current.predesessor().key!=key){
                return current;//
            }
            return current.left.findTrue(key);
        }

        return this;
    }
    private BSTree findFalse(int key){
        BSTree current= this;
        //System.out.println("calling helper on address= "+current.address+" key= "+current.key);
        if(current.key==key){
            return current.findTrue(key);
        }
        if(current.key>=key && current.left==null){
            //System.out.println("THis is "+current.address);
            return current;//no need to return smallest. it is the smallest
        }
        if(current.key<key){
            while(current.right!=null && current.key<key){
                current=current.right;
            }
            return current;//
        }
        if(current.key>=key){
            if(current.predesessor().key<key){
                return current;//
            }
            return current.left.findFalse(key);
        }

        return this;
        // if(current.left==null){
        //     return null;
        // }
        //System.out.println("left not null addresss= "+current.left.address+" key= "+current.key);
        // current=current.left;
        // if(current.key==key){
        //     return current;
        // }

    }
    private void printer(){
        System.out.println(this.address+" "+this.size+" "+this.key);
    }
    private void delHelper(){
        BSTree current=this;
        if(current.right==null && current.left==null){
            //System.out.println("deleting leaf with address "+current.address+" key "+current.key);
            if(current.parent.right==current){
                current.parent.right=null;
                return;
            }
            current.parent.left=null;
            return;
        }
        //garbage collection will remove it as no pointer to the node exists
        if(current.left==null&&current.right!=null){
            if(current.parent.right==current){
                current.right.parent=current.parent;
                current.parent.right=current.right;
                return;
            }
            current.right.parent=current.parent;
            current.parent.left=current.right;
            return;         
        }
        if(current.right==null&&current.left!=null){
            if(current.parent.left==current){
                current.left.parent=current.parent;
                current.parent.left=current.left;
                return;
            }
            current.left.parent=current.parent;
            current.parent.right=current.left;
            return;            
        }
        if(current.right!=null && current.left!=null){
            BSTree pre=current.predesessor();
            //System.out.println("Deleting address "+pre.address+" key "+pre.key);
            //\pre.printer();
            this.address=pre.address;
            this.key=pre.key;
            this.size=pre.size;
            //System.out.println("Before delete right is with address "+this.right.address+" key "+this.right.key);
            //pre.printer();
            pre.delHelper();
            //System.out.println("After delete right is with address "+this.right.address+" key "+this.right.key);

            return;
        }
        // if(current.left!=null ){//if left child exists
        //     BSTree pre=current.predesessor();
        //     //System.out.println("Deleting address "+pre.address+" key "+pre.key);
        //     //\pre.printer();
        //     if(pre==null){
        //         return;
        //     }
        //     this.address=pre.address;
        //     this.key=pre.key;
        //     this.size=pre.size;
        //     //System.out.println("Before delete right is with address "+this.right.address+" key "+this.right.key);
        //     //pre.printer();
        //     pre.delHelper();
        //     //System.out.println("After delete right is with address "+this.right.address+" key "+this.right.key);

        //     return;
            
        // }
        // if(current.right!=null){//right exists and left is null
        //     BSTree suc=current.successor();
        //     // if(suc==null){//this cant happen as right exists
        //     //     return;
        //     // }
        //     this.address=suc.address;
        //     this.key=suc.key;
        //     this.size=suc.size;
        //     suc.delHelper();
        //     return;
        // }
        // //both null
        // if(current.right==null && current.left==null){
        //     //System.out.println("deleting leaf with address "+current.address+" key "+current.key);
        //     if(current.parent.right==current){
        //         current.parent.right=null;
        //         return;
        //     }
        //     current.parent.left=null;
        //     return;
        // }
        // //garbage collection will remove it as no pointer to the node exists

    }
    private boolean isSentinal(){
        if(this.parent==null){
            return true;
        }
        return false;
    }

    private BSTree successor(){
        BSTree current=this;
        if(current.isSentinal()){
            return null;
        }
        //checked for invalid input of sentinel
        
        if(current.right!=null){
            current=current.right;
            while(current.left!=null){
                current=current.left;
            }
            return current;
        }
        while(current.parent!=null && current.parent.left!=current){
            current=current.parent;
        }
        if(current.parent!=null && !current.parent.isSentinal()){
            return current.parent;
        }   
        return null;

    }

    private BSTree predesessor(){
        BSTree current=this;
        if(current.isSentinal()){
            return null;
        }

        //checked for invalid input of sentinel
        if(current.left!=null){
            current=current.left;
            while(current.right!=null){
                current=current.right;
            }
            return current;
        }

        /*if(current.parent.right==current){
            return current.parent;
        }
        else{*/
            while(current.parent!=null && current.parent.right!=current){
                current=current.parent;
            }
            if(current.parent!=null&&!current.parent.isSentinal()){
                return current.parent;
            }   
            return null;
        //}

    }
    private BSTree getSent(){
        BSTree current=this;
        while(!current.isSentinal()){
            current=current.parent;
        }
        return current;
    }
    public static void main(String[] args) {
        BSTree test=new BSTree();
        test.Insert(84,416,416);
        test.Insert(9,2,2000);
        test.Insert(0,6,111110);
        
        
        
        
        

        BSTree bfit = test.right.right.successor();
        System.out.println(bfit.address + " " + bfit.size + " " + bfit.key);

         Dictionary iit = new BSTree(0, 6, 111110);
         test.Delete(iit);
        // bfit = test.Find(8, false);
        // System.out.println(bfit.address + " " + bfit.size + " " + bfit.key);
        // System.out.println();
        for (BSTree d = test.getFirst(); d != null; d = d.successor()){
            System.out.println(d.address+" "+d.size+" "+d.key);
            if(d.successor()==null){
                System.out.println();
                while(d!=null){
                    System.out.println(d.address+" "+d.size+" "+d.key);
                    d=d.predesessor();
                }
                break;
        
            }
        }

    }
}

//1. delete must be problematic
 


