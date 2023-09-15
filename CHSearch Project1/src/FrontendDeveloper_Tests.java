import java.util.*;

public class FrontendDeveloper_Tests {

    /**
     * Testing runCommandLoop() method and quitting
     * @return true if all test cases pass, false if otherwise
     */
    public static boolean test1() { 
        //uppercase, valid commands
        {
            //UI tester
            TextUITester tester = new TextUITester("Q\n"); //UITester before scanner
            //test
            try (Scanner scr = new Scanner(System.in)) {
                CHSearchFrontendFD front = new CHSearchFrontendFD(scr, new CHSearchBackendFD(null, null));
        
                front.runCommandLoop(); //method
                //close UITester
                String output = tester.checkOutput();
                
                String cmdpt = "[L]oad data from file\nSearch Post [T]itles\nSearch Post [B]odies\n"
                    + "Search [P]ost titles and bodies\nDisplay [S]tatistics for dataset\n[Q]uit";

                if (!output.contains(cmdpt)) {
                    return false;
                }
                //check quit message
                if (!output.contains("Quitting")) {
                    return false;
                }
                //should not have invalid inputs
                if (output.contains("Invalid command. Choose again.")) {
                    return false;
                }
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        //lowercase, valid commands
        {
            //UI tester
            TextUITester tester = new TextUITester("q\n"); //UITester before scanner
            //test
            try (Scanner scr = new Scanner(System.in)) {
                CHSearchFrontendFD front = new CHSearchFrontendFD(scr, new CHSearchBackendFD(null, null));
        
                front.runCommandLoop(); //method
                //close UITester
                String output = tester.checkOutput();
                
                String cmdpt = "[L]oad data from file\nSearch Post [T]itles\nSearch Post [B]odies\n"
                    + "Search [P]ost titles and bodies\nDisplay [S]tatistics for dataset\n[Q]uit";

                if (!output.contains(cmdpt)) {
                    return false;
                }
                //check quit message
                if (!output.contains("Quitting")) {
                    return false;
                }
                //should not have invalid inputs
                if (output.contains("Invalid command. Choose again.")) {
                    return false;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        //lowercase, invalid commands included
        {
            //UI tester
            TextUITester tester = new TextUITester("r\nthis is a string\nq\n"); //UITester before scanner
            //test
            try (Scanner scr = new Scanner(System.in)) {
                CHSearchFrontendFD front = new CHSearchFrontendFD(scr, new CHSearchBackendFD(null, null));
        
                front.runCommandLoop(); //method
                //close UITester
                String output = tester.checkOutput();
                
                String cmdpt = "[L]oad data from file\nSearch Post [T]itles\nSearch Post [B]odies\n"
                    + "Search [P]ost titles and bodies\nDisplay [S]tatistics for dataset\n[Q]uit";

                if (!output.contains(cmdpt)) {
                    return false;
                }
                //check quit message
                if (!output.contains("Quitting")) {
                    return false;
                }
                //should have invalid inputs
                if (!output.contains("Invalid command. Choose again.")) {
                    return false;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        
        return true;
    }

    /**
     * Testing loadDataCommand()
     * @return true if all test cases pass, false if otherwise
     */
    public static boolean test2() {
        //uppercase, valid commands
        {
            //UI tester
            TextUITester tester = new TextUITester("L\nfile\nQ\n"); //UITester before scanner
            //test
            try (Scanner scr = new Scanner(System.in)) {
                CHSearchFrontendFD front = new CHSearchFrontendFD(scr, new CHSearchBackendFD(null, null));
        
                front.runCommandLoop(); //method
                //close UITester
                String output = tester.checkOutput();
                
                String cmdpt = "[L]oad data from file\nSearch Post [T]itles\nSearch Post [B]odies\n"
                    + "Search [P]ost titles and bodies\nDisplay [S]tatistics for dataset\n[Q]uit";

                if (!output.contains(cmdpt)) {
                    return false;
                }
                //check load data message
                if (!output.contains("Loading file")) {
                    return false;
                }
                //should not have invalid inputs
                if (output.contains("Invalid command. Choose again.")) {
                    return false;
                }
                

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        
        //lowercase, valid commands
        {
            //UI tester
            TextUITester tester = new TextUITester("l\nfile\nq\n"); //UITester before scanner
            //test
            try (Scanner scr = new Scanner(System.in)) {
                CHSearchFrontendFD front = new CHSearchFrontendFD(scr, new CHSearchBackendFD(null, null));
        
                front.runCommandLoop(); //method
                //close UITester
                String output = tester.checkOutput();
                
                String cmdpt = "[L]oad data from file\nSearch Post [T]itles\nSearch Post [B]odies\n"
                    + "Search [P]ost titles and bodies\nDisplay [S]tatistics for dataset\n[Q]uit";

                if (!output.contains(cmdpt)) {
                    return false;
                }
                //check load data message
                if (!output.contains("Loading file")) {
                    return false;
                }
                //should not have invalid inputs
                if (output.contains("Invalid command. Choose again.")) {
                    return false;
                }
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        
        //lowercase, invalid commands included, testing an invalid file
        {
            //UI tester
            TextUITester tester = new TextUITester("g\nl\nnotavalidfile\nl\nfile\nq\n"); //UITester before scanner
            //test
            
            try (Scanner scr = new Scanner(System.in)) {
                CHSearchFrontendFD front = new CHSearchFrontendFD(scr, new CHSearchBackendFD(null, null));
                
                front.runCommandLoop(); //method
                //close UITester
                
                String output = tester.checkOutput();
                
                String cmdpt = "[L]oad data from file\nSearch Post [T]itles\nSearch Post [B]odies\n"
                    + "Search [P]ost titles and bodies\nDisplay [S]tatistics for dataset\n[Q]uit";
                
                if (!output.contains(cmdpt)) {
                    return false;
                }
                
                //check load data message
                if (!output.contains("Loading file")) {
                    return false;
                }
                //should have invalid inputs
                if (!output.contains("Invalid command. Choose again.")) {
                    return false;
                }
                //check for the file exception
                if (!output.contains("file not found")) {
                    return false;
                }
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        
        return true;
    }

    /**
     * Testing searchTitleCommand()
     * @return true if all test cases pass, false if otherwise
     */
    public static boolean test3() {
        //uppercase, valid commands
        {
            //UI tester
            TextUITester tester = new TextUITester("T\nWORDS\nQ\n"); //UITester before scanner
            //test
            try (Scanner scr = new Scanner(System.in)) {
                CHSearchFrontendFD front = new CHSearchFrontendFD(scr, new CHSearchBackendFD(null, null));

                front.runCommandLoop(); //method
                //close UITester
                String output = tester.checkOutput();
                
                String cmdpt = "[L]oad data from file\nSearch Post [T]itles\nSearch Post [B]odies\n"
                    + "Search [P]ost titles and bodies\nDisplay [S]tatistics for dataset\n[Q]uit";


                if (!output.contains(cmdpt)) {
                    return false;
                }
                //check find title message
                if (!output.contains("findPostsByTitleWords() run")) {
                    return false;
                }
                //should not have invalid inputs
                if (output.contains("Invalid command. Choose again.")) {
                    return false;
                }
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        //lowercase, valid commands
        {
            //UI tester
            TextUITester tester = new TextUITester("t\nwords\nq\n"); //UITester before scanner
            //test
            try (Scanner scr = new Scanner(System.in)) {
                CHSearchFrontendFD front = new CHSearchFrontendFD(scr, new CHSearchBackendFD(null, null));

                front.runCommandLoop(); //method
                //close UITester
                String output = tester.checkOutput();
                
                String cmdpt = "[L]oad data from file\nSearch Post [T]itles\nSearch Post [B]odies\n"
                    + "Search [P]ost titles and bodies\nDisplay [S]tatistics for dataset\n[Q]uit";


                if (!output.contains(cmdpt)) {
                    return false;
                }
                //check find title message
                if (!output.contains("findPostsByTitleWords() run")) {
                    return false;
                }
                //should not have invalid inputs
                if (output.contains("Invalid command. Choose again.")) {
                    return false;
                }
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        //lowercase, invalid commands included
        {
            //UI tester
            TextUITester tester = new TextUITester("a\ny\nthis is a string\nt\nwords\nq\n"); //UITester before scanner
            //test
            try (Scanner scr = new Scanner(System.in)) {
                CHSearchFrontendFD front = new CHSearchFrontendFD(scr, new CHSearchBackendFD(null, null));

                front.runCommandLoop(); //method
                //close UITester
                String output = tester.checkOutput();
                
                String cmdpt = "[L]oad data from file\nSearch Post [T]itles\nSearch Post [B]odies\n"
                    + "Search [P]ost titles and bodies\nDisplay [S]tatistics for dataset\n[Q]uit";


                if (!output.contains(cmdpt)) {
                    return false;
                }
                //check find title message
                if (!output.contains("findPostsByTitleWords() run")) {
                    return false;
                }
                //should have invalid inputs
                if (!output.contains("Invalid command. Choose again.")) {
                    return false;
                }
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        return true;
    }

    /**
     * Testing searchBodyCommand()
     * @return true if all test cases pass, false if otherwise
     */
    public static boolean test4() {
        //uppercase, valid commands
        {
            //UI tester
            TextUITester tester = new TextUITester("B\nWORDS\nQ\n"); //UITester before scanner
            //test
            try (Scanner scr = new Scanner(System.in)) {
                CHSearchFrontendFD front = new CHSearchFrontendFD(scr, new CHSearchBackendFD(null, null));

                front.runCommandLoop(); //method
                //close UITester
                String output = tester.checkOutput();
                
                String cmdpt = "[L]oad data from file\nSearch Post [T]itles\nSearch Post [B]odies\n"
                    + "Search [P]ost titles and bodies\nDisplay [S]tatistics for dataset\n[Q]uit";


                if (!output.contains(cmdpt)) {
                    return false;
                }
                //check find body message
                if (!output.contains("findPostsByBodyWords() run")) {
                    return false;
                }
                //should not have invalid inputs
                if (output.contains("Invalid command. Choose again.")) {
                    return false;
                }
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        //lowercase, valid commands
        {
            //UI tester
            TextUITester tester = new TextUITester("b\nwords\nq\n"); //UITester before scanner
            //test
            try (Scanner scr = new Scanner(System.in)) {
                CHSearchFrontendFD front = new CHSearchFrontendFD(scr, new CHSearchBackendFD(null, null));

                front.runCommandLoop(); //method
                //close UITester
                String output = tester.checkOutput();
                
                String cmdpt = "[L]oad data from file\nSearch Post [T]itles\nSearch Post [B]odies\n"
                    + "Search [P]ost titles and bodies\nDisplay [S]tatistics for dataset\n[Q]uit";


                if (!output.contains(cmdpt)) {
                    return false;
                }
                //check find body message
                if (!output.contains("findPostsByBodyWords() run")) {
                    return false;
                }
                //should not have invalid inputs
                if (output.contains("Invalid command. Choose again.")) {
                    return false;
                }
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        //lowercase, invalid commands included
        {
            //UI tester
            TextUITester tester = new TextUITester("7\nthis is a string\nb\nwords\nq\n"); //UITester before scanner
            //test
            try (Scanner scr = new Scanner(System.in)) {
                CHSearchFrontendFD front = new CHSearchFrontendFD(scr, new CHSearchBackendFD(null, null));

                front.runCommandLoop(); //method
                //close UITester
                String output = tester.checkOutput();
                
                String cmdpt = "[L]oad data from file\nSearch Post [T]itles\nSearch Post [B]odies\n"
                    + "Search [P]ost titles and bodies\nDisplay [S]tatistics for dataset\n[Q]uit";


                if (!output.contains(cmdpt)) {
                    return false;
                }
                //check find body message
                if (!output.contains("findPostsByBodyWords() run")) {
                    return false;
                }
                //should have invalid inputs
                if (!output.contains("Invalid command. Choose again.")) {
                    return false;
                }
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        return true;
    }

    /**
     * Testing searchPostCommand()
     * @return true if all test cases pass, false if otherwise
     */
    public static boolean test5() {
        //uppercase, valid commands
        {
            //UI tester
            TextUITester tester = new TextUITester("P\nWORDS\nQ\n"); //UITester before scanner
            //test
            try (Scanner scr = new Scanner(System.in)) {
                CHSearchFrontendFD front = new CHSearchFrontendFD(scr, new CHSearchBackendFD(null, null));

                front.runCommandLoop(); //method
                //close UITester
                String output = tester.checkOutput();
                
                String cmdpt = "[L]oad data from file\nSearch Post [T]itles\nSearch Post [B]odies\n"
                    + "Search [P]ost titles and bodies\nDisplay [S]tatistics for dataset\n[Q]uit";


                if (!output.contains(cmdpt)) {
                    return false;
                }
                //check find post message
                if (!output.contains("findPostsByTitleOrBodyWords() run")) {
                    return false;
                }
                //should not have invalid inputs
                if (output.contains("Invalid command. Choose again.")) {
                    return false;
                }
            
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        //lowercase, valid commands
        {
            //UI tester
            TextUITester tester = new TextUITester("p\nwords\nq\n"); //UITester before scanner
            //test
            try (Scanner scr = new Scanner(System.in)) {
                CHSearchFrontendFD front = new CHSearchFrontendFD(scr, new CHSearchBackendFD(null, null));

                front.runCommandLoop(); //method
                //close UITester
                String output = tester.checkOutput();
                
                String cmdpt = "[L]oad data from file\nSearch Post [T]itles\nSearch Post [B]odies\n"
                    + "Search [P]ost titles and bodies\nDisplay [S]tatistics for dataset\n[Q]uit";

                if (!output.contains(cmdpt)) {
                    return false;
                }
                //check find post message
                if (!output.contains("findPostsByTitleOrBodyWords() run")) {
                    return false;
                }
                //should not have invalid inputs
                if (output.contains("Invalid command. Choose again.")) {
                    return false;
                }
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        //lowercase, invalid commands included
        {
            //UI tester
            TextUITester tester = new TextUITester("y\nu\nm\nthis is a string\np\nwords\nq\n"); //UITester before scanner
            //test
            try (Scanner scr = new Scanner(System.in)) {
                CHSearchFrontendFD front = new CHSearchFrontendFD(scr, new CHSearchBackendFD(null, null));

                front.runCommandLoop(); //method
                //close UITester
                String output = tester.checkOutput();
                
                String cmdpt = "[L]oad data from file\nSearch Post [T]itles\nSearch Post [B]odies\n"
                    + "Search [P]ost titles and bodies\nDisplay [S]tatistics for dataset\n[Q]uit";


                if (!output.contains(cmdpt)) {
                    return false;
                }
                //check find post message
                if (!output.contains("findPostsByTitleOrBodyWords() run")) {
                    return false;
                }
                //should have invalid inputs
                if (!output.contains("Invalid command. Choose again.")) {
                    return false;
                }
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        return true;
    }

    /**
     * Testing displayStatsCommand()
     * @return true if all test cases pass, false if otherwise
     */
    public static boolean test6() {
        //uppercase, valid commands
        {
            //UI tester
            TextUITester tester = new TextUITester("S\nQ\n"); //UITester before scanner
            //test
            try (Scanner scr = new Scanner(System.in)) {
                CHSearchFrontendFD front = new CHSearchFrontendFD(scr, new CHSearchBackendFD(null, null));

                front.runCommandLoop(); //method
                //close UITester
                String output = tester.checkOutput();
                
                String cmdpt = "[L]oad data from file\nSearch Post [T]itles\nSearch Post [B]odies\n"
                    + "Search [P]ost titles and bodies\nDisplay [S]tatistics for dataset\n[Q]uit";


                if (!output.contains(cmdpt)) {
                    return false;
                }
                //check get statistics message
                if (!output.contains("getStatisticsString() run")) {
                    return false;
                }
                //should not have invalid inputs
                if (output.contains("Invalid command. Choose again.")) {
                    return false;
                }
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        //lowercase, valid commands
        {
            //UI tester
            TextUITester tester = new TextUITester("s\nq\n"); //UITester before scanner
            //test
            try (Scanner scr = new Scanner(System.in)) {
                CHSearchFrontendFD front = new CHSearchFrontendFD(scr, new CHSearchBackendFD(null, null));

                front.runCommandLoop(); //method
                //close UITester
                String output = tester.checkOutput();
                
                String cmdpt = "[L]oad data from file\nSearch Post [T]itles\nSearch Post [B]odies\n"
                    + "Search [P]ost titles and bodies\nDisplay [S]tatistics for dataset\n[Q]uit";

                if (!output.contains(cmdpt)) {
                    return false;
                }
                //check get statistics message
                if (!output.contains("getStatisticsString() run")) {
                    return false;
                }
                //should not have invalid inputs
                if (output.contains("Invalid command. Choose again.")) {
                    return false;
                }
            
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        //lowercase, invalid commands included
        {
            //UI tester
            TextUITester tester = new TextUITester("e\nv\nj\nthis is a string\ns\nq\n"); //UITester before scanner
            //test
            try (Scanner scr = new Scanner(System.in)) {
                CHSearchFrontendFD front = new CHSearchFrontendFD(scr, new CHSearchBackendFD(null, null));

                front.runCommandLoop(); //method
                //close UITester
                String output = tester.checkOutput();
                
                String cmdpt = "[L]oad data from file\nSearch Post [T]itles\nSearch Post [B]odies\n"
                    + "Search [P]ost titles and bodies\nDisplay [S]tatistics for dataset\n[Q]uit";


                if (!output.contains(cmdpt)) {
                    return false;
                }
                //check get statistics message
                if (!output.contains("getStatisticsString() run")) {
                    return false;
                }
                //should have invalid inputs
                if (!output.contains("Invalid command. Choose again.")) {
                    return false;
                }
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        return true;
    }

    /**
     * Testing integration of the CHSearch app to look for posts resulting in no results, one results, and more than one result
     * Testing words with lowercase and uppercase, no matches, punctuation, and repeated words
     * @return true if all tests pass, false other
     */
    public static boolean integrationTest1() {
        //One result - Searching body for posts, only valid lowercase commands
        {
            //UI tester
            TextUITester tester = new TextUITester("L\ndata/small.txt\np\nmoney\nq\n"); //UITester before scanner
            //test
            try (Scanner scr = new Scanner(System.in)) {
                // Use data wrangler's code to load post data
                PostReaderInterface postLoader = new PostReaderDW();
                // Use algorithm engineer's code to store and search for data
                HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable;
                hashtable = new HashtableWithDuplicateKeysAE<>();
                // Use the backend developer's code to manage all app specific processing
                CHSearchBackendInterface backend = new CHSearchBackendBD(hashtable, postLoader);
                // Use the frontend developer's code to drive the text-base user interface
                Scanner scanner = new Scanner(System.in);
                CHSearchFrontendInterface frontend = new CHSearchFrontendFD(scanner, backend);
                frontend.runCommandLoop();

                String output = tester.checkOutput();

                String cmdpt = "[L]oad data from file\nSearch Post [T]itles\nSearch Post [B]odies\n"
                        + "Search [P]ost titles and bodies\nDisplay [S]tatistics for dataset\n[Q]uit";
                
                if (!output.contains(cmdpt)) {
                    return false;
                }
                //this is the only result for "money"
                if (!output.contains("How to save money on rice? - https://www.reddit.com/r/EatCheapAndHealthy/comments/zp7kz8/how_to_save_money_on_rice/")) {
                    return false;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        
        //One result - Searching body for posts, including invalid uppercase commands with punctuation
        {
            //UI tester
            TextUITester tester = new TextUITester("L\ndata/small.txt\nthis is a string\nP\nMONEY!\nq\n"); //UITester before scanner
            //test
            try (Scanner scr = new Scanner(System.in)) {
                // Use data wrangler's code to load post data
                PostReaderInterface postLoader = new PostReaderDW();
                // Use algorithm engineer's code to store and search for data
                HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable;
                hashtable = new HashtableWithDuplicateKeysAE<>();
                // Use the backend developer's code to manage all app specific processing
                CHSearchBackendInterface backend = new CHSearchBackendBD(hashtable, postLoader);
                // Use the frontend developer's code to drive the text-base user interface
                Scanner scanner = new Scanner(System.in);
                CHSearchFrontendInterface frontend = new CHSearchFrontendFD(scanner, backend);
                frontend.runCommandLoop();

                String output = tester.checkOutput();

                String cmdpt = "[L]oad data from file\nSearch Post [T]itles\nSearch Post [B]odies\n"
                        + "Search [P]ost titles and bodies\nDisplay [S]tatistics for dataset\n[Q]uit";
                
                if (!output.contains(cmdpt)) {
                    return false;
                }
                //this is the only result for "money"
                if (!output.contains("[How to save money on rice? - https://www.reddit.com/r/EatCheapAndHealthy/comments/zp7kz8/how_to_save_money_on_rice/]")) {
                    return false;
                }
                if (!output.contains("Invalid command. Try again."));

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        
        //No result - Searching a body with no matches
        {
            //UI tester
            TextUITester tester = new TextUITester("L\ndata/small.txt\np\nnomatches\nq\n"); //UITester before scanner
            //test
            try (Scanner scr = new Scanner(System.in)) {
                // Use data wrangler's code to load post data
                PostReaderInterface postLoader = new PostReaderDW();
                // Use algorithm engineer's code to store and search for data
                HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable;
                hashtable = new HashtableWithDuplicateKeysAE<>();
                // Use the backend developer's code to manage all app specific processing
                CHSearchBackendInterface backend = new CHSearchBackendBD(hashtable, postLoader);
                // Use the frontend developer's code to drive the text-base user interface
                Scanner scanner = new Scanner(System.in);
                CHSearchFrontendInterface frontend = new CHSearchFrontendFD(scanner, backend);
                frontend.runCommandLoop();

                String output = tester.checkOutput();

                String cmdpt = "[L]oad data from file\nSearch Post [T]itles\nSearch Post [B]odies\n"
                        + "Search [P]ost titles and bodies\nDisplay [S]tatistics for dataset\n[Q]uit";
                
                if (!output.contains(cmdpt)) {
                    return false;
                }
                if (!output.contains("No results found.")) {
                    return false;
                }
               
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        //Many results - Searching body for posts, only valid lowercase commands with a repeat keyword
        {
            //UI tester
            TextUITester tester = new TextUITester("L\ndata/small.txt\np\nchicken, chicken\nq\n"); //UITester before scanner
            //test
            try (Scanner scr = new Scanner(System.in)) {
                // Use data wrangler's code to load post data
                PostReaderInterface postLoader = new PostReaderDW();
                // Use algorithm engineer's code to store and search for data
                HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable;
                hashtable = new HashtableWithDuplicateKeysAE<>();
                // Use the backend developer's code to manage all app specific processing
                CHSearchBackendInterface backend = new CHSearchBackendBD(hashtable, postLoader);
                // Use the frontend developer's code to drive the text-base user interface
                Scanner scanner = new Scanner(System.in);
                CHSearchFrontendInterface frontend = new CHSearchFrontendFD(scanner, backend);
                frontend.runCommandLoop();

                String output = tester.checkOutput();

                String cmdpt = "[L]oad data from file\nSearch Post [T]itles\nSearch Post [B]odies\n"
                        + "Search [P]ost titles and bodies\nDisplay [S]tatistics for dataset\n[Q]uit";
                
                if (!output.contains(cmdpt)) {
                    return false;
                }
                //checking if the output contains all of the posts containing chicken
                if (!output.contains("\"What is a good, high protein, easy to make, plant based food to replace chicken with?\" - https://www.reddit.com/r/EatCheapAndHealthy/comments/zp3qhf/what_is_a_good_high_protein_easy_to_make_plant/")) {
                    return false;
                }
                if (!output.contains("Anyone try Aviate Lupini FLAKES? - https://www.reddit.com/r/EatCheapAndHealthy/comments/zohfum/anyone_try_aviate_lupini_flakes/")) {
                    return false;
                }
                if (!output.contains("ORANGE CHICKEN - https://www.reddit.com/r/EatCheapAndHealthy/comments/zpfsvx/orange_chicken/")) {
                    return false;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        return true;
    }

    /**
     * Testing integration of the CHSearch app to display the statistics
     * @return true if all test pass, false otherwise
     */
    public static boolean integrationTest2() {
        //using small.txt for stats
        {
            //UI tester
            TextUITester tester = new TextUITester("L\ndata/small.txt\ns\nq\n"); //UITester before scanner
            //test
            try (Scanner scr = new Scanner(System.in)) {
                // Use data wrangler's code to load post data
                PostReaderInterface postLoader = new PostReaderDW();
                // Use algorithm engineer's code to store and search for data
                HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable;
                hashtable = new HashtableWithDuplicateKeysAE<>();
                // Use the backend developer's code to manage all app specific processing
                CHSearchBackendInterface backend = new CHSearchBackendBD(hashtable, postLoader);
                // Use the frontend developer's code to drive the text-base user interface
                Scanner scanner = new Scanner(System.in);
                CHSearchFrontendInterface frontend = new CHSearchFrontendFD(scanner, backend);
                frontend.runCommandLoop();

                String output = tester.checkOutput();

                String cmdpt = "[L]oad data from file\nSearch Post [T]itles\nSearch Post [B]odies\n"
                        + "Search [P]ost titles and bodies\nDisplay [S]tatistics for dataset\n[Q]uit";
                
                if (!output.contains(cmdpt)) {
                    return false;
                }
                //checking if the corresponding stats are showing up
                if (output.contains("12 posts\n571 unique words\n2933 total word-post pairs")) {
                    return false;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        return true;
    }


    /**
     * Testing the backend method for looking for posts with the keywords
     * @return true if all tests pass, false otherwise
     */
    public static boolean bdTest1() {
        //Searching in posts
        {
            try {
                PostReaderInterface postLoader = new PostReaderDW();
                // Use algorithm engineer's code to store and search for data
                HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable;
                hashtable = new HashtableWithDuplicateKeysAE<>();
                // Use the backend developer's code to manage all app specific processing
                CHSearchBackendInterface backend = new CHSearchBackendBD(hashtable, postLoader);
    
                backend.loadData("data/small.txt");
                //checking if the corresponding posts are showing up
                if (!backend.findPostsByBodyWords("chicken").toString().contains("\"What is a good, high protein, easy to make, plant based food to replace chicken with?\" - https://www.reddit.com/r/EatCheapAndHealthy/comments/zp3qhf/what_is_a_good_high_protein_easy_to_make_plant/")) {
                    return false;
                }
                if (!backend.findPostsByBodyWords("chicken").toString().contains("Anyone try Aviate Lupini FLAKES? - https://www.reddit.com/r/EatCheapAndHealthy/comments/zohfum/anyone_try_aviate_lupini_flakes/")) {
                    return false;
                }
                if (!backend.findPostsByBodyWords("chicken").toString().contains("ORANGE CHICKEN - https://www.reddit.com/r/EatCheapAndHealthy/comments/zpfsvx/orange_chicken/")) {
                    return false;
                }
                
    
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        //Searching in titles
        {
            try {
                PostReaderInterface postLoader = new PostReaderDW();
                // Use algorithm engineer's code to store and search for data
                HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable;
                hashtable = new HashtableWithDuplicateKeysAE<>();
                // Use the backend developer's code to manage all app specific processing
                CHSearchBackendInterface backend = new CHSearchBackendBD(hashtable, postLoader);
    
                backend.loadData("data/small.txt");
                //checking if the corresponding posts are showing up
                if (!backend.findPostsByTitleWords("chicken").toString().contains("\"What is a good, high protein, easy to make, plant based food to replace chicken with?\" - https://www.reddit.com/r/EatCheapAndHealthy/comments/zp3qhf/what_is_a_good_high_protein_easy_to_make_plant/")) {
                    return false;
                }
                if (!backend.findPostsByTitleWords("chicken").toString().contains("ORANGE CHICKEN - https://www.reddit.com/r/EatCheapAndHealthy/comments/zpfsvx/orange_chicken/")) {
                    return false;
                }
                
    
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        
        //Searching in body
        {
            try {
                PostReaderInterface postLoader = new PostReaderDW();
                // Use algorithm engineer's code to store and search for data
                HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable;
                hashtable = new HashtableWithDuplicateKeysAE<>();
                // Use the backend developer's code to manage all app specific processing
                CHSearchBackendInterface backend = new CHSearchBackendBD(hashtable, postLoader);
    
                backend.loadData("data/small.txt");
                //checking if the corresponding posts are showing up
                if (!backend.findPostsByBodyWords("chicken").toString().contains("\"What is a good, high protein, easy to make, plant based food to replace chicken with?\" - https://www.reddit.com/r/EatCheapAndHealthy/comments/zp3qhf/what_is_a_good_high_protein_easy_to_make_plant/")) {
                    return false;
                }
                if (!backend.findPostsByBodyWords("chicken").toString().contains("Anyone try Aviate Lupini FLAKES? - https://www.reddit.com/r/EatCheapAndHealthy/comments/zohfum/anyone_try_aviate_lupini_flakes/")) {
                    return false;
                }
                if (!backend.findPostsByBodyWords("chicken").toString().contains("ORANGE CHICKEN - https://www.reddit.com/r/EatCheapAndHealthy/comments/zpfsvx/orange_chicken/")) {
                    return false;
                }
                
    
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        return true;
    }

    /**
     * Testing the backend method for displaying the statistics
     * @return true if all tests pass, false otherwise
     */
    public static boolean bdTest2() {
        try {
            PostReaderInterface postLoader = new PostReaderDW();
            // Use algorithm engineer's code to store and search for data
            HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable;
            hashtable = new HashtableWithDuplicateKeysAE<>();
            // Use the backend developer's code to manage all app specific processing
            CHSearchBackendInterface backend = new CHSearchBackendBD(hashtable, postLoader);

            backend.loadData("data/small.txt");
           
            if (backend.getStatisticsString().contains("12 posts\n571 unique words\n2933 total word-post pairs")) {
                return false;
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Main method to run all the test cases
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("Frontend Individual Test 1: " + test1());
        System.out.println("Frontend Individual Test 2: " + test2());
        System.out.println("Frontend Individual Test 3: " + test3());
        System.out.println("Frontend Individual Test 4: " + test4());
        System.out.println("Frontend Individual Test 5: " + test5());
        System.out.println("Frontend Individual Test 6: " + test5());

        System.out.println("Frontend Integration Test 1: " + integrationTest1());
        System.out.println("Frontend Integration Test 2: " + integrationTest2());

        System.out.println("Frontend Partner (Backend) Test 1: " + bdTest1());
        System.out.println("Frontend Partner (Backend) Test 2: " + bdTest2());
        

    }
}
