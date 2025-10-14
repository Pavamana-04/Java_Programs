public class Main {
    public static void main(String[] args) {
        System.out.println("POLYMORPHISM");

        Animal myDog = new Dog();
        Animal myCat = new Cat();
        Animal myBird = new Bird();

        System.out.println("\n--- 1. Method Overriding ---");
        myDog.makeSound();
        myCat.makeSound();
        myBird.makeSound();

        System.out.println("\n--- 2. Polymorphism with Array ---");
        Animal[] animals = {new Dog(), new Cat(), new Bird(), new Dog()};

        for (Animal animal : animals) {
            animal.makeSound();
        }

        System.out.println("\n--- 3. Runtime Polymorphism ---");
        Animal animal;

        animal = new Dog();
        animal.makeSound();

        animal = new Cat();
        animal.makeSound();

        animal = new Bird();
        animal.makeSound();

        System.out.println("\n--- 4. Using Parent Methods ---");
        myDog.eat();
        myCat.eat();
        myBird.eat();

        System.out.println("\n--- 5. Type Checking ---");
        checkAnimalType(myDog);
        checkAnimalType(myCat);
        checkAnimalType(myBird);
    }

    public static void checkAnimalType(Animal animal) {
        if (animal instanceof Dog) {
            System.out.println("This is a Dog");
            ((Dog) animal).fetch();
        } else if (animal instanceof Cat) {
            System.out.println("This is a Cat");
            ((Cat) animal).scratch();
        } else if (animal instanceof Bird) {
            System.out.println("This is a Bird");
            ((Bird) animal).fly();
        }
    }
}


class Animal {
    public void makeSound() {
        System.out.println("Animal makes a sound");
    }

    public void eat() {
        System.out.println("Animal is eating...");
    }
}


class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof! Woof!");
    }
    public void fetch() {
        System.out.println("Dog is fetching the ball!");
    }
}


class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow! Meow!");
    }


    public void scratch() {
        System.out.println("Cat is scratching the furniture!");
    }
}


class Bird extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Chirp! Chirp!");
    }


    public void fly() {
        System.out.println("Bird is flying high!");
    }
}