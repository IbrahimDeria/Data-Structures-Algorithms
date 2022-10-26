import java.util.Arrays;

public class SuperArray {
    private Account[] accounts;
    private int size = 0;

    public SuperArray(int size) {
        this.accounts = new Account[size];
    }

    public void update(int i, Account account) {
        rangeCheck(i);
        accounts[i] = account;

    }

    public Account get(int i) {
        rangeCheck(i);
        return accounts[i];
    }

    public int find(Account account) {

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].equals(account)) {
                return i;
            }
        }
        return -1;

    }

    public int size() {
        return size;
    }

    public void add(Account ac) {
        if (size >= accounts.length) {
            throw new ListIsFullException();
        }

        accounts[size] = ac;

        size++;
    }

    public void delete(int i) {

        rangeCheck(i);

        for (; i < size - 1; i++) {
            accounts[i] = accounts[i + 1];
        }

        // TODO: After the loop, set the last cell's value to null and decrement size
        size--;

    }

    public void delete(Account ac) {

        int deleteIndex = find(ac);
        delete(deleteIndex);

    }

    private void rangeCheck(int i) {
        if (i < 0 || i >= size)
            throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public String toString() {
        return Arrays.toString(accounts);
    }
}

class ListIsFullException extends RuntimeException {
}
