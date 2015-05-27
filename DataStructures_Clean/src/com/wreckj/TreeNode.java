package com.wreckj;

import java.util.Date;
import java.util.LinkedHashMap;

/**
 * Created by jagsir on 24/04/15.
 */
class TreeNode {
    LinkedHashMap lhm;
    public Object getData() {
        Date date;
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    Object data;

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    TreeNode left;
    TreeNode right;
    TreeNode parent;

    TreeNode(){

    }
    TreeNode(Object data){
        setData(data);
    }

    public String toString() {
        Date date = new Date();

        StringBuffer treeNodeRepr = new StringBuffer();
        String leftRepr = "" + ((getLeft() != null )? getLeft().hashCode():null);
        String rightRepr = "" + ((getRight() != null )? getRight().hashCode():null);
        treeNodeRepr.append("left: ").append(leftRepr).append(", right: ").append(rightRepr).append(", data: ").append(getData());
        return treeNodeRepr.toString();
    }
}
