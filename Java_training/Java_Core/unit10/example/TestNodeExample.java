package demo.unit10.example;

public class TestNodeExample {

    private static void test(Node<?> node) {
        while (node != null) {
            System.out.println(node);
            System.out.println("value is: " + node.getValue() + "'");
            node = node.next;
        }
    }

    public static void testRun1() {
        Node<String> root = new Node<>("step 1");
        Node<String> node1 = new Node<>("Step 2");
        root.next = node1;

        test(root);
    }

    public static void testRun2() {
        Node<Integer> root = new Node<>(99, new Node<Integer>(23, new Node<Integer>(11)));
        test(root);
    }

    public static void main(String[] args) {
        testRun1();
    }

}
