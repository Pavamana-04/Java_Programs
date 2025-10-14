public class Main{
    public static void main(String[] args) {
        System.out.println("Encapsulation ");

        Student student1 = new Student();

        student1.setName("Pavan");
        student1.setAge(22);
        student1.setGpa(7);

        System.out.println("Name: "+ student1.getName());
        System.out.println("Age: "+ student1.getAge());
        System.out.println("Gpa: "+ student1.getGpa());

        System.out.println("Testing Validation");
        student1.setAge(150);
        student1.setGpa(10);
        student1.setName(" ");

        System.out.println("After invalid inputs:");
        System.out.println("Age: "+student1.getAge());
        System.out.println("Gpa: "+student1.getGpa());

    }
}

class Student {
    private String name;
    private int age;
    private double gpa;

    public String getName() {
        return name;
    }
    public int getAge(){
        return age;
    }
    public double getGpa(){
        return gpa;
    }

    public void setName(String name){
        if(name != null&& !name.isEmpty()){
            this.name = name;
            System.out.println("Name set to: "+ name);
        } else{
            System.out.println("Error: Name cannot be empty!");
        }
    }
    public void setAge(int age) {
        if(age > 0 && age <= 120){
            this.age = age;
            System.out.println("Age set to: "+ age);
        } else{
            System.out.println("Error: Age must be between 1 and 120");
        }
    }
    public void setGpa(int gpa) {
        if (gpa >= 0.0 && age <= 10) {
            this.gpa = gpa;
            System.out.println("gpa set to: " + gpa);
        } else {
            System.out.println("Error: Gpa must be between 0.0 and 10.0");
        }
    }
}
