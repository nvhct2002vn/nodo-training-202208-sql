package demo.unit9.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class StudentTest {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();

        students.add(new Student(12, "Hien"));
        students.add(new Student(22, "Thuy an cut cho"));
        students.add(new Student(19, "phuong"));
        students.add(new Student(14, "hoa"));
        students.add(new Student(13, "vinh"));

        FilterStd<Student> older = student -> student.getAge() >= 11;
        List<Student> filtered = filter(students, older);

        for (Student std : filtered) {
            System.out.println(std);
        }

        System.out.println("1-------------------------------------------------------");

        Stream<Student> stream = students.stream().filter(std -> std.getAge() >= 13);
        stream.forEach(System.out::println);

        System.out.println("2-------------------------------------------------------");

        Collections.sort(students, (Student std1, Student std2) -> std1.getAge() - std2.getAge());
        students.forEach(System.out::println);

        System.out.println("3-------------------------------------------------------");

        Stream<Student> stream1 = students.stream().sorted((std1, std2) -> std1.getAge() - std2.getAge());
        stream1.forEach(System.out::println);

        System.out.println("4-------------------------------------------------------");

        Comparator<Student> comparator = (std1, std2) -> std1.getAge() - std2.getAge();

        Stream<Student> stream2 = students.stream().sorted(comparator);
        stream2.forEach(System.out::println);

        System.out.println("5-------------------------------------------------------");

        System.out.println("Max is: " + students.stream().max(comparator));
        int sum = students.stream().mapToInt(Student::getAge).sum();
        System.out.println("Average of age is: " + sum / students.size());

        System.out.println("6-------------------------------------------------------");

        students.parallelStream().forEach((it -> System.out.println(Thread.currentThread().getName() + " Hello " + it.getName())));
    }

    public static List<Student> filter(List<Student> students, FilterStd<Student> pred) {

        List<Student> listStd = new ArrayList<>();

        for (Student std : students) {
            if (pred.valid(std)) {
                listStd.add(std);
            }
        }
        return listStd;
    }

}
