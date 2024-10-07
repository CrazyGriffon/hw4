package org.example;

public class TicketManagerImpl implements TicketManager {

    private static final int DEFAULT_CAPACITY = 16;

    private MyPriorityQueue<Ticket> priorityQueue;

    public TicketManagerImpl(int maxQueueLen) {
        priorityQueue = new MyPriorityQueue<>(maxQueueLen);
    }

    public TicketManagerImpl() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void add(Ticket ticket) {
        priorityQueue.enqueue(ticket);
    }

    @Override
    public Ticket next() {
        if (priorityQueue.isEmpty()) {
            return null;
        }
        return priorityQueue.dequeue();
    }
}