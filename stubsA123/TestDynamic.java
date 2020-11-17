//import java.util.Scanner;

public class TestDynamic {

    public static void printarray(A1DynamicMem fox) {
        //Dictionary free1 = fox.freeBlk.getFirst();
        //Dictionary alloc1 = fox.allocBlk.getFirst();
        for (Dictionary d = fox.freeBlk.getFirst(); d != null; d = d.getNext()) {
            System.out.print(d.address + " ");
            System.out.print(d.address + d.size + " ");
            //System.out.print(d.key + " ");
            System.out.print("    ");
        }
        System.out.println(" ");
        for (Dictionary d = fox.allocBlk.getFirst(); d != null; d = d.getNext()) {
            System.out.print(d.address + " ");
            System.out.print(d.address + d.size + " ");
            //System.out.print(d.key + " ");
            System.out.print("    ");
        }
        System.out.println("");
        System.out.println("");
    }
    public static void main(String[] args) {
        //Scanner s = new Scanner(System.in);
        A1DynamicMem tip = new A1DynamicMem(100);
        printarray(tip);
        System.out.println("Allocate(10)");
        tip.Allocate(10);
        printarray(tip);
        System.out.println("Allocate(5)");
        tip.Allocate(5);
        printarray(tip);
        System.out.println("Free(15)");
        tip.Free(15);
        printarray(tip);
        System.out.println("Free(0)");
        tip.Free(0);
        printarray(tip);
        System.out.println("Allocate(15)");
        tip.Allocate(15);
        printarray(tip);
        System.out.println("Allocate(80)");
        tip.Allocate(80);
        printarray(tip);
        System.out.println("Allocate(70)");
        tip.Allocate(70);
        printarray(tip);
        System.out.println("Allocate(1)");
        tip.Allocate(1);
        printarray(tip);
        System.out.println("Allocate(30)");
        tip.Allocate(30);
        printarray(tip);

        
    }
    
}
