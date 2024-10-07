package org.example;

@SuppressWarnings (value="unchecked")
public class MyPriorityQueue<T extends Comparable<? super T>> {
    T[] a;
    int heapSize;

    public MyPriorityQueue(int capacity) {
        a = (T[]) new Comparable[capacity];
        this.heapSize = 0;
    }

    public void enqueue(T elem) {
        if (isFull()) {
            throw new RuntimeException("Priority Queue is full!");
        }
        a[heapSize++] = elem;
        int i = heapSize - 1;
        while (i >= 0 && a[i].compareTo(a[parent(i)]) > 0) {
            T tmp = a[i];
            a[i] = a[parent(i)];
            a[parent(i)] = tmp;
            i = parent(i);
        }
    }

    public boolean isEmpty() {
        return (heapSize == 0);
    }

    public boolean isFull() {
        return (heapSize == a.length);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Priority Queue is empty!");
        }

        T top = a[0];
        a[0] = a[heapSize - 1];
        heapSize--;
        heapify(0);

        return top;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    private void heapify(int i) {
        int l = left(i);
        int r = right(i);

        int largest = i;
        if (l < heapSize && a[l].compareTo(a[largest]) > 0) {
            largest = l;
        }
        if (r < heapSize && a[r].compareTo(a[largest]) > 0) {
            largest = r;
        }

        if (largest != i) {
            T tmp = a[largest];
            a[largest] = a[i];
            a[i] = tmp;
            heapify(largest);
        }
    }
}