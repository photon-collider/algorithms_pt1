import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

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
    public Deque() {
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


        if (size == 0) {
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

        size++;

    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }


        if (size == 0) {
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

        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        } 

        Item frontItem = this.front.item;

        if (size == 1) {
            front = null;
            back = null;
        }
        
        else {
            front = front.next;
            front.prev = null;
        }

        size--;
        return frontItem;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }

 
        Item backItem = this.back.item;

        if (size == 1) {
            this.back = null;
            this.front = null;
        }

        else {
            //System.out.println("Prev Item: " + this.back.prev.item);
            back = back.prev;
            back.next = null;
        }

        size--;
        //System.out.println("Back Item: " + backItem);
        return backItem;
        
    }

    private class QueueIterator implements Iterator<Item> {
        private Node current = front;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if(!hasNext()){
                throw new java.util.NoSuchElementException();
            }
            
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }


    // unit testing (required)
    public static void main(String[] args) {

        Deque<String> q = new Deque<String>();
        System.out.println(q.isEmpty());
        System.out.println(q.size());

        q.addFirst("first");
        q.addFirst("second");
        q.addLast("third");

        for (String s : q) {
            System.out.println(s);
        }

        q.removeLast();
        System.out.println(q.size());
        for (String s : q) {
            System.out.println(s);
        }

        q.removeFirst();
        System.out.println(q.size());
        for (String s : q) {
            System.out.println(s);
        }

        q.removeLast();
        System.out.println(q.size());

        for (String s : q) {
            System.out.println(s);
        }

        q.removeLast();

        for (String s : q) {
            System.out.println(s);
        }
    }
}