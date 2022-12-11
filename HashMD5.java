import java.util.ArrayList;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class HashMD5 {
    private ArrayList<Integer> values;
    private ArrayList<String> keys;
    private int collisions;
    private int coeff;
    private int capacity;
    private int approaching;
    private int inventory;

    public HashMD5(){
        this.coeff = 9;
        this.capacity = 10;
        this.approaching = (int)(capacity * (4 / 5f));
        this.inventory = 0;
        this.values = new ArrayList<>();
        this.keys = new ArrayList<>();
        for(int i = 0; i < capacity; i++) {
            keys.add(null);
            values.add(null);
        }
    }
    public int getCollisions(){
        return collisions;
    }
    public int Probe(int x) {
        return coeff * x;
    }
    public int GCD(int x, int y) {
        if (x == 0) {
            return y;
        }
        while (y != 0) {
            if (x > y) {
                x = x - y;
            }
            else {
                y = y - x;
            }
        }
        return x;
    }

    public void doubleCap(){
        capacity *= 2;
    }

    public void adjustCap(){
        while(GCD(coeff, capacity) != 1) {
            capacity++;
        }
    }
    public void sizeUp(){
        int oldCap = capacity;
        doubleCap();
        adjustCap();
        approaching = (int)(capacity * (4 / 5f));
        ArrayList<String> oldKeys = new ArrayList<>();
        ArrayList<String> oldKeystemp = keys;
        keys = oldKeys;
        oldKeys = oldKeystemp;
        ArrayList<Integer> oldValues = new ArrayList<>();
        ArrayList<Integer> oldValuestemp = values;
        values = oldValues;
        oldValues = oldValuestemp;
        for(int i = 0; i < capacity; i++) {
            keys.add(null);
            values.add(null);
        }
        for(int i = 0; i < capacity; i++) {
            if(i < oldCap) {
                if (oldKeys.get(i) != null) {
                    insert(oldKeys.get(i), oldValues.get(i));
                }
                oldKeys.set(i, null);
                oldValues.set(i, 0);
            }
        }
    }

    public static int getHash(String input)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            // digest() method is called to calculate message digest
            BigInteger bi = new BigInteger(1, md.digest(input.getBytes()));
            return bi.intValue();
        }
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String getKeys(int value){
        return keys.get(value);
    }

    private int getIndex(int value){
        return (value & 0x7FFFFFFF) % capacity;
    }
    public void insert(String key,int value){
        if(inventory >= approaching) {
            sizeUp();
        }
        if(keys.get(getIndex(getHash(key))) != null && keys.get(getIndex(getHash(key))).equals(key)) {
            collisions++;
        }
        for( int i = getIndex(getHash(key)), x = 1;; i = getIndex(getHash(key) + Probe(x++))) {
            if(keys.get(i) == null) {
                values.set(i, value);
                keys.set(i, key);
                inventory++;
                return;
            }
            else if(keys.get(i).equals(key)){
                values.set(i,value);
                return;
            }
        }
    }
    public int search(String key){
        for( int i = getIndex(getHash(key)), x = 1 ; ; i = getIndex(getHash(key) + Probe(x++))) {
            if(keys.get(i) != null) {
                if(keys.get(i).equals(key)){
                    return values.get(i);
                }
            }
            else {
                return -1;
            }
        }
    }
}
