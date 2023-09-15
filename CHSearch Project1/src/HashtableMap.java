// --== CS400 Project One File Header ==--
// Name: Richard
// CSL Username: richardf
// Email: rtfeng@wisc.edu
// Lecture #: LEC 004, MWF 3:30-4:20
// Notes to Grader: 

import java.util.NoSuchElementException;

/**
 * Class containing the data structure Hashtable Map
 */
public class HashtableMap <KeyType, ValueType> implements MapADT <KeyType, ValueType> {
    
    protected Pair[] array;
    private int size;

    /**
     * Constructor with a desired capacity array
     * @param capacity
     */
    public HashtableMap (int capacity) {
        array = new Pair[capacity];
        fillEmpty();
        size = 0;
    }

    /**
     * Default constructor creates a size 8 array
     */
    public HashtableMap () {
        array = new Pair[8];
        fillEmpty();
        size = 0;
    }

    /**
     * Add a new key-value pair/mapping to this collection
     * Throws exception when key is null or duplicate of one already stored
     * @param key
     * @param value
     * 
     * @throws IllegalArgumentException
     */
    @Override
    public void put(KeyType key, ValueType value) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        if (containsKey(key) == true) {
            throw new IllegalArgumentException("Key exists in the table already");
        }

        Pair pairToInsert = new Pair(key, value);
        int indexToInsert = hash(key);
        
        while (true) {
            //we can insert when the array slot's key is null
            if (array[indexToInsert].getKey() == null) {
                array[indexToInsert] = pairToInsert;
               
                size++;
                break;
            } 
            //the array slot is full
            else {
                //if the next index is out of range, go back to 0
                if (indexToInsert + 1 == array.length) {
                    indexToInsert = 0;
                }
                //else, just move one over
                else {
                    indexToInsert++;
                }
            }
        }

        resize();
    }

    /**
     * Helper function to check if the resize occurs
     */
    private void resize () {
        //if the array is too small, no resize
        if ((double)size / array.length < 0.7) {
            return;
        }

        //array is too big
        Pair[] copyArray = array; //save a copy of the old array
        array = new Pair[array.length * 2]; //resize the array

        //fill the new array with empty Pairs
        fillEmpty();
        
        size = 0; //put() will automatically correct the size

        //rehash the old values from the old array into the new array
        for (int i = 0; i < copyArray.length; i++) {
            //skip over the keys with null
            if (copyArray[i].getKey() != null) {
                this.put((KeyType)copyArray[i].getKey(), (ValueType)copyArray[i].getValue());
            }
        }
    }

    /**
     * Fills the array with null keys
     */
    private void fillEmpty () {
        for (int i = 0; i < array.length; i++) {
            Pair fill = new Pair(null, null);
            array[i] = fill;
        }
    }

    /**
     * Returns the hashed key
     * @param key
     * @return hashed key
     */
    private int hash (KeyType key) {
        return Math.abs(key.hashCode()) % array.length;
    }

    /**
     * Returns the index of the key
     * @param key
     * @return index of the key
     */
    private int findIndex (KeyType key) {
        if (key == null) {
            return -1;
        }
        
        int hashIndex = hash(key);

        if (array[hashIndex].getKey()!= null && array[hashIndex].equals(key)) {
            return hash(key);
        }

        else {
            for (int i = 0; i < array.length; i++) {
                int hashIndexWrapped = (hashIndex + i) % array.length;

                if (array[hashIndexWrapped].getKey() != null && array[hashIndexWrapped].getKey().equals(key)) {
                    return hashIndexWrapped;
                }
            }
        }
        
        return -1;
    }

    /**
     * Check whether a key maps to a value within this collection
     * @param key
     * @return true if table contains key, false otherwise
     */
    @Override
    public boolean containsKey(KeyType key) {
        try {
            //if get() returns a ValueType, that means there is a key associated with the value
            //if get() errors, that means there is no key associated with the value
            get(key);
            
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    
    /**
     * Retrieve the specific value that a key maps to
     * Throws exception when key is not stored in this collection
     * @param key
     * @return value
     * 
     * @throws NoSuchElementException
     */
    @Override
    public ValueType get(KeyType key) throws NoSuchElementException {
        if (findIndex(key) == -1) {
            throw new NoSuchElementException("Key is not found");
        }

        return (ValueType) array[findIndex(key)].getValue(); //return the value at the index of the key
    }

    /**
     * Remove the mapping for a given key from this collection
     * Throws exception when key is not stored in this collection
     * @param key
     * @return value
     * 
     * @throws NoSuchElementException
     */
    @Override
    public ValueType remove(KeyType key) throws NoSuchElementException {
        if (findIndex(key) == -1) {
            throw new NoSuchElementException("Key is not found");
        }

        ValueType result = get(key); //saving the value at the index of the key
        array[findIndex(key)].reset(); //setting the parameters to null

        size--; //update size

        return result;
    }

    /**
     * Remove all key-value pairs from this collection
     */
    @Override
    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i].reset(); //setting all parameters to null
        }
        size = 0;
    }
    
    /**
     * Retrieve the number of keys stored within this collection
     */
    @Override
    public int getSize() {
        return size;
    }

    /** 
     * Retrieve this collection's capacity (size of its underlying array)
     */
    @Override
    public int getCapacity() {
        return array.length;
    }

    /**
     * String representation
     * @returns string representation
     */
    @Override
    public String toString () {
        String str = "";
        for (int i = 0; i < array.length; i++) {
            str += array[i].toString();
        }

        return str;
    }
}
