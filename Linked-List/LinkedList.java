public class LinkedList<T> {
    
    class Node {
        T item;
        Node next;

        public Node() {
        }

        public Node(T item) {
            this.item = item;
        }
    }

    private Node head;
    private Node tail;

    private int size = 0;

    public void push(T item) {
     
        Node newItem = new Node(item);
        if (head == null) {
            head = new Node(item);
            size++;
        } else {
            newItem.next = null;
            Node last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = newItem;
            size++;
        }
    }

    public T get(int i) throws ListIndexOutOfBoundException {
        rangeCheck(i); 
       
        int finding = 0;
        Node last = head;
        while (last != null) {
            if (finding == i) {
                return last.item;
            }
            last = last.next;
            finding++;
        }
        return null;
    }

    public int find(T item) {
      
        Node last = head;
        int index = 0;
        while (last != null) {
            if (last.item.equals(item)) {
                return index;
            }
            last = last.next;
            index++;
        }
        return -1;
    }

    public void insert(int i, T item) throws ListIndexOutOfBoundException {

        if (item == null) {
            throw new ListIndexOutOfBoundException();
        } else if (head == null) {
            head = new Node(item);
            head.next = null;
            size++;
        } else if (i == 0) {
            Node newItem = new Node(item);
            newItem.next = head;
            head = newItem;
            size++;
        } else {
            int finding = 0;
            Node last = head;
            Node newItem = new Node(item);
            while (last != null) {
                if (finding == i - 1) {
                    newItem.next = last.next;
                    last.next = newItem;
                    break;
                }
                last = last.next;
                finding++;
            }
            size++;
        }
    }

    public int size() {
        return size;
    }

    public T delete(int i) throws ListIndexOutOfBoundException, EmptyListException {

        T deletedItem;


        if (head == null) {
            return null;
        } else if (i == 0) {
            deletedItem = head.item;
            head = head.next;
            size--;
            return deletedItem;
        } else {
            int finding = 0;
            Node last = head;
            while (last != null) {
                if (finding == i - 1) {
                    deletedItem = last.next.item;
                    last.next = last.next.next;
                    size--;
                    return deletedItem;
                }
                last = last.next;
                finding++;
            }
        }
        return null;

    }


    public void rangeCheck(int i) throws ListIndexOutOfBoundException {
        if (i < 0 || i >= size)
            throw new ListIndexOutOfBoundException();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node cur = head;
        sb.append("[");
        while (cur != null) {
            if (cur.next == null) {
                sb.append(cur.item.toString());
            } else {
                sb.append(cur.item.toString());
                sb.append(", ");
            }
            cur = cur.next;
        }

        sb.append("]");
        return sb.toString();
    }
}

class ListIndexOutOfBoundException extends Exception {
}

class EmptyListException extends Exception {
}
