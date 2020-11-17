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
        A1List new_node=new A1List(address,size,key);
        new_node.next=this.next;
        this.next.prev=new_node;
        this.next=new_node;
        new_node.prev=this;
        return new_node;
    }

    public boolean Delete(Dictionary d) 
    {
        A1List position=this.Find(d.key, true);
        if(position==null){
            return false;            
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
            while(!isTailsent(current)){
                if(current.key==k){
                    return current;
                }
                current=current.next;
            }
            current=this;
            while(!isHeadsent(current)){
                if(current.key==k){
                    return current;
                }
                current=current.prev;
            }
            return null;
        }
        if(exact==false){
            A1List current=this;
             //System.out.println(current.next.size);
            while(!isTailsent(current)){
                if(current.key>=k){
                    return current;
                }
                //System.out.println("INNER "+current.address);
               //System.out.println("Inner "+current.key);
                //System.out.println("Inner "+current.size);
                current=current.next;
            }
            current=this;
            while(!isHeadsent(current)){
                if(current.key>=k){
                    return current;
                }
                current=current.prev;
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
        while(!isHeadsent(temp_prev)){
            temp_prev=temp_prev.prev;
        }
        return temp_prev.next;
    }
    
    public A1List getNext() 
    {
        // If the element is the last element of DLL then we should return null instead of tail sentinal node
        // Assume: No tail sentinal is given as input. if given return null
        if(this==null || isTailsent(this)){
            return null;
        }
        if(isTailsent(this.next)){
            return null;
        }
        return this.next;
    }

    public boolean sanity()
    {
        A1List currentb=this;
        A1List currentf=this;
        if(isCycle(this)){
            return false;
        }
        //traverse till head sentinal and check if node.prev.next=node
        while(!isHeadsent(currentb)){
            if(currentb.prev.next!=currentb){
                return false;
            }
            currentb=currentb.prev;
        }
        while(!isTailsent(currentf)){
            if(currentf.next.prev!=currentf){
                return false;
            }
            currentb=currentb.prev;
        }
        if(currentb.prev!=null){
            return false;
        }
        if(currentf.next!=null){
            return false;
        }

        return true;
    }
    private boolean isHeadsent(A1List testnode){
        if(testnode==null){
            return false;
        }
        if(testnode.address==-1 && testnode.size==-1 && testnode.key==-1 &&testnode.prev==null){
            return true;
        }
        return false;
    }
    private boolean isTailsent(A1List testnode){
        if(testnode.address==-1 && testnode.size==-1 && testnode.key==-1 &&testnode.next==null){
            return true;
        }
        return false;
    }
    private boolean isCycle(A1List current){
        A1List current2=current;
//remaining//
        return false;
    }
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
        while(!test.isTailsent(test)){
        //System.out.println("getFirst = "+test.getFirst().key);
        //System.out.println("getNext = "+test.getNext().key);
        System.out.println("This = "+test.key);
        test=test.next;
        }
        System.out.println(test.key);
    }

}


