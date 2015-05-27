package com.wreckj;

import com.sun.source.tree.BinaryTree;
import com.wreckj.NotSupportedException;
import sun.text.normalizer.CharTrie;
import sun.text.normalizer.Trie;

import java.io.Serializable;


/**
 * Created by jagsir on 23/04/15.
 */

public class LinkedList implements Cloneable, Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8542959801694755841L;
	Node head = null;
    Node tail = null;
    LinkedList(){

    }
    LinkedList(Node head){
        this.head = head;
    }

    LinkedList addHead(Node head) {
        this.head = head;
        return this;
    }
    /** 
     * the most embarrassing mistake.
     * I was progressing from node to tail all the time.
     * 
     * I was getting passed the head all the time.
     * 
     * @param node
     * @return
     */
    Node progressToTail(Node node) {
    	if(node == null){
    		node = (tail==null)? head:tail;
        }
    	Node lastNode = node;
        
        while(lastNode.next != null) {
            lastNode = lastNode.next;
        }
        tail = lastNode;
        return tail;
    }
    LinkedList addAtEnd(Node node){
        Node lastNode = progressToTail(tail);
        lastNode.next = node;
        tail = node;

        return this;
    }

    public String toString(){
        StringBuffer linkedListRepresentation = new StringBuffer();
        Node node = head;
        do {
            linkedListRepresentation.append("\n:").append(node);
            node = node.next;
        } while(node!=null);
        return linkedListRepresentation.toString();
    }
    public LinkedList reverseTheList(){
    	Node temp1 = head;
    	Node list1Head = null;
    	Node temp2 = null;
    	
    	temp1 = head;
    	temp2 = temp1.next;
    	head.next = null;
    	while(temp2 != null) {
    		temp1.next = list1Head;
    		list1Head = temp1;
    		
    		temp1 = temp2;
    		temp2 = temp1.next;
    	}
    	temp1.next = list1Head;
		list1Head = temp1;
		
    	head = list1Head;
		return this;
    }
    
    public Node reverse(Node node, Node prev){
    	Node temp = null;
    	if(node.next==null) {//reached tail.
    		node.next = prev;
    		return node;
    	} else {
    		temp = reverse(node.next, node);
    		node.next = prev;
    		return temp;
    	}
    }
    public int length() {
    	long nanostarttime = System.nanoTime();
    	int size = 1;
    	Node temp = head;
    	while (temp.next != null){
    		++size;
    		temp = temp.next;
    	}
    	long nanoendtime = System.nanoTime();
    	System.err.println("total time for size: " + size + " : " + (nanoendtime - nanostarttime)/10000);
    	return size;
    }
    public LinkedList reverse() {
    	head = this.reverse(head, null);
    	return this;
    }
    public Object clone(){
        //Trie t = new CharTrie('a')
        //BinaryTree
    	return (LinkedList)this.clone();
    }
    public static void main(String args[]) {

        System.out.println("Test Started");
        for (String arg: args){
            System.out.println(arg + " " + arg.hashCode());
        }
    
        LinkedList ll = new LinkedList().addHead(new Node(1,null));
        
        
        
        //System.out.println(ll + "\nsize: " + ll.length());
        
        for(int outer = 0; outer <= 8; outer++) {
        	ll = new LinkedList().addHead(new Node(1,null));
            
        	for(long i = 2; i<= (long)Math.pow(10, outer); i++){
            	ll.addAtEnd(new Node(i,null));
            }
        	System.out.println("size: " + ll.length());
        }
        //ll.reverse();
        //ll.reverseTheList();
        
        int []intarr = new int[100000000];
        for(int i = 0; i < intarr.length; i ++){
        	intarr[i] = i + 1;
        }
 
        int []intarrrev = new int[intarr.length];
        for (int i = 0; i < intarr.length; i++) {
        	intarrrev[intarr.length -1 - i] = intarr[i];
        }

        System.out.println(intarr[0] + " " + intarr[1] + " : " + intarrrev[0] + " " + intarrrev[1]);

        /*LinkedHashMap<Integer, Integer> lhm = new LinkedHashMap<Integer,Integer>();
        for(int i = 2; i<=100000; i++){
        	lhm.put(1,1);
        } 
        java.util.LinkedList<Integer> jll = new java.util.LinkedList<Integer> ();
        for(int i = 2; i<=100000; i++){
        	jll.add(1);
        }*/
        
    }
}
