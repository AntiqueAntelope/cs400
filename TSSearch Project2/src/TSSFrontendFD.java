import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Frontend class for TSS
 */
public class TSSFrontendFD {

  private Scanner userInput;
  private TSSBackendInterface backend;

  /**
   * Constructor
   * 
   * @param userInput user input
   * @param backend   of the program
   */
  public TSSFrontendFD(Scanner userInput, TSSBackendInterface backend) {
    this.userInput = userInput;
    this.backend = backend;
  }

  /**
   * The loop part of the program that deals with the command selections.
   */
  public void runCommandLoop() {

    System.out.println("Welcome to the Test Score and Student Sorter.\n");
    char command = '\0';
    while (command != 'Q') { // main loop continues until user chooses to quit
      command = this.mainMenuPrompt();
      switch (command) {
        case 'L': // System.out.println(" [L]oad data from file");
          loadDataCommand();
          break;
        case 'S': // System.out.println(" Search Students with [S]core");
          searchNodeCommand();
          break;
        case 'T': // System.out.println(" Search S[T]udent’s Score");
          searchValueCommand();
          break;
        case 'I': // System.out.println(" [I]nsert Student and Score");
          insertNodeCommand();
          break;
        case 'R': // System.out.println(" [R]emove Student");
          removeValueCommand();
          break;
        case 'O': // System.out.println(" Remove Sc[O]re");
          removeNodeCommand();
          break;
        case 'D': // System.out.println(" [D]isplay Statistics for dataset");
          displayStatsCommand();
          break;
        case 'E': // System.out.println(" Display Tr[E]e");
          displayTreeCommand();
          break;
        case 'Q': // System.out.println(" [Q]uit");
          // do nothing, containing loop condition will fail
          break;
        default:
          System.out.println(
              "Didn't recognize that command.  Please type one of the letters presented within []s to identify the command you would like to choose.");
          break;
      }
    }
  }

  /**
   * Main menu prompt of the program
   * 
   * @return s the char that represents the selected command
   */
  public char mainMenuPrompt() {
    // display menu
    System.out.println("Choose a command from the list below:");
    System.out.println("    [L]oad data from file");
    System.out.println("    Search Students with [S]core");
    System.out.println(" Search S[T]udent’s Score");
    System.out.println("    [I]nsert Student and Score");
    System.out.println("    [R]emove Student");
    System.out.println("    Remove Sc[O]re");
    System.out.println("    [D]isplay Statistics for dataset");
    System.out.println("    Display Tr[E]e");
    System.out.println("    [Q]uit");

    System.out.print("Input command key: ");
    String input = userInput.nextLine().trim(); // read user input & trim space
    if (input.length() != 1) {
      return '\0';// return null char if input is not a single character
    }
    return Character.toUpperCase(input.charAt(0)); // return uppercase of the character inputted
  }

  /**
   * Prompt user to enter filename, and display error message when loading fails.
   */
  public void loadDataCommand() {
    System.out.print("Enter the name of the file to load: ");
    String filename = userInput.nextLine().trim(); // format filename
    try {
      backend.loadData(filename);
    } catch (FileNotFoundException e) {
      System.out.println("Error: Could not find or load file: " + filename);
      return; // fail
    }
    System.out.println("Data Loaded Successfully.");
  }

  /**
   * Method that prompts the UI of the searchNode command
   */
  public void searchNodeCommand() {
    double input = -1;
    System.out.print("Enter Score: ");
    try {
      input = userInput.nextDouble(); // read
    } catch (Exception e) {
      input = -1;
    }
    if (input < 0) {
      System.out.println("Invalid input.");
      return; // fail
    } else {
      List<String> students = backend.searchScore(input); // backend
      if (students.size() > 0) {
        System.out.println("Students with the score " + input + " are as follows."); // Found
      } else {// Not Found
        System.out.println("No students has a score of " + input);
      }
      int studentIndex = 1;
      for (String student : students) {
        System.out.println("[" + (studentIndex++) + "] " + student);
      } // Output Format
    }
  }

  /**
   * Method that prompts the UI of the searchValue command
   */
  public void searchValueCommand() {
    String input = userInput.nextLine().trim(); // input format

    double score = -1;
    try {
      score = backend.searchStudent(input);
    } catch (Exception e) {
      System.out.println("Student not found.");
      return; // fail
    }
    System.out.println("Score of " + input + " : " + score);
    // output format
  }

  /**
   * Method that prompts the UI of the insertNode command
   */
  public void insertNodeCommand() {
    String inputNameString;
    double inputScore = -1;
    try {
      System.out.println("Enter Name: ");
      inputNameString = userInput.nextLine().trim(); // input name
    } catch (Exception e) {
      System.out.println("Invalid input.");
      return; // fail
    }
    if (inputNameString.length() == 0) {
      System.out.println("Invalid input.");
      return; // fail
    } else {
      System.out.print("Enter Score: ");
      try {
        inputScore = userInput.nextDouble(); // read score
      } catch (Exception e) {
        inputScore = -1;
      }
      if (inputScore < 0) {
        System.out.println("Invalid input.");
        return; // fail
      }
    }
    try {
      backend.insertPair(inputScore, inputNameString); // backend
    } catch (Exception e) {
      System.out.println("Insertion Failed.");
      return; // fail
    }
    System.out.println("Insertion Successful.");

  }

  /**
   * Method that prompts the UI of the removeNode command
   */
  public void removeNodeCommand() {
    double input = -1;
    System.out.print("Enter Score: ");
    try {
      input = userInput.nextDouble(); // read score
    } catch (Exception e) {
      input = -1;
    }
    if (input < 0) {
      System.out.println("Invalid input.");
      return; // fail
    } else {
      try {
        backend.removeScore(input); // backend
      } catch (NoSuchElementException e) {
        System.out.println("Score Not found.");
        return; // fail
      }
      System.out.println("Remove Successful.");
    }
  }

  /**
   * Method that prompts the UI of the removeValue command
   */
  public void removeValueCommand() {
    String inputNameString;
    double inputScore = -1;
    System.out.println("Do you know the Score of the Student? (Y/N)"); // Check if score is known
    String input;
    input = userInput.nextLine().trim();
    if (input.equals("Y")) { // if known
      System.out.println("Enter name: ");
      inputNameString = userInput.nextLine().trim(); // input name
      if (inputNameString.length() == 0) {
        System.out.println("Invalid input.");
        return; // fail
      } else {
        System.out.print("Enter Score: ");
        try {
          inputScore = userInput.nextDouble(); // read score
        } catch (Exception e) {
          inputScore = -1;
        }
        if (inputScore < 0) {
          System.out.println("Invalid input.");
          return; // fail
        }
      }
      try {
        backend.removeStudentByNameAndGrade(inputScore, inputNameString); // backend
      } catch (NoSuchElementException e) {
        System.out.println("Student not found.");
        return; // fail
      }
      System.out.println("Remove Successful.");
    } else if (input.equals("N")) { // if score not known

      System.out.print("Enter Name: ");

      inputNameString = userInput.nextLine().trim(); // read name

      try {
        backend.removeStudentByName(inputNameString); // backend
      } catch (NoSuchElementException e) {
        System.out.println("Student Not found.");
        return; // fail

      }
      System.out.println("Remove Successful.");
    }

  }

  /**
   * Method that displays stats
   */
  public void displayStatsCommand() {
    System.out.println("Statistics: ");
    System.out.println(backend.displayStatistics());
  }

  /**
   * Method that displays tree
   */
  public void displayTreeCommand() {
    System.out.println("Tree: ");
    System.out.println(backend.displayTree());
  }
}
