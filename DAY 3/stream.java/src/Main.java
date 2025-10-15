import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args){
        List<Integer> numbers = Arrays.asList(10,20,30,40,50);

        //Using Stream to filter

        numbers.stream()
                .filter(n -> n > 25) /// keeping num greater than 25
                .forEach(System.out::println);///printing each
    }
}