package com.codeaslife.Structures.Trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CheckBST {

    public static int preValue = Integer.MIN_VALUE;

    public boolean isBst(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean isLeftBst = isBst(root.left);
        if (!isLeftBst) {
            return false;
        }
        if (root.value <= preValue) {
            return false;
        } else {
            preValue = root.value;
        }
        return isBst(root.right);
    }

    public boolean isValidBST(TreeNode root) {
        List<TreeNode> inOrderList = new ArrayList<>();
        isBst2Helper(root, inOrderList);
        // 遍历inOrderList
        for (int i = 1; i < inOrderList.size(); i++) {
            if (inOrderList.get(i).value <= inOrderList.get(i - 1).value) {
                return true;
            }
        }
        return false;
    }

    public void isBst2Helper(TreeNode root, List<TreeNode> inOrderList) {
        if (root == null) {
            return;
        }
        isBst2Helper(root.left, inOrderList);
        inOrderList.add(root);
        isBst2Helper(root.right, inOrderList);
    }

    public boolean isBst3(TreeNode root) {
        if (root != null) {

            int preValue = Integer.MIN_VALUE;

            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || root != null) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();

                    // 检查遍历的结果是否为升序，不是升序的话就直接返回false
                    if (root.value <= preValue) {
                        return false;
                    } else {
                        preValue = root.value;
                    }

                    root = root.right;
                }
            }
        }
        return true;
    }

    // 递归方法
    public boolean isBst4(TreeNode root) {
        // left && right && max(left) < root && min(right) > root
        return process(root).isBST;
    }

    public ReturnData process(TreeNode root) {
        if (root == null) {
            return null;
        }
        ReturnData leftData = process(root.left);
        ReturnData rightData = process(root.right);

        int min = root.value;
        int max = root.value;
        if (root.left != null) {
            min = Math.min(min, leftData.min);
            max = Math.max(max, leftData.max);
        }
        if (root.right != null) {
            min = Math.min(min, rightData.min);
            max = Math.max(max, rightData.max);
        }

        boolean isBST = true;
        if (leftData != null && !leftData.isBST || leftData.max >= root.value) {
            isBST = false;
        }
        if (rightData != null && !rightData.isBST || rightData.min <= root.value) {
            isBST = false;
        }

        return new ReturnData(isBST, min, max);

    }

    class ReturnData {
        public boolean isBST;
        public int min;
        public int max;

        public ReturnData(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }
}
