package com.codeaslife.Structures.Trees;

import java.util.*;

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
    public void bfs(TreeNode root) {
        if (root != null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>(); // Java中LinkedList是Queue的一种实现
        queue.add(root);
        HashMap<TreeNode, Integer> levelMap = new HashMap<>();
        levelMap.put(root, 1);
        int curLevel = 1; // 当前在多少层
        int curLevelNodes = 0; // 当前层有多少节点
        int max = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 0;
            }

            System.out.println(cur.value);
            if (cur.left != null) {
                // 进栈之前就记录好节点的层数
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
        }
    }

    // 验证一棵树是否为BST
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

//    public boolean isBst2(TreeNode root) {
//        List<TreeNode> inOrderList = new ArrayList<>();
//        // 主函数将process2函数中存储的list打印出来，for循环为升序就是BST
//
//
//    }

    public static void process2(TreeNode root, List<TreeNode> inOrderList) {
        if (root == null) {
            return;
        }
        process2(root.left, inOrderList);
        inOrderList.add(root); // 其实就是中序遍历，但是这里不打印，改为存储在list中
        process2(root.right, inOrderList);
    }

    // 完全二叉树的判断
    // 1)任何一个节点，有右无左false 2)不违反第一原则下，如果遇到第一个左右不全的节点，后续所有节点必须全是叶子节点
    public boolean isCBT(TreeNode root) {
        if (root == null) {
            return true;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        // 是否遇到左右孩子不全的节点
        boolean leaf = false; // 最开始是false
        TreeNode l = null;
        TreeNode r = null;
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            l = root.left;
            r = root.right;
            if (
                    // 条件二：如果遇到左右不全的节点之后，并且发现当前节点不是叶子节点
                    (leaf && (l != null || r != null) ||
                    (l == null || r != null))) { // 条件一：只有右节点，无左节点
                return false;
            }

            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true; // 一旦被标记为true，就不会再变了
            }
        }
        return true;
    }

    // 如何判断一棵树是满二叉树
    // 最大深度l - 节点数N (N = 2 ^ l - 1)


    // 判断平衡二叉树（AVL Tree ?）
    // left right && |left - right| <= 1 (how to calculate height depth)
    public ReturnType process(TreeNode root) {
        if (root == null) {
            return new ReturnType(true, 0);
        }

        ReturnType leftData = process(root.left);
        ReturnType rightData = process(root.right);

        int height = Math.max(leftData.height, rightData.height) + 1;
        boolean isBalanced = leftData.isBalanced && rightData.isBalanced &&
                (Math.abs(leftData.height - rightData.height) < 2);
        return new ReturnType(isBalanced, height);
    }


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

class ReturnType {
    public boolean isBalanced;
    public int height;

    public ReturnType(boolean isBalanced, int height) {
        this.isBalanced = isBalanced;
        this.height = height;
    }
}