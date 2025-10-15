import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner Sc = new Scanner(System.in);

        System.out.print("Enter a number1: ");
        int number1 = Sc.nextInt();

        System.out.print("Enter a number2: ");
        int number2 = Sc.nextInt();

        System.out.println("Addition of two number: "+ (number1+number2));
        System.out.println("Multiplication of two number: "+ (number1 * number2));
        System.out.println("Division of two number: "+ (number1/number2));
        System.out.println("Sub of two number: "+ (number1-number2));


    }
}