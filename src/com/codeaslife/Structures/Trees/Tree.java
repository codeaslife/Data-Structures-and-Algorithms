package com.codeaslife.Structures.Trees;

import java.util.Stack;

public class Tree {
    public void preOrderTraverseRec(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.value + " ");
        preOrderTraverseRec(root.left);
        preOrderTraverseRec(root.right);
    }

    public void preOrderTraverse(TreeNode root) {
        System.out.println("preOrderTraverse: ");
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.add(root);
            while (!stack.isEmpty()) {
                root = stack.pop();
                System.out.println(root.value + " "); // 弹出就处理（打印）
                if (root.right != null) {
                    stack.push(root.right);
                }
                if (root.left != null) {
                    stack.push(root.left);
                }
            }
        }
        System.out.println();
    }

    public void inOrderTraverseRec(TreeNode root) {
        if (root == null) {
            return;
        }
        preOrderTraverseRec(root.left);
        System.out.println(root.value + " ");
        preOrderTraverseRec(root.right);
    }

    // 整棵树的左边界进栈，依次弹出的过程中，对弹出节点的右树同样操作
    public void inOrderTraverse(TreeNode root) {
        System.out.println("inOrderTraverse: ");
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || root != null) {
                // 树的左边界进栈
                if (root != null) {
                    stack.push(root);
                    root = root.left; // root进栈后向左移动
                } else { //
                    root = stack.pop();
                    System.out.println(root.value + " "); // 弹出就打印
                    root = root.right; // 推出之后移动到右树
                }
            }
        }
        System.out.println();
    }

    public void postOrderTraverseRec(TreeNode root) {
        if (root == null) {
            return;
        }
        preOrderTraverseRec(root.left);
        preOrderTraverseRec(root.right);
        System.out.println(root.value + " ");
    }

    // 后序遍历可以通过前序遍历变形得来
    // 用一个栈收集前序遍历弹出的元素
    public void postOrderTraverse(TreeNode root) {
        System.out.println("poseTraverse: ");
        if (root != null) {
            Stack<TreeNode> s1 = new Stack<>();
            Stack<TreeNode> s2 = new Stack<>();
            s1.push(root);
            while (!s1.isEmpty()) {
                root = s1.pop();
                s2.push(root);
                // 先压左再压右
                if (root.left != null) {
                    s1.push(root.left);
                }
                if (root.right != null) {
                    s1.push(root.left);
                }
            }
            while (!s2.isEmpty()) {
                System.out.println(s2.pop().value + " ");
            }
        }
        System.out.println();
    }

    // 如何直观打印一棵二叉树


    // 宽度优先遍历（使用队列）


}

class TreeNode {
    public int value;
    public TreeNode left;
    public TreeNode right;
    public TreeNode() {}
    public TreeNode(int value) {
        this.value = value;
    }
    public TreeNode(int value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}