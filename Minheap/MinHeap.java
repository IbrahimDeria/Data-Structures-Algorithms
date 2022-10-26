public class MinHeap<T extends Comparable<T>> {
    private int HeapSize;
    private int capacity;
    private Item<T>[] HeapArray;
    private final int intitalizeArray = 15;

    public class Item<T> {
        private T obj;
        private double priority;

        public Item(T obj, double priority) {
            this.obj = obj;
            this.priority = priority;
        }

        public double returnPriority() {
            return this.priority;
        }

        public T returnObj() {
            return this.obj;
        }
    }

    public MinHeap() {
        this.capacity = intitalizeArray;
        this.HeapSize = 0;
        HeapArray = new Item[this.capacity];
    }

    public int Parent(int index) {
        return (index - 1) / 2;
    }

    public int leftChild(int index) {
        return 2 * index + 1;
    }

    public int rightChild(int index) {
        return 2 * index + 2;
    }

  
    public void offer(T obj, double priority) {
        Item<T> tempItem = new Item<T>(obj, priority);
        int point = HeapSize;
        HeapArray[point] = tempItem;
        HeapSize++;
        if (HeapSize >= capacity) {
            return;
        }

        while (point != 0 && HeapArray[point].returnPriority() < HeapArray[Parent(point)].returnPriority()) {
            Item<T> hold = HeapArray[point];
            HeapArray[point] = HeapArray[Parent(point)];
            HeapArray[Parent(point)] = hold;
            point = Parent(point);
            System.out.println(point);
        }
    }

  
    public T peek() {
        return HeapArray[0].returnObj();
    }

    private void swap(int fpos, int spos) {

        Item<T> tmp;
        tmp = HeapArray[fpos];

        HeapArray[fpos] = HeapArray[spos];
        HeapArray[spos] = tmp;
    }

    public void sort(int index) {
        int small = index;
        int left = leftChild(index);
        int right = rightChild(index);
        if (HeapSize > left && HeapArray[left].returnPriority() < HeapArray[index].returnPriority()) {
            small = left;
        }
        if (HeapSize > right && HeapArray[right].returnPriority() < HeapArray[small].returnPriority()) {
            small = right;
        }
        if (small != index) {
            swap(index, small);
            sort(small);
        }
    }

   
    public T poll() {
        Item<T> hold = HeapArray[0];
        HeapArray[0] = HeapArray[HeapSize - 1];
        sort(0);
        HeapSize--;
        return hold.returnObj();
    }

    public boolean contains(T obj) {
        for (int i = 0; i <= HeapSize - 1; i++) {
            if (HeapArray[i].returnObj() != null && HeapArray[i].returnObj() == obj) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return HeapSize;
    }
}
