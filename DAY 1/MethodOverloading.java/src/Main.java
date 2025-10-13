import java.util.*;
public class Main {
    public int square(int number) {

        return number * number;
    }

    public double square(double number) {
        return number * number;
    }

    public int add(int a, int b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        Main calc = new Main();

        System.out.println("Square of 4 (int): " + calc.square(4));
        System.out.println("Square of 3.5 (double): " + calc.square(3.5));
        System.out.println("Add 5 + 10" + calc.add(5, 10));
        System.out.println("Add 1 + 2 + 3:" + calc.add(1, 2, 3));
    }
}
