import java.util.Arrays;

import javax.naming.ldap.InitialLdapContext;

class DataItem {
    long key;
    String item;

    public DataItem(long key, String item) {
        this.key = key;
        this.item = item;
    }

    @Override
    public String toString() {
        return String.format("{%s:%s}", key, item);
    }
}

public class HashMap {
    private int size = 0;
    private static final int INITIAL_SIZE = 13;
    private static final int DELETED_KEY = 0;
    private DataItem[] items;

    public HashMap() {
        items = new DataItem[INITIAL_SIZE];
    }

    public int size() {
        return size;
    }


    public long hashFunction(String key) {
        
        long hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash += key.charAt(i) * Math.pow(27, i);
        }
        return hash;
    }


    public void put(String key, String value) throws TableIsFullException {
    
        if (size >= items.length) {
            throw new TableIsFullException();
        }
        DataItem tempData = new DataItem(hashFunction(key), value);
 
        long trueIndex = hashFunction(key) % INITIAL_SIZE;
        int index = (int) trueIndex;
        while (items[index] != null && items[index].key != DELETED_KEY) {
            index++;
        }
        items[index] = tempData;
        size++;
    }


    public String get(String key) throws ItemNotFoundException {
     
        long pureIndex = hashFunction(key) % INITIAL_SIZE;
        int index = (int) pureIndex;
        int counter = 0;
        while (items[index] != null) {
            if (counter++ > INITIAL_SIZE) {
                throw new ItemNotFoundException();
            }
            System.out.println(String.valueOf(items[index].key));
            System.out.println(hashFunction(key));
            if (items[index].key == hashFunction(key)) {
                return items[index].item;
            }
            index++;
        }
        throw new ItemNotFoundException();
    }

    public void update(String key, String newValue) throws ItemNotFoundException {
      
        long pureIndex = hashFunction(key) % INITIAL_SIZE;
        int index = (int) pureIndex;
        int counter = 0;
        while (items[index] != null) {
            if (counter++ > INITIAL_SIZE) {
                throw new ItemNotFoundException();
            }
            System.out.println(String.valueOf(items[index].key));
            System.out.println(hashFunction(key));
            if (items[index].key == hashFunction(key)) {
                items[index].item = newValue;
                return;
            }
            index++;
        }
        throw new ItemNotFoundException();
    }

    public String delete(String key) throws ItemNotFoundException {
      
        long pureIndex = hashFunction(key) % INITIAL_SIZE;
        int index = (int) pureIndex;
        int counter = 0;
        while (items[index] != null) {
            if (counter++ > INITIAL_SIZE) {
                throw new ItemNotFoundException();
            }
            System.out.println(String.valueOf(items[index].key));
            System.out.println(hashFunction(key));
            if (items[index].key == hashFunction(key)) {
                items[index].key = DELETED_KEY;
                size--;
                return items[index].item;
            }
            index++;
        }
        throw new ItemNotFoundException();
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }
}

class TableIsFullException extends Exception {
}

class ItemNotFoundException extends Exception {
}
