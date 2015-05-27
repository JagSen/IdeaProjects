package com.wreckj;

import java.io.Serializable;

/**
 * Created by jagsir on 24/04/15.
 */

class Node implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -7219295518201151565L;
    Object data;
    Node next;
    Node(){
        data = null;
        next = null;
    }
    Node(Object data, Node nextNode){
        this.data = data;
        this.next = nextNode;
    }
    Node addBeforeThisNode(Node prevNode) {
        this.next = prevNode.next;
        prevNode.next = this;
        return this;
    }
    Node addAfterThisNode(Node nextNode) throws com.wreckj.NotSupportedException {
        if (this.next != null) {
            throw new com.wreckj.NotSupportedException("next Node not Empty");
        }
        this.next = nextNode;
        return this;
    }
    Node dropNode(Node prevToDropNode, Node dropNode) throws com.wreckj.NotSupportedException {
        if (prevToDropNode.next != dropNode){
            throw new com.wreckj.NotSupportedException("prevToDropNode should point to dropNode");
        } else {
            prevToDropNode.next = dropNode.next;
        }
        dropNode.next = null;
        return this;
    }
    public String toString() {
        StringBuffer nodeRepresentation = new StringBuffer();
        nodeRepresentation.append(hashCode()).append(" : ").append( data).append(" -> ").append(next!=null ? next.hashCode():null);
        return nodeRepresentation.toString();
    }
}