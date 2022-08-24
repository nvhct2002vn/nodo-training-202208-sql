package demo.unit10.example;

public class Node<T> {
    T value;

    Node<T> next;

    Node(T v) {
        value = v;
    }

    Node(T v, Node<T> n) {
        value = v;
        next = n;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", next=" + next +
                '}';
    }
}
