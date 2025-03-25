package com.cardove;

/**
 * 红黑树遍历演示类
 * 
 * @author cardove
 */
public class RedBlackTreeDemo {
    
    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        
        // 插入一些数据
        int[] data = {50, 30, 70, 20, 40, 60, 80};
        System.out.println("插入数据:");
        for (int value : data) {
            System.out.print(value + " ");
            tree.insert(value);
        }
        System.out.println("\n");
        
        // 执行各种遍历
        tree.preOrderTraversal();   // 前序遍历
        tree.inOrderTraversal();    // 中序遍历
        tree.postOrderTraversal();  // 后序遍历
        tree.levelOrderTraversal(); // 层序遍历
    }
}