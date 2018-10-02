import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;


public class Kaprekar_Numbers{

  public static void main (String[]args){
    Scanner scan = new Scanner(System.in);
    boolean run = false;

    while(run==false){
      try {
        int low = Integer.valueOf(args[0]);
        int high = Integer.valueOf(args[1]);
        while(low<=high){
            if (Kaprekar_check(low)){
                 System.out.print(low + " ");
               }
               low++;
             }
        run = true;

      } catch (InputMismatchException e){
        System.out.println("Please input two integers separated by whitespace");
      }
    }
  }

    static boolean Kaprekar_check(int n){
        int sqr = n*n;
        String sqr_str = Integer.toString(sqr);
        int count_digit =Integer.toString(n).length();
        for (int count = 1; count <= count_digit; count++) {          
            String a = sqr_str.substring(0,count);
            String b = sqr_str.substring(count);
           
     
            if(a!=null && b!=null){
                if(b.isEmpty()){
                    b ="0";
                }
                if (Integer.parseInt(a) == n && Integer.parseInt(a) != 1 ){ 
                    continue;
                }
               
                if((Integer.parseInt(a)+Integer.parseInt(b))== n){
                    return true;
                }
           
            }
            
            
        }
        return false;
    }
}
