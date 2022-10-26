import java.util.LinkedList;
import java.util.Queue;

import org.w3c.dom.Node;

public class BST {
    private class TreeNode {
        int key;
        TreeNode left;
        TreeNode right;

        public TreeNode(int key) {
            this.key = key;
        }
    }

    private TreeNode root;
    private int size = 0;

    public int size() {
        return size;
    }

    public boolean hasKey(int key) {

        return hasKey(root, key);
    }

    private boolean hasKey(TreeNode node, int key) {

        if (node == null) {
            return false;
        }
        if (node.key == key) {
            return true;
        } else if (node.key < key) {
            return hasKey(node.right, key);
        } else if (node.key > key) {
            return hasKey(node.left, key);
        }
        return false;
    }

    public int height() {

        return height(root);
    }

    private int height(TreeNode node) {

        if (node == null || size == 1) {
            return -1;
        }

        else {
            return 1 + Math.max(height(node.left), height(node.right));
        }
    }

    public void add(int key) {

        if (hasKey(root, key) == true) {
            return;
        }

        root = add(root, key);

        size++;
    }

    private TreeNode add(TreeNode root, int key) {

        if (root == null) {
            root = new TreeNode(key);
            return root;
        }

        if (key < root.key) {
            root.left = add(root.left, key);
        }

        if (key > root.key) {
            root.right = add(root.right, key);
        }

        return root;
    }

    public String preOrderString() {

        if (root == null) {
            return "";
        }

        return preOrderString(root).trim();

    }

    private String preOrderString(TreeNode node) {

        if (node == null) {
            return "";
        }

        String str = " " + node.key;

        str += preOrderString(node.left);
        str += preOrderString(node.right);

        return str;
    }

    public String inOrderString() {

        if (root == null) {
            return "";
        }
        return inOrderString(root).trim();

    }

    private String inOrderString(TreeNode root) {

        if (root == null) {
            return "";
        }
        String str = inOrderString(root.left);

        str += " " + root.key;

        str += inOrderString(root.right);

        return str;
    }

    public String levelOrderString() {

        if (root == null) {
            return "";
        }

        Queue<TreeNode> q = new LinkedList<>();

        q.add(root);

        String str = "";

        while (!q.isEmpty()) {
            TreeNode temp = q.poll();

            str += " " + temp.key;

            if (temp.left != null) {
                q.add(temp.left);
            }

            if (temp.right != null) {
                q.add(temp.right);
            }
        }
        return str.trim();
    }

    public void delete(int key) {

        if (hasKey(root, key) == false) {
            return;
        }
        root = delete(root, key);

        size--;
    }

    private TreeNode delete(TreeNode node, int key) {

        if (root == null) {
            return null;
        }

        if (key < node.key) {
            node.left = delete(node.left, key);
        }

        else if (key > node.key) {
            node.right = delete(node.right, key);
        }

        else {

            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;

            node.key = minValue(node.right);
            node.right = delete(node.right, node.key);

        }

        return node;
    }

    private int minValue(TreeNode node) {

        while (node.left != null) {
            node = node.left;
        }
        return node.key;

    }

    @Override
    public String toString() {
        return treeToString(root);
    }

    private String treeToString(TreeNode root) {
        if (root == null) {
            return "";
        }
        if (root.left == null && root.right == null) {
            return root.key + "";
        }
        if (root.right == null) {
            return root.key + "(" + treeToString(root.left) + ")";
        }

        return root.key + "(" + treeToString(root.left) + ")(" + treeToString(root.right) + ")";
    }

}