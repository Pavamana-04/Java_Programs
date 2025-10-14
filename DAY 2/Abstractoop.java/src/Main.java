public class Main{
    public static void main(String[] args){
        System.out.println("Abstraction Practice");
//creating objects
        Car myCar = new Car("Suzuki");
        Bike myBike = new Bike("TVS");
//method calling
        myCar.start();
        myBike.start();
        myCar.honk();

        System.out.println("Car brand: " + myCar.getBrand());
    }
}
//Abstraction Class
abstract class Vehicle {
    String brand;

    abstract void start();

    public void honk() {
        System.out.println("Beep");
    }
    public String getBrand() {
        return brand;
    }
}

class Car extends Vehicle{
    Car(String brand){
        this.brand = brand;
    }
    @Override
    void start(){
        System.out.println(brand+"car starting with key!");
    }
}

class Bike extends Vehicle{
    Bike(String brand){
        this.brand = brand;
    }
    @Override
    void start(){
        System.out.println(brand+"Bike starting with Kick!");
    }
}