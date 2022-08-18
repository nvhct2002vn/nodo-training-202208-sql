package demo.unit9.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FunctionTest {
    public static void main(String[] args) {
        functionTest3();
    }

    public static void functionTest1() {

        List<Student> students = new ArrayList<>();

        Consumer<Student> c = (Student std) -> {
            if (std.getAge() > 23) {
                std.setAge(23);
            }
            students.add(std);
        };
        c.accept(new Student(34, "Nguyen van A"));
        System.out.println(students.get(0));
    }

    public static void functionTest2() {
        String[] names = {"Nguyen viet hien", "Tran duc phuong", "Le minh thuy"};
        int[] ages = {21, 22, 21};
        IntStream intStream = IntStream.rangeClosed(0, names.length - 1);
        Stream<Object> stream = intStream.mapToObj(value -> new Student(ages[value], names[value]));

        Consumer<Student> c = (Student std) -> {
            System.out.println(std);
        };
        stream.forEach(System.out::println);
    }

    public static void functionTest3() {
        String[] names = {"Nguyen viet hien", "Tran duc phuong", "Le minh thuy"};
        int[] ages = {21, 22, 21};
        IntStream intStream = IntStream.rangeClosed(0, names.length - 1);
        Stream<Student> stream = intStream.mapToObj(value -> new Student(ages[value], names[value]));

        Consumer<Student> c = (Student std) -> {
            System.out.println(std);
        };
        Function<Student, String> JsonFunction = (std) -> {
            StringBuilder builder = new StringBuilder();
            builder.append("student{\n");
            builder.append("\tid: ").append(std.getId()).append("\n");
            builder.append("\tname: ").append(std.getName()).append("\n");
            builder.append("\tage: ").append(std.getAge()).append("\n");
            builder.append("}");
            return builder.toString();
        };

        c = (student -> {
            System.out.println(JsonFunction.apply(student));
        });

//        stream.forEach(System.out::println);

        Predicate<Student> predicate = (student -> {
            return student.getAge() > 21;
        });

        Stream<Student> older = stream.filter(predicate);

        older.forEach(System.out::println);

        Supplier<Student> supplier = () -> {
            return new Student(21, "Nguyen viet hien");
        };

        System.out.println(JsonFunction.apply(supplier.get()));
    }
}
