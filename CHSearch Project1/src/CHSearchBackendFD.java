import java.io.FileNotFoundException;
import java.util.*;

public class CHSearchBackendFD implements CHSearchBackendInterface {
   
    public CHSearchBackendFD(HashtableWithDuplicateKeysInterface<String,PostInterface> hashtable, PostReaderInterface postReader) {

    }

    @Override
    public void loadData(String filename) throws FileNotFoundException {
        //hardcode
        if (filename.equals("file")) {
            return;
        }

        throw new FileNotFoundException("file not found");
    }

    @Override
    public List<String> findPostsByTitleWords(String words) {
        //hardcode
        if (words.equals("words")) {
            ArrayList<String> strs = new ArrayList<String>();
            strs.add(0, "findPostsByTitleWords() run");
            return strs;
        }
        return null;
    }

    @Override
    public List<String> findPostsByBodyWords(String words) {
        //hardcode
        if (words.equals("words")) {
            ArrayList<String> strs = new ArrayList<String>();
            strs.add("findPostsByBodyWords() run");
            return strs;
        }
        return null;
    }

    @Override
    public List<String> findPostsByTitleOrBodyWords(String words) {
        //hardcode
        if (words.equals("words")) {
            ArrayList<String> strs = new ArrayList<String>();
            strs.add("findPostsByTitleOrBodyWords() run");
            return strs;
        }
        return null;
    }

    @Override
    public String getStatisticsString() {
        return "getStatisticsString() run";
    }

}
