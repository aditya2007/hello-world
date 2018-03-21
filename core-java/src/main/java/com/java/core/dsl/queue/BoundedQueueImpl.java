package com.java.core.dsl.queue;

import java.util.concurrent.ArrayBlockingQueue;

public class BoundedQueueImpl<E> {
	
	private E[] items;
	private int count;
	private int putIdx;
	private int takeIdx;
	private int capacity;
	
	public BoundedQueueImpl(int capacity) {
		this.capacity = capacity;
		this.items = (E[])new Object[capacity];
	}
	
	public boolean enqueue(E item) {
		if( item == null ) throw new NullPointerException();
		if( isFull() ) return false;
		
		int tmpIdx = putIdx;
		items[putIdx] = item;
		putIdx = inc(putIdx);
		System.out.println(item + " is Queued @ Index " + tmpIdx + ", Next Queuing @ Index : " + putIdx);
		++count;
		return true;
	}
	
	public E dequeue() {
		if( count == 0 ) return null;
		System.out.println(" Dequeued @ Index : " + takeIdx);
		E item = items[takeIdx];
		items[takeIdx] = null;
		takeIdx = inc(takeIdx);
		--count;
		
		return item;
	}
	
	public int size() {
		return count;
	}
	
	public boolean isEmpty() {
		return count == 0;
	}
	
	public boolean isFull() {
		return count == capacity;
	}
	
	
	private int inc(int idx) {
		return ++idx == items.length ? 0 : idx;
	}
	
	public static void main(String[] args) {
		BoundedQueueImpl<String> queue = new BoundedQueueImpl<String>(10);
		queue.testUnBoundArrayQueue(queue);
		//queue.testArrayBlockingQueue();
	}
	
	private void testUnBoundArrayQueue(BoundedQueueImpl<String> queue) {
		System.out.println("Is Queue Empty @1 ?? " + queue.isEmpty() );
		System.out.println("Is Queue Full @1 ?? " + queue.isFull() );
		
		queue.enqueue("Task1");
		queue.enqueue("Task2");
		queue.enqueue("Task3");
		queue.enqueue("Task4");
		queue.enqueue("Task5");
		queue.enqueue("Task6");
		queue.enqueue("Task7");
		queue.enqueue("Task8");
		queue.enqueue("Task9");
		queue.enqueue("Task10");
		
		System.out.println("Is Queue Empty @2 ?? " + queue.isEmpty() );
		System.out.println("Is Queue Full @2 ?? " + queue.isFull() );
		
		boolean isInserted = queue.enqueue("Task11");
		System.out.println("Is 11th item got inserted @1>>>> " + isInserted);
		
		String item = queue.dequeue();
		System.out.println("Dequeued item : " + item);
		isInserted = queue.enqueue("Task12");
		System.out.println("Is 12th item got inserted @2>>>> " + isInserted);
	}
	private void testArrayBlockingQueue() {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
		System.out.println("Is Queue Empty @1 ?? " + queue.isEmpty() );
		System.out.println("Remaining Capacity @1 ?? " + queue.remainingCapacity() );
		
		queue.offer("Task1");
		queue.offer("Task2");
		queue.offer("Task3");
		queue.offer("Task4");
		queue.offer("Task5");
		queue.offer("Task6");
		queue.offer("Task7");
		queue.offer("Task8");
		queue.offer("Task9");
		queue.offer("Task10");
		
		System.out.println("Is Queue Empty @2 ?? " + queue.isEmpty() );
		System.out.println("Remaining Capacity @2 ?? " + queue.remainingCapacity() );
		
		boolean isInserted = queue.offer("Task11");
		System.out.println("Is 11th item got inserted @1>>>> " + isInserted);
		
		String item = queue.poll();
		System.out.println("Dequeued item : " + item);
		isInserted = queue.offer("Task12");
		System.out.println("Is 12th item got inserted @2>>>> " + isInserted);

	}
}
