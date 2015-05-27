package com.wreckj;

/**
 * Created by jagsir on 24/04/15.
 */

public class Tree {
    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    TreeNode root = null;
    void preorder(TreeNode root) {
        if (root != null) {
            System.out.println(root);
            preorder(root.left);
            preorder(root.right);
        }
    }
    public static void main(String[] args){
        Tree tree = new Tree();
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        treeNode3.setLeft(treeNode4);
        treeNode1.setLeft(treeNode2);
        treeNode1.setRight(treeNode3);

        tree.setRoot(treeNode1);

        tree.preorder(treeNode1);
    }
}
