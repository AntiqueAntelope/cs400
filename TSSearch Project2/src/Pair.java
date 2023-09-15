public interface Pair<KeyType, ValueType> {
    // public Pair(KeyType key, ArrayList<ValueType> values);
    // public Pair(KeyType key, ArrayList<ValueType> values, Pair parent, Pair
    // leftChild, Pair rightChild);

    public void setParent(Pair<KeyType, ValueType> parent);

    public void setLeftChild(Pair<KeyType, ValueType> leftChild);

    public void setRightChild(Pair<KeyType, ValueType> rightChild);

    public void setColor(int color);

    public KeyType getKey();

    public ValueType getValue();

    public void addValue(ValueType value);

    public void removeValue(ValueType value);

    public void clearValues();

    public void setKey(KeyType key);
}