public class Main {
    public static void main(String[] args) {
        interface MessagePrinter {
            void printMessage();
        }

        MessagePrinter printer = () -> System.out.println("Hello from a lambda expression!");

        printer.printMessage();
    }
}