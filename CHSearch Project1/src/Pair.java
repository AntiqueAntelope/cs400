// --== CS400 Project One File Header ==--
// Name: Richard
// CSL Username: richardf
// Email: rtfeng@wisc.edu
// Lecture #: LEC 004, MWF 3:30-4:20
// Notes to Grader: 

/**
 * Pair class to store key and value in one object, maintain a 1D array for the hash table
 */
public class Pair <KeyType, ValueType> {
    
    private KeyType key;
    private ValueType value;
    
    /**
     * Constructor
     * @param key
     * @param value
     */
    public Pair (KeyType key, ValueType value) {
        
        this.key = key;
        this.value = value;
    }

    /**
     * Sets parameters to null, used when removing a key
     * Adds the sentinel tag
     */
    public void kill () {
        key = null;
        value = null;
    }

    /**
     * Resets all parameters back to default states
     */
    public void reset () {
        key = null;
        value = null;
    }

    /**
     * Getter for key
     * @return key
     */
    public KeyType getKey () {
        return key;
    }

    /**
     * Getter for value
     * @return value
     */
    public ValueType getValue () {
        return value;
    }

    @Override
    public String toString () {
        if (key == null) {
            return "() ";
        }
        else

        return "(" + key + "," + value + ") ";
    }

}
