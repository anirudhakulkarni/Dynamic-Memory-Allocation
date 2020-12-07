// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
        
        A1List current=this;
        //traverse till head. Uncomment this if clarification comes that we have to insert at head only irrespective of node on which it is called
        //finally 
        // while(!current.isHeadsent() ){
        //     current=current.prev;
        // }
        if(current.isTailsent()){
            return null;
        }
        A1List new_node=new A1List(address,size,key);
        new_node.next=current.next;
        current.next.prev=new_node;
        current.next=new_node;
        new_node.prev=current;
        return new_node;
    }

    public boolean Delete(Dictionary d) 
    {
        A1List position=null;
        A1List current=this;
            while(!current.isHeadsent()){
                current=current.prev;
            }
            current=current.next;
            while(!current.isTailsent()){
                if(current.key==d.key &&current.size==d.size && current.address==d.address){
                    position=current;
                    break;
                }
                current=current.next;
        }

        //A1List position=this.Find(d.key, true);
        if(position==null||position.isHeadsent()||position.isTailsent()){
            return false;            
        }
        //if node to be deleted is same element we are calling
        //then store data of prev element in it and then delete prev node
        //trust me its super intelligent :p
        if(position==this){
            this.key=this.prev.key;
            this.address=this.prev.address;
            this.size=this.prev.size;
            if(this.prev.isHeadsent()){
                this.prev=null;
                return true;
            }
            this.prev=this.prev.prev;
            this.prev.next=this;
            
            
            return true;
        }
        position.prev.next=position.next;
        position.next.prev=position.prev;
        position.next=null;position.prev=null;
        return true;
    }

    public A1List Find(int k, boolean exact)
    {
        if(exact==true){
            A1List current=this;
            while(!current.isHeadsent()){
                current=current.prev;
            }
            current=current.next;
            while(!current.isTailsent()){
                if(current.key==k){
                    return current;
                }
                current=current.next;
            }
            
            return null;
        }
        if(exact==false){
            A1List current=this;
             //System.out.println(current.next.size);
             while(!current.isHeadsent()){

                current=current.prev;
            }
            current=current.next;
            while(!current.isTailsent()){
                if(current.key>=k){
                    return current;
                }
                //System.out.println("INNER "+current.address);
               //System.out.println("Inner "+current.key);
                //System.out.println("Inner "+current.size);
                current=current.next;
            }            
            //debug
            //System.out.println("searching k "+k+" current address,data"+current.address+" "+current.size );
            return null;
        }
        return null;
    }

    public A1List getFirst()
    {
        //if node is null or list is empty
        if((this==null)||(this.prev==null && this.next.next==null)||(this.next==null && this.prev.prev==null)){
            return null;
        }
        //we have DLL with atleast 1 element and 2 sentinal nodes
        A1List temp_prev=this;
        if(this.prev==null){
            temp_prev=this.next;    
        }
        //traverse till find headsentinal and then output next element
        while(!temp_prev.isHeadsent()){
            temp_prev=temp_prev.prev;
        }
        if(temp_prev.next.isTailsent()){
            return null;
        }
        return temp_prev.next;
    }
    
    public A1List getNext() 
    {
        // If the element is the last element of DLL then we should return null instead of tail sentinal node
        // Assume: No tail sentinal is given as input. if given return null
        if(this==null || this.isTailsent()){
            return null;
        }
        if(this.next.isTailsent()){
            return null;
        }
        return this.next;
    }

    public boolean sanity()
    {
        A1List currentb=this;
        A1List currentf=this;
        //checks if atleast 2 nodes are present including sentinels
        if(this.next==null && this.prev==null){
            return false;
        }
        //checks cycle
        if(isCycle(this)){
            return false;
        }
        //traverse till head and check if node.prev.next=node
        while(currentb.prev!=null){
            if(currentb.prev.next!=currentb){
                return false;
            }
            currentb=currentb.prev;
        }
        //traverse till tail and check if node.prev.next=node
        
        while(currentf.next!=null){
            if(currentf.next.prev!=currentf){
                return false;
            }
            currentf=currentf.next;
        }
        //checks if data of first element is -1 also with prev=null, so as to be a head sentinel
        if(!currentb.isHeadsent()){
            return false;
        }
        //checks if data of last element is -1 also with next=null, so as to be a tail sentinel
        if(!currentf.isTailsent()){
            return false;
        }

        return true;
    }
    private boolean isHeadsent(){
        // if(this==null){
        //     return false;
        // }
        if(this.address==-1 && this.size==-1 && this.key==-1 &&this.prev==null){
            return true;
        }
        return false;
    }
    private boolean isTailsent(){
        // if(this==null){
        //     return false;
        // }
        if(this.address==-1 && this.size==-1 && this.key==-1 &&this.next==null){
            return true;
        }
        return false;
    }
    private boolean isCycle(A1List current){
        A1List current1=current;
        A1List current2=current;
        while(current2!=null && current2.next!=null &&current1!=null){
            current1=current1.next;
            current2=current2.next.next;
            if(current1==current2){
                return true;
            }
        }
        current1=current;
        current2=current;
        while(current2!=null && current2.prev!=null &&current1!=null){
            current1=current1.prev;
            current2=current2.prev.prev;
            if(current1==current2){
                return true;
            }
        }
//remaining//
        return false;
    }


/*
    //MAKE SURE TO DELETE MAIN FUNCTION BEFORE SUBMITTING
    public static void main(String[] args) {
        A1List test=new A1List();
        System.out.println("getFirst = "+test.getFirst());
        System.out.println("getNext = "+test.getNext());
        test.Insert(112234, 20, 1);
        test.Insert(112234, 20, 2);
        test.Insert(112234, 20, 3);
        Dictionary a=new A1List(112234,20,3);
        test.next.Delete(a);
        while(!test.isTailsent()){
        //System.out.println("getFirst = "+test.getFirst().key);
        //System.out.println("getNext = "+test.getNext().key);
        System.out.println("This = "+test.key);
        test=test.next;
        }
        System.out.println(test.key);
    }
    */

}


