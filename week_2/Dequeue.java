package week_2;

import java.util.Iterator;

public class Dequeue<Item> implements Iterable<Item> {

    private Node front;
    private Node back;
    private int size;

    private class Node {
        public Item item;
        public Node next;
        public Node prev;

        public Node() {
            this.item = null;
            this.next = null;
            this.prev = null;
        }
    }

    // construct an empty deque
    public Dequeue() {
        this.size = 0;
        this.front = null;
        this.back = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (this.front == null);
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        size++;

        if (this.back == null) {
            this.front = new Node();
            this.front.item = item;
            this.back = this.front;
        } else {
            Node oldFront = this.front;
            Node newFront = new Node();
            newFront.item = item;
            newFront.next = oldFront;
            oldFront.prev = newFront;
            this.front = newFront;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        size++;

        if (this.back == null) {
            this.front = new Node();
            this.front.item = item;
            this.back = this.front;
        } else {
            Node oldBack = this.back;
            Node newBack = new Node();
            newBack.item = item;
            newBack.prev = oldBack;
            oldBack.next = newBack;
            this.back = newBack;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        } else {
            Item frontItem = this.front.item;

            this.front = this.front.next;

            if (size == 1) {
                this.front = null;
                this.back = null;
            }
            size--;
            return frontItem;
        }

    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }

        else {
            Item backItem = this.back.item;

            this.back = this.back.prev;

            if (size == 1) {
                this.back = null;
                this.front = null;
            }

            size--;
            return backItem;
        }
    }

    private class QueueIterator implements Iterator<Item> {
        private Node current = front;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {

        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    public void printArr() {
        for (Item s : this) {
            System.out.println(s);
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

        Dequeue<String> q = new Dequeue<String>();
        System.out.println(q.isEmpty());
        System.out.println(q.size());

        q.addFirst("first");
        q.addFirst("second");
        q.addLast("third");

        q.printArr();

        q.removeLast();
        System.out.println(q.size());
        q.printArr();

        q.removeLast();
        System.out.println(q.size());
        q.printArr();

        q.removeLast();
        System.out.println(q.size());
        q.printArr();

        // q.removeLast();
    }
}