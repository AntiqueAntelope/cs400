import java.util.*;

public class CHSearchFrontendFD implements CHSearchFrontendInterface{

    private Scanner scr;
    CHSearchBackendInterface backend;
    
    /**
     * Constructor for CHSearchFrontendFD
     * @param userInput
     * @param backend
     */
    public CHSearchFrontendFD(Scanner userInput, CHSearchBackendInterface backend) {
        scr = userInput;
        this.backend = backend;

    }

    /**
     * Loops the mainMenuPrompt() and takes a valid input char and runs the corresponding command
     */
    @Override
    public void runCommandLoop() {
        //continuously loops until 'q', then breaks
        while (true) {
            char input = mainMenuPrompt();
            //l - load data
            if (input == 'l') {
                loadDataCommand();
            }
            //t - search by title
            else if (input == 't') {
                searchTitleCommand(chooseSearchWordsPrompt());
            }
            //b - search by body
            else if (input == 'b') {
                searchBodyCommand(chooseSearchWordsPrompt());
            }
            //p - search by post
            else if (input == 'p') {
                searchPostCommand(chooseSearchWordsPrompt());
            }
            //s - statistics
            else if (input == 's') {
                displayStatsCommand();
            }
            //q - quit
            else if (input == 'q') {
                //exiting message
                System.out.println("Quitting");
                break;
            }
        }
    }

    @Override
    public char mainMenuPrompt() {
        //looping until a valid char is entered
        while (true) {
            System.out.println("[L]oad data from file\nSearch Post [T]itles\nSearch Post [B]odies\n"
            + "Search [P]ost titles and bodies\nDisplay [S]tatistics for dataset\n[Q]uit");

            String str = scr.nextLine().toLowerCase();
            
            //checking if input is a one letter input
            if (str.length() > 1 || str.length() == 0) {
                System.out.println("Invalid option. Choose again.");
            } else {
                //checking if input is a valid char
                if (isValid(str.charAt(0))) {
                    return str.charAt(0);
                }
                else {
                    System.out.println("Invalid command. Choose again.");
                }
            }
        }
    }

    /**
     * Helper for mainMenuPrompt() to check if char c 
     * @param c
     * @return true if valid command, false otherwise
     */
    private boolean isValid (char c) {
        return (c == 'l' || c == 't' || c == 'b' || c == 'p' || c == 's' || c == 'q');
    }

    /**
     * Helper for search commands that require a String for an input when chooseSearchWordsPrompt() return a List<String>
     * @param list
     * @return String representation of list with spaces
     */
    private String listToString (List<String> list) {
        StringBuilder strbul = new StringBuilder();

        for(String str : list)
        {
            //adding the string to the builder
            strbul.append(str);
            //adding a whitespace
            strbul.append(" ");
        }
        
        //turn builder into a string and trim the whitespace added at the last loop
        String str = strbul.toString().trim();
        return str;
    }

    /**
     * Command communicates with backend to load the data
     */
    @Override
    public void loadDataCommand() {
        System.out.println("Type a file to load.");
        String filepath = scr.nextLine().trim();
        
        try {
            backend.loadData(filepath);
            System.out.println("Loading file");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prompts user to enter keywords to search when using a search command
     * @return list of words that the user entered
     */
    @Override
    public List<String> chooseSearchWordsPrompt() {
        List<String> list = new ArrayList<>();

        while (true) {
            System.out.println("Keyword(s) to look for:");
            String str = scr.nextLine().toLowerCase().replaceAll(",", " ").trim();

            //if the string is not empty, then pass through, otherwise, keep looping until a valid string is entered
            if (str.length() != 0) {
                //splitting entered words by spaces and coverting to list for return
                for (String word : str.split(" ")) {
                    //add words
                    list.add(word);
                }
            
                return list;
            } 
        }
    }

    /**
     * Command communicates with backend to search titles with the keywords user entered
     */
    @Override
    public void searchTitleCommand(List<String> words) {
        List<String> str = backend.findPostsByTitleWords(listToString(words));
        if (str.isEmpty()) {
            System.out.println("No results found.");
        } else {
            System.out.println(str);
        }
    }

    /**
     * Command communicates with backend to search bodies with the keywords user entered
     */
    @Override
    public void searchBodyCommand(List<String> words) {
        List<String> str = backend.findPostsByBodyWords(listToString(words));
        if (str.isEmpty()) {
            System.out.println("No results found.");
        } else {
            System.out.println(str);
        }
    }

    /**
     * Command communicates with backend to search posts with the keywords user entered
     */
    @Override
    public void searchPostCommand(List<String> words) {
        List<String> str = backend.findPostsByTitleOrBodyWords(listToString(words));
        if (str.isEmpty()) {
            System.out.println("No results found.");
        } else {
            System.out.println(str);
        }
    }

    /**
     * Command communicates with backend to display statistics
     */
    @Override
    public void displayStatsCommand() {
        //hardcode
        System.out.println(backend.getStatisticsString());
    }
    
}
