import java.util.*;

public interface PairInterface<KeyType, ValueType> extends Comparable<PairInterface<KeyType, ValueType>> {

	//public Pair(KeyType key, ArrayList<ValueType> values);
	
	public KeyType getKey();

	public List<ValueType> getValue(ValueType value);

	public void addValue(ValueType value);

	public void removeValue(ValueType value);

    public void clearValues();

	public void setKey(KeyType Key);

	public int compareTo(PairInterface<KeyType, ValueType> pair);
	public String toString();
}
