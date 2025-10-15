import java.util.Stack;

public class Main {
    public static void main (String[] args){
        Stack<Integer> stk = new Stack<>();
        stk.push(1);//stk.add(1)
        stk.push(2);//stk.add(2)
        stk.pop();
        System.out.println(stk.peek());
        System.out.println(stk);

    }
}
