import java.util.Arrays;

public class SuperArray<T> {
    private Object[] items;
    private int size = 0;
    private static final int INITIAL_SIZE = 3;

    public SuperArray() {

        this.items = new Object[INITIAL_SIZE];
    }

    public void update(int i, T item) {
        rangeCheck(i);

        items[i] = item;
    }

    public T get(int i) {
        rangeCheck(i);

        return element(i);
    }

    public int find(T item) {

        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(item)) {
                return i;
            }
        }
        return -1;

    }

    public int size() {
        return size;
    }

    public int capacity() {
        return items.length;
    }

    public void add(int i, T item) {

        rangeCheck(i);
        if (size >= items.length) {
            items = copyOf(items, items.length + items.length / 2);
        }

        for (int index = items.length - 2; index >= i; index--) {
            items[index + 1] = items[index];
        }
        items[i] = item;
        size++;
    }

    public void push(T item) {

        if (size >= items.length) {
            items = copyOf(items, items.length + items.length / 2);
        }
        items[size] = item;
        size++;
    }

    public Object pop() {

        if (this.size == 0) {
            return null;
        }
        Object item = this.items[size - 1];
        this.size--;
        return item;
    }

    private Object[] copyOf(Object[] a, int newLength) {

        Object Items[] = new Object[newLength];
        for (int i = 0; i < this.size; i++) {
            Items[i] = a[i];
        }
        return Items;
    }

    public void delete(int i) {

        rangeCheck(i);
        for (int j = i; j < size - 1; j++) {
            items[j] = items[j + 1];
        }

    }

    public void delete(T item) {

        int deleteIndex = find(item);
        delete(deleteIndex);

    }

    private void rangeCheck(int i) {
        if (i < 0 || i >= size)
            throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }

    @SuppressWarnings("unchecked")
    private T element(int i) {
        return (T) items[i];
    }
}
