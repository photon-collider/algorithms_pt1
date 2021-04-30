package week_2;

import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private int backIndex;
    private Item[] items;

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        backIndex = 0;
        items = (Item[]) new Object[0];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    private void resizeArray(int capacity) {
        int j = 0;
        Item[] newItems = (Item[]) new Object[capacity];
        for (int i = 0; i < backIndex; i++) {
            if (items[i] != null) {
                newItems[j] = items[i];
                j++;
            }
        }
        this.items = newItems;
        backIndex = j;
    }

    private void promptResizeArray() {
        if (size > 0 && size == items.length / 4) {
            resizeArray(items.length / 2);
        }

        else if (size > 0 && size == items.length) {
            resizeArray(items.length * 2);
        }
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        } else {
            size++;
            items[backIndex + 1] = item;
            backIndex++;
            promptResizeArray();
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();

        }

        int removeIndex = StdRandom.uniform(backIndex);
        if (items[removeIndex] == null) {
            return dequeue();
        } else {
            Item removeItem = items[removeIndex];
            items[removeIndex] = null;
            size--;

            if (removeIndex == backIndex) {
                backIndex--;
            }

            promptResizeArray();
            return removeItem;
        }

    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }

        int sampleIndex = StdRandom.uniform(backIndex);
        if (items[sampleIndex] == null) {
            return sample();
        } else {
            return items[sampleIndex];
        }
    }

    private class RandomQueueIterator implements Iterator<Item> {
        private Item[] iterItems;
        private int[] randomInds;
        private int currentInd = 0;

        public RandomQueueIterator() {
            iterItems = (Item[]) new Object[size];
            randomInds = new int[size];

            int j = 0;
            for (int i = 0; i < backIndex; i++) {
                if (items[i] != null) {
                    iterItems[j] = items[i];
                    randomInds[j] = j;
                    j++;
                }
            }

            StdRandom.shuffle(randomInds);
        }

        public boolean hasNext() {
            return currentInd != (iterItems.length - 1);
        }

        public void remove() {

        }

        public Item next() {
            int randomIndex = randomInds[currentInd];
            Item item = iterItems[randomIndex];
            currentInd++;
            return item;
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {

    }
}
