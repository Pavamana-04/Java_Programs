import java.util.*;
import java.util.stream.*;

public class Main{
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);

        int sum = numbers.stream()
                .filter(n -> n % 2 == 0)
                .reduce(0,(a,b)-> a + b);
        System.out.println("Sum of even numbers: "+ sum);
    }
}