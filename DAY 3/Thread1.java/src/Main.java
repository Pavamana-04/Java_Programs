public class Main extends Thread {
    public void run() {
        System.out.println("This code is running in a thread");
    }

    public static void main(String[] args) {
        Main thread = new Main();
        thread.start();
    }
}