import java.io.FileNotFoundException;
import java.util.*;

public interface TSSBackendInterface {

    //public TSSBackendInterface (RedBlackTreeADT<Pair<Integer, List<String>>> testBST, PairReaderInterface PairReader);
    public void loadData (String filename) throws FileNotFoundException;
    public void insertPair (double score, String name);
    public List<String> searchScore (double score);
    public void removeScore (double score) throws NoSuchElementException ;
    public double searchStudent (String name) throws NoSuchElementException;
    public void removeStudentByName (String name) throws NoSuchElementException;
    public String displayStatistics ();
    public String displayTree();
    public void removeStudentByNameAndGrade(double inputScore, String inputNameString);

}
    
