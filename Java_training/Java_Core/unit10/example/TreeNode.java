package demo.unit10.example;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> {
    private T value;

    private TreeNode<T> nextSibling;
    private TreeNode<T> firstChild;
    private TreeNode<T> parent;

    public TreeNode(T v) {
        value = v;
    }

    public TreeNode<T> getNextSibling() {
        return nextSibling;
    }

    public TreeNode<T> firstChild() {
        return firstChild;
    }

    public TreeNode<T> parent() {
        return parent;
    }

    public T getValue() {
        return value;
    }

    public void addChild(TreeNode<T> child) {
        if (null == child) {
            return;
        }
        child.parent = parent;
        if (null == firstChild) {
            firstChild = child;
        } else {
            TreeNode<T> temp = firstChild;
            while (temp.nextSibling != null) {
                temp = temp.nextSibling;
            }
            temp.nextSibling = child;
        }
    }

    TreeNode<T> addChild(T value) {
        TreeNode<T> node = new TreeNode<T>(value);
        addChild(node);
        return node;
    }

    public List<T> chonLoc(TreeNodeFilter<T> filter) {
        List<T> list = new ArrayList<T>();
        TreeNodeUntils.visit(this, filter, list);
        return list;
    }
}
