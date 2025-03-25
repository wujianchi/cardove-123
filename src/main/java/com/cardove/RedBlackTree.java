package com.cardove;

/**
 * 红黑树实现
 * 
 * @author cardove
 */
public class RedBlackTree<T extends Comparable<T>> {
    
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    
    private Node root;
    
    private class Node {
        T data;
        Node left, right, parent;
        boolean color; // true for red, false for black
        
        Node(T data) {
            this.data = data;
            this.color = RED;
        }
    }
    
    /**
     * 插入节点
     */
    public void insert(T data) {
        root = insert(root, data);
        root.color = BLACK; // 根节点始终为黑色
    }
    
    private Node insert(Node h, T data) {
        if (h == null) return new Node(data);
        
        int cmp = data.compareTo(h.data);
        if (cmp < 0) {
            h.left = insert(h.left, data);
            h.left.parent = h;
        } else if (cmp > 0) {
            h.right = insert(h.right, data);
            h.right.parent = h;
        } else {
            h.data = data; // 更新已存在的节点
        }
        
        // 修复红黑树性质
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        
        return h;
    }
    
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }
    
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        if (x.left != null) x.left.parent = h;
        x.left = h;
        x.parent = h.parent;
        h.parent = x;
        x.color = h.color;
        h.color = RED;
        return x;
    }
    
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        if (x.right != null) x.right.parent = h;
        x.right = h;
        x.parent = h.parent;
        h.parent = x;
        x.color = h.color;
        h.color = RED;
        return x;
    }
    
    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }
    
    /**
     * 前序遍历
     */
    public void preOrderTraversal() {
        System.out.println("前序遍历:");
        preOrderTraversal(root);
        System.out.println();
    }
    
    private void preOrderTraversal(Node node) {
        if (node == null) return;
        System.out.print(node.data + " ");
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }
    
    /**
     * 中序遍历
     */
    public void inOrderTraversal() {
        System.out.println("中序遍历:");
        inOrderTraversal(root);
        System.out.println();
    }
    
    private void inOrderTraversal(Node node) {
        if (node == null) return;
        inOrderTraversal(node.left);
        System.out.print(node.data + " ");
        inOrderTraversal(node.right);
    }
    
    /**
     * 后序遍历
     */
    public void postOrderTraversal() {
        System.out.println("后序遍历:");
        postOrderTraversal(root);
        System.out.println();
    }
    
    private void postOrderTraversal(Node node) {
        if (node == null) return;
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.print(node.data + " ");
    }
    
    /**
     * 层序遍历
     */
    public void levelOrderTraversal() {
        System.out.println("层序遍历:");
        int height = height(root);
        for (int i = 1; i <= height; i++) {
            printLevel(root, i);
        }
        System.out.println();
    }
    
    private void printLevel(Node node, int level) {
        if (node == null) return;
        if (level == 1) {
            System.out.print(node.data + " ");
        } else if (level > 1) {
            printLevel(node.left, level - 1);
            printLevel(node.right, level - 1);
        }
    }
    
    private int height(Node node) {
        if (node == null) return 0;
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
}