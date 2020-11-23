import java.util.Scanner;
public class Driver{
    public static void main(String args[]){
    	//long startTime = System.nanoTime();

        int numTestCases;
        Scanner sc = new Scanner(System.in);
        numTestCases = sc.nextInt();
        int num=0;
        while(numTestCases-->0){
            int size;
            size = sc.nextInt();
            A1DynamicMem obj = new A1DynamicMem(size);
            int numCommands = sc.nextInt();
            //testing purpose
            //int origNum = numCommands;
            //
            while(numCommands-->0) {
                String command;
                command = sc.next();
                int argument;
                argument = sc.nextInt();
                int result = -5;
                switch (command) {
                    case "Allocate":
                        result = obj.Allocate(argument);
                        break;
                    case "Free":
                        result = obj.Free(argument);
                        break;
                    default:
                        break;
                }
              /* if(num==2728||num==2727){
                    System.out.println("command= "+command+" argument= "+argument);
                    obj.printBlk();
                    System.out.println(result);
                }
                if(num==2728)break;
               */ 
                num++;
                System.out.println(result);
                //for testing

                //System.out.println("command= "+command+" argument= "+argument);
                //obj.printBlk();
            }
            
        }
    //long stopTime = System.nanoTime();
	//System.out.println((stopTime - startTime)/1000000000.0);
    }


}