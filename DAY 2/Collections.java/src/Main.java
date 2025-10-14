import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== JAVA COLLECTIONS FRAMEWORK ===\n");

        // LIST
        System.out.println("1. LIST (Ordered, allows duplicates)");
        listDemo();

        // SET
        System.out.println("\n2. SET (No duplicates)");
        setDemo();

        //  MAP
        System.out.println("\n3. MAP (Key-Value pairs)");
        mapDemo();

        //  QUEUE
        System.out.println("\n4. QUEUE (FIFO)");
        queueDemo();

        // Useful
        System.out.println("\n5. USEFUL COLLECTION METHODS");
        usefulMethods();
    }

    public static void listDemo() {
        // ArrayList
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Apple"); // Duplicate allowed

        System.out.println("ArrayList: " + fruits);
        System.out.println("Get index 1: " + fruits.get(1));
        System.out.println("Contains 'Mango': " + fruits.contains("Mango"));

        // LinkedList
        List<Integer> numbers = new LinkedList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(1, 15); // Insert at specific position

        System.out.println("LinkedList: " + numbers);
    }

    public static void setDemo() {
        // HashSet
        Set<String> hashSet = new HashSet<>();
        hashSet.add("Pavamana");
        hashSet.add("Krishnan");
        hashSet.add("Pavan"); // Won't add duplicate
        hashSet.add("Pava");

        System.out.println("HashSet: " + hashSet);

        // Trees
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(50);
        treeSet.add(10);
        treeSet.add(30);
        treeSet.add(10); // Duplicate ignored

        System.out.println("TreeSet (Sorted): " + treeSet);

        // LinkedHashSet -
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("Zebra");
        linkedHashSet.add("Apple");
        linkedHashSet.add("Banana");

        System.out.println("LinkedHashSet (Insertion Order): " + linkedHashSet);
    }

    public static void mapDemo() {
        // HashMap
        Map<Integer, String> employees = new HashMap<>();
        employees.put(101, "Pavamana");
        employees.put(102, "Pavan");
        employees.put(103, "krishnan");
        employees.put(101, "Pavan Updated");

        System.out.println("HashMap: " + employees);
        System.out.println("Get employee 102: " + employees.get(102));

        // TreeMap
        Map<String, Integer> priceMap = new TreeMap<>();
        priceMap.put("Laptop", 50000);
        priceMap.put("Mobile", 20000);
        priceMap.put("Tablet", 30000);

        System.out.println("TreeMap (Sorted): " + priceMap);

        // Iterate
        System.out.println("Iterating HashMap:");
        for(Map.Entry<Integer, String> entry : employees.entrySet()) {
            System.out.println("ID: " + entry.getKey() + ", Name: " + entry.getValue());
        }
    }

    public static void queueDemo() {
        // PriorityQueue
        Queue<Integer> pq = new PriorityQueue<>();
        pq.offer(50);
        pq.offer(10);
        pq.offer(30);
        pq.offer(20);

        System.out.print("PriorityQueue: ");
        while(!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
        System.out.println();

        // LinkedList
        Queue<String> queue = new LinkedList<>();
        queue.offer("First");
        queue.offer("Second");
        queue.offer("Third");

        System.out.println("Queue: " + queue);
        System.out.println("Peek: " + queue.peek());
        System.out.println("Poll: " + queue.poll());
        System.out.println("Queue after poll: " + queue);
    }

    public static void usefulMethods() {
        List<String> list = new ArrayList<>();

        // Add elements
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        list.add(1, "Mango");

        System.out.println("Original List: " + list);
        System.out.println("Size: " + list.size());
        System.out.println("Is Empty: " + list.isEmpty());
        System.out.println("Contains 'Apple': " + list.contains("Apple"));

        // Remove elements
        list.remove("Banana");
        list.remove(0);
        System.out.println("After removal: " + list);

        // Convert to array
        String[] array = list.toArray(new String[0]);
        System.out.println("Array: " + Arrays.toString(array));

        // Sort the list
        List<Integer> numbers = Arrays.asList(50, 10, 30, 20);
        Collections.sort(numbers);
        System.out.println("Sorted Numbers: " + numbers);
    }
}