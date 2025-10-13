public class Main{
    public static void main (String[] args) {
        int[] numbers = {10,20,30,40,50};
        // For Loop
        for(int i = 0; i < numbers.length; i++) {
            System.out.println("element at index"+ i + ": " + numbers[i]);
        }
        // While Loop
        int i=0;
        while (i < numbers.length) {
            System.out.println("Element at index" + i + ":"+numbers[i]);
            i++;
        }
    }
}