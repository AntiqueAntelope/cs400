/**
 * Hardcoded RBT class
 */
public class RedBlackTreeBD <T extends Comparable<T>, KeyType, ValueType> implements RedBlackTreeADT <T, KeyType, ValueType> {
    int size; //testing purposes to test if adding and removing changes anything in

    /**
     * These methods are all hardcoded for testing reasons
     */

    
    public boolean removeNode(T data) {
        size--;
        return true;
    }

    public boolean removeValue(KeyType key, ValueType value) {
        size--;
        return true;
    }

    public boolean removeNode(KeyType key) {
        size--;
        return true;
    }

    public boolean insert(T Student) {
        size++;
        return true;
    }

    public ValueType findKeyReturnValue (KeyType key) {
        return null;
    }

    public KeyType findValueReturnKey (ValueType value) {
        return null;
    }

    public String toInOrderString() {
        return "toInOrderString()";
    }

    public String toLevelOrderString() {
        return "toLevelOrderString";
    }
    
    public boolean contains(T data) {
        return true;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
       return true;
    }

}
