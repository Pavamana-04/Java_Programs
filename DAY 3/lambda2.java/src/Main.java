import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Pavamana", "Krishnan", "Pavan");

        Collections.sort(names, (a, b) -> a.compareToIgnoreCase(b));
        
        names.forEach(name -> System.out.println(name));
    }
}