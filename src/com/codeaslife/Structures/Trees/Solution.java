package com.codeaslife.Structures.Trees;

public class Solution {

    // 找到后继节点
    // root有右树的时候，右树的最左节点就是后继节点
    // root没有右树的时候，
//    public TreeNode getSuccessorNode(TreeNode root) {
//        if (root == null) {
//            return root;
//        }
//        if (root.right != null) {
//            return getLeftMost(root.right);
//        } else {
//            TreeNode parent = root.parent;
//            while (parent != null && parent.left != root) {
//                root = parent;
//                parent = root.parent;
//            }
//            return parent;
//        }
//    }

    public TreeNode getLeftMost(TreeNode root) {
        if (root == null) {
            return root;
        }
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
}

//class TreeNode {
//    int val;
//    TreeNode left;
//    TreeNode right;
//    TreeNode parent;
//
//    TreeNode(int value) {
//        this.val = value;
//    }
//}