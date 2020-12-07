public class ignore{
    int aa;
    public ignore(){
        this.aa=15;
    }
    public Integer test(){
        ignore temp=this;
        temp.aa=temp.aa+1;
        return this.aa;
    }
    

public static void main(String[] args) {
    ignore a=new ignore();
    System.out.println(a.test());
}
}