package demo.unit1.example.BaiLam;


import demo.unit1.example.Class.Person;

public class PersonTest {

    public static void main(String[] args) {
        bai5(new String[]{"Hien", "Phuong"});
    }

    public static void bai5(String[] args) {
        Person person1 = new Person(args[0]);
        Person person2 = new Person(args[1]);

        System.out.println("persion 1's name is " + person1.getName());
        System.out.println("persion 2's name is " + person2.getName());
    }
}
