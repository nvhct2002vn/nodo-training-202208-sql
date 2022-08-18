package demo.unit10.example;

public class TestStackExample {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(23);
        stack.push(45);
        stack.push(12);
        stack.push(89);
        stack.push(11);

        while (stack.hasNext()) {
            System.out.println("Data: " + stack.pop());
        }
    }
}
