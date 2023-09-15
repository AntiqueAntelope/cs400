import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PairReaderBD<KeyType, ValueType> implements PairReaderInterface<KeyType, ValueType> {

    @Override
    public List<PairInterface<KeyType, ValueType>> readFromFile(String fileName) throws FileNotFoundException {
        if (fileName.equals("fileName")) {
            
            ArrayList<PairInterface<KeyType, ValueType>> list = new ArrayList<PairInterface<KeyType, ValueType>>();
            //hardcode to add a null object to see that the list is non-empty
            list.add(null);
            return list;
        } else {
            throw new FileNotFoundException("File not found");
        }
            
    }
    
}
