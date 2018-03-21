package com.java.core.dsl.trees.heap;

import java.util.Arrays;

/**
 * https://www.youtube.com/watch?v=t0Cq6tVNRBA&spfreload=1 - Hackrank
 *
 * Created by Yogananda Gowda - 212590467 on 5/29/17.
 */
public class MinIntHeap {

    private int capacity = 10;
    private int size = 0;

    int[] items = new int[capacity];

    private int getLeftChildIndex(int parentIdx) {
        return 2 * parentIdx + 1;
    }

    private int getRightChildIndex(int parentIdx) {
        return 2 * parentIdx + 2;
    }

    private int getParentIndex(int childIdx) {
        return (childIdx - 1) / 2;
    }

    private boolean hasLeftChild(int idx) {
        return getLeftChildIndex(idx) < size;
    }

    private boolean hasRightChild(int idx) {
        return getRightChildIndex(idx) < size;
    }

    private boolean hasParent(int idx) {
        return getParentIndex(idx) >= 0;
    }

    private int leftChild(int idx) {
        return items[getLeftChildIndex(idx)];
    }

    private int rightChild(int idx) {
        return items[getRightChildIndex(idx)];
    }

    private int parent(int idx) {
        return items[getParentIndex(idx)];
    }

    private void swap(int idx1, int idx2) {
        int tmp = items[idx1];
        items[idx1] = items[idx2];
        items[idx2] = tmp;
    }

    private void ensureExtraCapacity() {
        if( size == capacity ) {
            items = Arrays.copyOf(items, capacity * 2);
            capacity *= 2;
        }
    }

    public int peek() {
        if( size == 0 ) throw new IllegalStateException();
        return  items[0];
    }

    public int poll() {
        if( size == 0) throw new IllegalStateException();
        int item = items[0];
        items[0] = items[size - 1];
        size--;
        heapifyDown();
        return item;
    }

    public void offer(int item) {
        ensureExtraCapacity();
        items[size] = item;
        size++;
        heapifyUp();
    }

    public void heapifyUp() {
        int idx = size - 1;
        while( hasParent(idx) && parent(idx) > items[idx] ) {
            swap(getParentIndex(idx), idx);
            idx = getParentIndex(idx);
        }
    }

    public void heapifyDown() {
        int idx = 0;
        while( hasLeftChild(idx) ) {
            int smallerChildIdx = getLeftChildIndex(idx);
            if( hasRightChild(idx) && rightChild(idx) < leftChild(idx) ){
                smallerChildIdx = getRightChildIndex(idx);
            }

            if ( items[idx] < items[smallerChildIdx]) {
                break;
            } else {
                swap(idx , smallerChildIdx);
            }
            idx = smallerChildIdx;
        }
    }

    public static void main(String[] args) {
        MinIntHeap heap =  new MinIntHeap();
        System.out.println(Arrays.toString(heap.items));

        heap.offer(10);
        heap.offer(15);
        heap.offer(20);
        heap.offer(17);
        heap.offer(25);
        heap.offer(8);

        //heap.poll();
        System.out.println(Arrays.toString(heap.items));
    }


}
