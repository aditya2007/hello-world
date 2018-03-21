package com.java.core.dsl.linkedlist;

public class DoublyLinkedList<E> {
	private Node<E> head;

	public static void main(String[] args) {
		DoublyLinkedList<Integer> linkedList = new DoublyLinkedList<>();
		linkedList.inserAtHead(2);
		linkedList.inserAtHead(4);
		linkedList.inserAtHead(6);
		linkedList.print();
		linkedList.reversePrint();
	}

	public void inserAtHead(E data) {
		Node newNode = getNewNode(data);
		if (head == null) {
			head = newNode;
		} else {
			head.prev = newNode;
			newNode.next = head;
			head = newNode;
		}
	}

	private Node getNewNode(E data) {
		Node<E> newNode = new Node<>();
		newNode.data = data;
		newNode.next = null;
		newNode.prev = null;
		return newNode;
	}

	private void print() {
		StringBuilder builder = new StringBuilder();
		Node currNode = head;
		while (currNode.next != null) {
			builder.append(currNode.data).append("==>");
			currNode = currNode.next;
		}
		builder.append(currNode.data);
		System.out.println(builder);
	}

	private void reversePrint() {
		StringBuilder builder = new StringBuilder();
		Node currNode = head;
		while(currNode.next != null) {
			currNode = currNode.next;
		}

		while (currNode.prev != null) {
			builder.append(currNode.data).append("<==");
			currNode = currNode.prev;
		}
		builder.append(currNode.data);
		System.out.println(builder);

	}

	class Node<E> {
		E data;
		Node<E> prev = null;
		Node<E> next = null;
	}
}
