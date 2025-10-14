public class Main {
    public static void main(String[] args) {
        System.out.println("=== COMPLETE INHERITANCE DEMO ===");

        // Object creation
        Car myCar = new Car("Toyota", "Camry", 2023, 4);
        Motorcycle myBike = new Motorcycle("Honda", "CBR", 2022, true);

        System.out.println("\n--- 1. Inherited Methods ---");
        myCar.displayInfo();
        myBike.displayInfo();

        System.out.println("\n--- 2. Own Methods ---");
        myCar.driveCar();
        myBike.wheelie();

        System.out.println("\n--- 3. Method Overriding ---");
        myCar.start();
        myBike.start();

        System.out.println("\n--- 4. Accessing Fields ---");
        System.out.println("Car Brand: " + myCar.brand);
        System.out.println("Bike Model: " + myBike.model);

        System.out.println("\n--- 5. Using Parent Reference ---");
        Vehicle vehicle1 = new Car("BMW", "X5", 2024, 4);
        Vehicle vehicle2 = new Motorcycle("Yamaha", "R1", 2023, false);

        vehicle1.start();
        vehicle2.start();
    }
}

class Vehicle {
    protected String brand;
    protected String model;
    protected int year;
    public Vehicle(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        System.out.println("Vehicle constructor called: " + brand);
    }
    public void displayInfo() {
        System.out.println("Brand: " + brand + ", Model: " + model + ", Year: " + year);
    }

    public void start() {
        System.out.println("Vehicle is starting...");
    }

    public void stop() {
        System.out.println("Vehicle is stopping...");
    }
}

// CHILD CLASS 1
class Car extends Vehicle {
    private int numberOfDoors;
    public Car(String brand, String model, int year, int numberOfDoors) {
        super(brand, model, year);
        this.numberOfDoors = numberOfDoors;
    }
    @Override
    public void start() {
        System.out.println(brand + " car is starting with key ignition!");
    }
    public void driveCar() {
        System.out.println("Driving " + brand + " " + model + " with " + numberOfDoors + " doors");
    }


    public int getNumberOfDoors() {
        return numberOfDoors;
    }
}

class Motorcycle extends Vehicle {
    private boolean hasFairing;

    public Motorcycle(String brand, String model, int year, boolean hasFairing) {
        super(brand, model, year);
        this.hasFairing = hasFairing;
    }
    @Override
    public void start() {
        System.out.println(brand + " motorcycle is starting with electric start!");
    }


    public void wheelie() {
        System.out.println(brand + " " + model + " is doing a wheelie!");
    }


    public boolean hasFairing() {
        return hasFairing;
    }
}