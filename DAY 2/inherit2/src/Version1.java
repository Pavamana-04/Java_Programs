

public class Version1 {
    public void layout(){
        System.out.println("Version 1");
        System.out.println("red colour menu");
    }

    public static void main(String[] args){

        Version1 caller = new Version1();
        caller.layout();
    }
}

class Version2 extends Version1{
    public void layout2(){
        System.out.println("Version 2");
        System.out.println("Yellow colour menu");
    }
    public static void main(String[] args){

        Version2 caller = new Version2();
        caller.layout();
        caller.layout2();
    }
}