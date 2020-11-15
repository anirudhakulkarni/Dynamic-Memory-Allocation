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
        this.next=new_node;
        new_node.prev=this;
        return new_node;
    }

    public boolean Delete(Dictionary d) 
    {
        if(this.key==d.key){
            
        }
        
        A1List head_node=

        while(this!=null){
            if(this.key==d.key){
                
            }
        }
        return false;
    }

    public A1List Find(int k, boolean exact)
    { 

        return null;
    }

    public A1List getFirst()
    {
        if(this.prev==null){
            return this;
        }
        A1List temp_prev=this.prev;
        while(temp_prev!=null){
            temp_prev=temp_prev.prev;
        }
        return temp_prev;
    }
    
    public A1List getNext() 
    {
        return this.next;
    }

    public boolean sanity()
    {
        return true;
    }
    public static void main(String[] args) {
        A1List test=new A1List(112233,10,1);
        test.Insert(112234, 20, 2);
        System.out.println(test.next.key);

    }

}


