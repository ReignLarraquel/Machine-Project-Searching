import java.util.ArrayList;
import java.util.zip.CRC32;

public class HashCRC {
    private ArrayList<Integer> values;
    private ArrayList<String> keys;
    private int collisions;
    private final int coeff;
    private int capacity;
    CRC32 crc = new CRC32();
    private int approaching;
    private int inventory;

    public HashCRC(){
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
    private int getHash(String key){
        crc.reset();
        crc.update(key.getBytes());
        return (int)crc.getValue();
    }
    public String getKeys(int value){
        return keys.get(value);
    }
    public int getIndex(int value){
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