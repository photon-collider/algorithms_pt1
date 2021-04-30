public class Deque<Item> implements Iterable<Item> {

    private Node front; 
    private Node back; 
    private int size; 

    private class Node{
        public Item item; 
        public Node next;
        public Node prev; 

        public Node(){
            this.item = null;
            this.next = null;
            this.prev = null;
        }
    }

    // construct an empty deque
    public Deque(){
        this.size = 0;
        this.front = null;
        this.back = null;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return( this.back.next)
    }

    // return the number of items on the deque
    public int size(){
        return size;
    }

    // add the item to the front
    public void addFirst(Item item){
        size++;
        if((this.front == null) && (this.back == null)){
            this.front = new Node(); 
            this.front.item = item;
            this.back = this.front 
        }
        else {
            oldFront = this.front;
            newFront = new Node();
            newFront.item = item;
            newFront.next = oldFront;
            oldFront.prev = newFront;
            this.front = newFront;
        }
    }

    // add the item to the back
    public void addLast(Item item){
        if(item == null){
            throw new IllegalArgumentException();
        }

        size++;
        if((this.front == null) && (this.back == null)){
            this.front = new Node(); 
            this.front.item = item;
            this.back = this.front 
        }
        else {
            oldBack = this.back;
            newBack = new Node();
            newBack.item = item;
            newBack.prev = oldBack;
            oldBack.next = newBack;
            this.back = newBack;
        }    
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if(size == 0){
            throw new java.Util.NoSuchElementException();
        }
        
        if(size < 2){

        }
        size--;

    }

    // remove and return the item from the back
    public Item removeLast(){
        if(size == 0){
            throw new java.Util.NoSuchElementException();
        }

        if(size < 2){

        }
        size--;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){

    }

    // unit testing (required)
    public static void main(String[] args){

    }
}