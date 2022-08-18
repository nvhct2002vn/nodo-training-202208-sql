package demo.unit10.example;

import java.util.List;

public class TreeNodeTest {
    public static void main(String[] args) {
        TreeNode<String> root = new TreeNode<>("0");

        TreeNode<String> node1 = new TreeNode<>("1.0");
        root.addChild(node1);
        root.addChild(new TreeNode<>("1.1"));
        root.addChild(new TreeNode<>("1.2"));
        root.addChild(new TreeNode<>("1.3"));

        TreeNode<String> node2 = node1.getNextSibling();
        System.out.println("next sibling of" + node1.getValue() + " is " + node2.getValue());

        node2.addChild("2.0.0");
        node2.addChild("2.1.0");
        node2.addChild("2.1.1");

        TreeNode<String> node3 = node1.getNextSibling();
        System.out.println("next sibling of" + node2.getValue() + " is " + node3.getValue());

        System.out.println("check contains 2.1.1 = " + TreeNodeUntils.contains(root, "2.1.1"));

        List<String> list = root.chonLoc(value -> {
            return value.contains(".0");
        });
        System.out.println("co tong so " + list.size() + " chua '.0'");
        list.forEach(value -> System.out.println("phan tu loc ===> " + value));
    }
}
