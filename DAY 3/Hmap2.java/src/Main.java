import java.util.*;

public class Main {
    public static void main (String[] args){
        ArrayList<String> arr = new ArrayList<>();// LinkedList<String> arr = new LinkedList<>();
        arr.add("Hi");
        arr.add("I am Pavan");
        Iterator it = arr.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
            System.out.println(arr.size());
            System.out.println(arr.size());
        }
    }
}