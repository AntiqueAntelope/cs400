import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the SMBPAlgorithmAE class
 */
public class AlgorithmEngineerTests {
    
    /**
     * This junit tests of the Dijkstra's functioning on a graph
     */
    @Test
    public void dijkstraTest() {
        SMBPAlgorithmAE<String, Integer> graph = new SMBPAlgorithmAE<>();

        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("C");
        graph.insertNode("D");
        graph.insertNode("E");
        graph.insertNode("F");
        
        graph.insertEdge("A", "B", 7);
        graph.insertEdge("A", "C", 12);
        graph.insertEdge("B", "C", 2);
        graph.insertEdge("B", "D", 9);
        graph.insertEdge("C", "E", 10);
        graph.insertEdge("D", "F", 1);
        graph.insertEdge("E", "D", 4);
        graph.insertEdge("E", "F", 5);

        assertTrue(graph.shortestPathCost("A", "A") == 0.0);
        assertTrue(graph.shortestPathData("A", "A").equals(Arrays.asList("A")));

        assertTrue(graph.shortestPathCost("A", "B") == 7.0);
        assertTrue(graph.shortestPathData("A", "B").equals(Arrays.asList("A", "B")));

        assertTrue(graph.shortestPathCost("A", "C") == 9.0);
        assertTrue(graph.shortestPathData("A", "C").equals(Arrays.asList("A", "B", "C")));

        assertTrue(graph.shortestPathCost("A", "D") == 16.0);
        assertTrue(graph.shortestPathData("A", "D").equals(Arrays.asList("A", "B", "D")));

        assertTrue(graph.shortestPathCost("A", "D") == 16.0);
        assertTrue(graph.shortestPathData("A", "D").equals(Arrays.asList("A", "B", "D")));

        assertTrue(graph.shortestPathCost("A", "E") == 19.0);
        assertTrue(graph.shortestPathData("A", "E").equals(Arrays.asList("A", "B", "C", "E")));

        assertTrue(graph.shortestPathCost("A", "F") == 17.0);
        assertTrue(graph.shortestPathData("A", "F").equals(Arrays.asList("A", "B", "D", "F")));
    }

    /**
     * This junit tests the Dijkstra algorithm's errors on the graph above
     */
    @Test
    public void dijkstraErrorTest() {
        SMBPAlgorithmAE<String, Integer> graph = new SMBPAlgorithmAE<>();

        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("C");
        graph.insertNode("D");
        graph.insertNode("E");
        graph.insertNode("F");
        
        graph.insertEdge("A", "B", 7);
        graph.insertEdge("A", "C", 12);
        graph.insertEdge("B", "C", 2);
        graph.insertEdge("B", "D", 9);
        graph.insertEdge("C", "E", 10);
        graph.insertEdge("D", "F", 1);
        graph.insertEdge("E", "D", 4);
        graph.insertEdge("E", "F", 5);

        //invalid paths
        try {
            graph.shortestPathCost("C", "A");
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        try {
            graph.shortestPathData("C", "A");
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        try {
            graph.shortestPathCost("D", "B");
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        try {
            graph.shortestPathData("D", "B");
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        //node doesn't exist
        try {
            graph.shortestPathCost("C", "X");
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        try {
            graph.shortestPathData("C", "X");
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        try {
            graph.shortestPathCost("X", "E");
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        try {
            graph.shortestPathData("X", "E");
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        //null node
        try {
            graph.shortestPathCost(null, "A");
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        try {
            graph.shortestPathData(null, "A");
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        try {
            graph.shortestPathCost("C", null);
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        try {
            graph.shortestPathData("C", null);
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
    }

    /**
     * This junit tests if the multi node calculation using Dijkstra's works for two checkpoints is correct
     * This is effectively running Dijkstra's one more time, ensuring the multi calculation works with just two stops
     */
    @Test
    public void smbpSingleMultiTest() {
        SMBPAlgorithmAE<String, Integer> graph = new SMBPAlgorithmAE<>();

        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("C");
        graph.insertNode("D");
        graph.insertNode("E");
        graph.insertNode("F");
        
        graph.insertEdge("A", "B", 7);
        graph.insertEdge("A", "C", 12);
        graph.insertEdge("B", "C", 2);
        graph.insertEdge("B", "D", 9);
        graph.insertEdge("C", "E", 10);
        graph.insertEdge("D", "F", 1);
        graph.insertEdge("E", "D", 4);
        graph.insertEdge("E", "F", 5);

        assertTrue(graph.shortestMultiPathCost(new ArrayList<>(Arrays.asList("A", "A"))) == 0.0);
        assertTrue(graph.shortestMultiPathData(new ArrayList<>(Arrays.asList("A", "A"))).equals(Arrays.asList("A")));

        assertTrue(graph.shortestMultiPathCost(new ArrayList<>(Arrays.asList("A", "B"))) == 7.0);
        assertTrue(graph.shortestMultiPathData(new ArrayList<>(Arrays.asList("A", "B"))).equals(Arrays.asList("A", "B")));

        assertTrue(graph.shortestMultiPathCost(new ArrayList<>(Arrays.asList("A", "C"))) == 9.0);
        assertTrue(graph.shortestMultiPathData(new ArrayList<>(Arrays.asList("A", "C"))).equals(Arrays.asList("A", "B", "C")));

        assertTrue(graph.shortestMultiPathCost(new ArrayList<>(Arrays.asList("A", "D"))) == 16.0);
        assertTrue(graph.shortestMultiPathData(new ArrayList<>(Arrays.asList("A", "D"))).equals(Arrays.asList("A", "B", "D")));

        assertTrue(graph.shortestMultiPathCost(new ArrayList<>(Arrays.asList("A", "E"))) == 19.0);
        assertTrue(graph.shortestMultiPathData(new ArrayList<>(Arrays.asList("A", "E"))).equals(Arrays.asList("A", "B", "C", "E")));

        assertTrue(graph.shortestMultiPathCost(new ArrayList<>(Arrays.asList("A", "F"))) == 17.0);
        assertTrue(graph.shortestMultiPathData(new ArrayList<>(Arrays.asList("A", "F"))).equals(Arrays.asList("A", "B", "D", "F")));
    }

    /**
     * This junit tests the erroring of the multi node calculation with two checkpoints is correct using the graph above
     */
    @Test
    public void smbpSingleMultiErrorTest() {
        SMBPAlgorithmAE<String, Integer> graph = new SMBPAlgorithmAE<>();

        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("C");
        graph.insertNode("D");
        graph.insertNode("E");
        graph.insertNode("F");
        
        graph.insertEdge("A", "B", 7);
        graph.insertEdge("A", "C", 12);
        graph.insertEdge("B", "C", 2);
        graph.insertEdge("B", "D", 9);
        graph.insertEdge("C", "E", 10);
        graph.insertEdge("D", "F", 1);
        graph.insertEdge("E", "D", 4);
        graph.insertEdge("E", "F", 5);

        //invalid paths
        try {
            graph.shortestMultiPathCost(new ArrayList<>(Arrays.asList("C", "A")));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        try {
            graph.shortestMultiPathData(new ArrayList<>(Arrays.asList("C", "A")));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        try {
            graph.shortestMultiPathCost(new ArrayList<>(Arrays.asList("D", "B")));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        try {
            graph.shortestMultiPathData(new ArrayList<>(Arrays.asList("D", "B")));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        //node doesn't exist
        try {
            graph.shortestMultiPathCost(new ArrayList<>(Arrays.asList("C", "X")));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        try {
            graph.shortestMultiPathData(new ArrayList<>(Arrays.asList("C", "X")));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        try {
            graph.shortestMultiPathCost(new ArrayList<>(Arrays.asList("X", "C")));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        try {
            graph.shortestMultiPathData(new ArrayList<>(Arrays.asList("X", "C")));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        //null node
        try {
            graph.shortestMultiPathCost(new ArrayList<>(Arrays.asList(null, "E")));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        try {
            graph.shortestMultiPathData(new ArrayList<>(Arrays.asList(null, "E")));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        try {
            graph.shortestMultiPathCost(new ArrayList<>(Arrays.asList("A", null)));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        try {
            graph.shortestMultiPathData(new ArrayList<>(Arrays.asList("A", null)));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
    }

    /**
     * This junit tests if the multi calculation works correctly with more than two checkpoint nodes using the graph above
     */
    @Test
    public void smbpMultiTest() {
        SMBPAlgorithmAE<String, Integer> graph = new SMBPAlgorithmAE<>();

        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("C");
        graph.insertNode("D");
        graph.insertNode("E");
        graph.insertNode("F");
        
        graph.insertEdge("A", "B", 7);
        graph.insertEdge("A", "C", 12);
        graph.insertEdge("B", "C", 2);
        graph.insertEdge("B", "D", 9);
        graph.insertEdge("C", "E", 10);
        graph.insertEdge("D", "F", 1);
        graph.insertEdge("E", "D", 4);
        graph.insertEdge("E", "F", 5);
        
        //added edges to create a more complex graph
        graph.insertEdge("D", "B", 0);

        graph.insertEdge("F", "E", 5);
        graph.insertEdge("E", "C", 2);
        graph.insertEdge("C", "A", 8);
        graph.insertEdge("E", "B", 12);
        graph.insertEdge("B", "A", 3);
        graph.insertEdge("C", "A", 5);

        //A to E to B
        assertTrue(graph.shortestMultiPathCost(new ArrayList<>(Arrays.asList("A", "E", "B"))) == 23.0);
        assertTrue(graph.shortestMultiPathData(new ArrayList<>(Arrays.asList("A", "E", "B"))).equals(Arrays.asList("A", "B", "C", "E", "D", "B")));

         //A to F to A
         assertTrue(graph.shortestMultiPathCost(new ArrayList<>(Arrays.asList("A", "F", "A"))) == 29.0);
         assertTrue(graph.shortestMultiPathData(new ArrayList<>(Arrays.asList("A", "F", "A"))).equals(Arrays.asList("A", "B", "D", "F", "E", "C", "A")));

        graph.removeEdge("E", "D");

         //D to E to D
         assertTrue(graph.shortestMultiPathCost(new ArrayList<>(Arrays.asList("D", "E", "D"))) == 27.0);
         assertTrue(graph.shortestMultiPathData(new ArrayList<>(Arrays.asList("D", "E", "D"))).equals(Arrays.asList("D", "F", "E", "B", "D")));

         graph.insertEdge("E", "D", 4);

         //A to B to E to A 
         assertTrue(graph.shortestMultiPathCost(new ArrayList<>(Arrays.asList("A", "B", "E", "A"))) == 26.0);
         assertTrue(graph.shortestMultiPathData(new ArrayList<>(Arrays.asList("A", "B", "E", "A"))).equals(Arrays.asList("A", "B", "C", "E", "C", "A")));

         //E to A to F to C
         assertTrue(graph.shortestMultiPathCost(new ArrayList<>(Arrays.asList("E", "A", "F", "C"))) == 31.0);
         assertTrue(graph.shortestMultiPathData(new ArrayList<>(Arrays.asList("E", "A", "F", "C"))).equals(Arrays.asList("E", "C", "A", "B", "D", "F", "E", "C")));

         //F to A to C to E
         assertTrue(graph.shortestMultiPathCost(new ArrayList<>(Arrays.asList("F", "A", "C", "E"))) == 31.0);
         assertTrue(graph.shortestMultiPathData(new ArrayList<>(Arrays.asList("F", "A", "C", "E"))).equals(Arrays.asList("F", "E", "C", "A", "B", "C", "E")));

        //B to E to E to F
        //this path will remove the path E to E as it is to itself
        assertTrue(graph.shortestMultiPathCost(new ArrayList<>(Arrays.asList("B", "E", "E", "F"))) == 17.0);
        assertTrue(graph.shortestMultiPathData(new ArrayList<>(Arrays.asList("B", "E", "E", "F"))).equals(Arrays.asList("B", "C", "E", "F")));
    }

    /**
     * This junit tests the erroring of the SMBPAlgorithm's multi checkpoint calculation is working correctly using the graph agove
     */
    @Test
    public void smbpMultiErrorTest() {
        SMBPAlgorithmAE<String, Integer> graph = new SMBPAlgorithmAE<>();

        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("C");
        graph.insertNode("D");
        graph.insertNode("E");
        graph.insertNode("F");

        //adding a node to test invalid paths
        graph.insertNode("Y");
        
        graph.insertEdge("A", "B", 7);
        graph.insertEdge("A", "C", 12);
        graph.insertEdge("B", "C", 2);
        graph.insertEdge("B", "D", 9);
        graph.insertEdge("C", "E", 10);
        graph.insertEdge("D", "F", 1);
        graph.insertEdge("E", "D", 4);
        graph.insertEdge("E", "F", 5);
        
        //added edges to create a more complex graph
        graph.insertEdge("D", "B", 0);

        graph.insertEdge("F", "E", 5);
        graph.insertEdge("E", "C", 2);
        graph.insertEdge("C", "A", 8);
        graph.insertEdge("E", "B", 12);
        graph.insertEdge("B", "A", 3);
        graph.insertEdge("C", "A", 5);

        //one length list of checkpoints
        try {
            graph.shortestMultiPathCost(new ArrayList<>(Arrays.asList("C")));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        try {
            graph.shortestMultiPathData(new ArrayList<>(Arrays.asList("C")));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        //list of checkpoints that start with an invalid path
        try {
            graph.shortestMultiPathCost(new ArrayList<>(Arrays.asList("X", "C", "E")));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        try {
            graph.shortestMultiPathData(new ArrayList<>(Arrays.asList("X", "C", "E")));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        //list of checkpoints that end with an invalid path
        try {
            graph.shortestMultiPathCost(new ArrayList<>(Arrays.asList("C", "E", "X")));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        try {
            graph.shortestMultiPathData(new ArrayList<>(Arrays.asList("C", "E", "X")));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        //list of checkpints that have a node that does not exist at start, middle, and end
        try {
            graph.shortestMultiPathCost(new ArrayList<>(Arrays.asList("Y", "C", "E")));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        try {
            graph.shortestMultiPathData(new ArrayList<>(Arrays.asList("Y", "C", "E")));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        try {
            graph.shortestMultiPathCost(new ArrayList<>(Arrays.asList("C", "Y", "E")));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        try {
            graph.shortestMultiPathData(new ArrayList<>(Arrays.asList("C", "Y", "E")));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        try {
            graph.shortestMultiPathCost(new ArrayList<>(Arrays.asList("C", "E", "Y")));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        try {
            graph.shortestMultiPathData(new ArrayList<>(Arrays.asList("C", "E", "Y")));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        //list of checkpoints that have a null node at the start, middle and end
        try {
            graph.shortestMultiPathCost(new ArrayList<>(Arrays.asList(null, "C", "E")));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        try {
            graph.shortestMultiPathData(new ArrayList<>(Arrays.asList(null, "C", "E")));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        try {
            graph.shortestMultiPathCost(new ArrayList<>(Arrays.asList("C", null, "E")));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        try {
            graph.shortestMultiPathData(new ArrayList<>(Arrays.asList("C", null, "E")));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        try {
            graph.shortestMultiPathCost(new ArrayList<>(Arrays.asList("C", null, "Y")));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        try {
            graph.shortestMultiPathData(new ArrayList<>(Arrays.asList("C", null, "Y")));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
    }

    /*
     * A key note is that the Frontend fundamentally does not work with the backend due to a mismatch in object types in the
     * Frontend/Backend/AE, as well as the Frontend cutting communications with the team for the past two weeks.
     * There is also a bug with how the DW is loading data and how the BD is inserting the nodes at the time of writing
     * this code. 
     * 
     * This will be explained in detail in the Flip video.
     * Because of this, I will be doing integration testing with only the backend.
     */

    /** 
     * This is a junit integration test of the backend with the algorithm engineer for shortest path of two nodes
     */
    @Test
    public void integrationTest1() {
        BusRouteReaderDW dataWrangler = new BusRouteReaderDW();
        SMBPBackendBD backend = new SMBPBackendBD(dataWrangler);

        BusStopDW one = new BusStopDW("one", new LinkedList<>(), new LinkedList<>());
        BusStopDW two = new BusStopDW("two", new LinkedList<>(), new LinkedList<>());
        
        backend.insertStop(one);
        backend.insertStop(two);
        backend.insertStreet(one, two, new BusPathDW(one, two, 10));

        assertTrue(backend.getNumStops() == 2);
        assertTrue(backend.getNumStreets() == 1);

        assertTrue(backend.shortestBusPathCost(one, two) == 10.0);
        assertTrue(backend.shortestBusPath(one, two).equals(Arrays.asList(one, two)));

        BusStopDW three = new BusStopDW("three", new LinkedList<>(), new LinkedList<>());

        backend.insertStop(three);
        backend.insertStreet(one, three, new BusPathDW(one, three, 5));
        backend.insertStreet(two, three, new BusPathDW(two, three, 5));

        assertTrue(backend.getNumStops() == 3);
        assertTrue(backend.getNumStreets() == 3);

        assertTrue(backend.shortestBusPathCost(one, three) == 5.0);
        assertTrue(backend.shortestBusPath(one, three).equals(Arrays.asList(one, three)));

        backend.removeStreet(one, three);

        assertTrue(backend.getNumStops() == 3);
        assertTrue(backend.getNumStreets() == 2);

        assertTrue(backend.shortestBusPathCost(one, three) == 15.0);
        assertTrue(backend.shortestBusPath(one, three).equals(Arrays.asList(one, two, three)));

        //checking errors
        //nonexisting stop
        try {
            BusStopDW four = new BusStopDW("four", new LinkedList<>(), new LinkedList<>());

            backend.shortestBusPath(one, four);
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        //null
        try {
            backend.shortestBusPath(one, null);
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
    }

    /**
     * This is a junit integration test of the backend with the algorithm engineer to compute the multi shortest path calculation
     */
    @Test
    public void integrationTest2() {
        BusRouteReaderDW dataWrangler = new BusRouteReaderDW();
        SMBPBackendBD backend = new SMBPBackendBD(dataWrangler);

        BusStopDW one = new BusStopDW("one", new LinkedList<>(), new LinkedList<>());
        BusStopDW two = new BusStopDW("two", new LinkedList<>(), new LinkedList<>());
        BusStopDW three = new BusStopDW("three", new LinkedList<>(), new LinkedList<>());
        BusStopDW four = new BusStopDW("four", new LinkedList<>(), new LinkedList<>());

        backend.insertStop(one);
        backend.insertStop(two);
        backend.insertStop(three);
        backend.insertStop(four);

        backend.insertStreet(one, two, new BusPathDW(one, two, 10));
        backend.insertStreet(one, three, new BusPathDW(one, three, 4));
        //making this a one way street
        //backend.insertStreet(one, four, new BusPathDW(one, four, 1));

        backend.insertStreet(two, one, new BusPathDW(two, one, 10));
        backend.insertStreet(two, three, new BusPathDW(two, three, 3));
        backend.insertStreet(two, four, new BusPathDW(two, four, 5));

        backend.insertStreet(three, one, new BusPathDW(three, one, 4));
        backend.insertStreet(three, two, new BusPathDW(three, two, 3));

        backend.insertStreet(four, one, new BusPathDW(four, one, 1));
        backend.insertStreet(four, two, new BusPathDW(four, two, 5));
        

        
        assertTrue(backend.getNumStops() == 4);
        System.out.println(backend.getNumStreets());
        assertTrue(backend.getNumStreets() == 9);

        System.out.println(backend.shortestBusPathCostMultipleStops(Arrays.asList(one, two, four)));
        
        assertTrue(backend.shortestBusPathCostMultipleStops(Arrays.asList(one, two, four)) == 12.0);
        assertTrue(backend.shortestBusPathMultipleStops(Arrays.asList(one, two, four)).equals(Arrays.asList(one, three, two, four)));

        assertTrue(backend.shortestBusPathCostMultipleStops(Arrays.asList(four, three, four, four)) == 13.0);
        assertTrue(backend.shortestBusPathMultipleStops(Arrays.asList(four, three, four, four)).equals(Arrays.asList(four, one, three, two, four)));

        assertTrue(backend.shortestBusPathCostMultipleStops(Arrays.asList(two, three, four, one, four)) == 24.0);
        assertTrue(backend.shortestBusPathMultipleStops(Arrays.asList(two, three, four, one, four)).equals(Arrays.asList(two, three, two, four, one, three, two, four)));

        assertTrue(backend.shortestBusPathCostMultipleStops(Arrays.asList(one, three, four, one, one)) == 13.0);
        assertTrue(backend.shortestBusPathMultipleStops(Arrays.asList(one, three, four, one, one)).equals(Arrays.asList(one, three, two, four, one)));

        //checking errorings
        //nonexisting stop
        try {
            backend.shortestBusPathMultipleStops(Arrays.asList(one));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        //null
        try {
            backend.shortestBusPathMultipleStops(Arrays.asList(one, null, two));
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
    }


    /**
     * This is a code review of the front end developer role. 
     * This junit tests if the inserting of a stop works correctly
     */
    @Test
    public void codeReviewOfFrontendDeveloper1() {
        TextUITester textUI = new TextUITester("n\none\nu\nQ\n");
		
		try(Scanner in = new Scanner(System.in))
		{   
            SMBPBackendBD bd = new SMBPBackendBD(new BusRouteReaderDW());
			SMBPFrontendFD fd = new SMBPFrontendFD(in, bd);
			
			fd.runCommandLoop();
			String output = textUI.checkOutput();
			
			assertTrue(output.contains("Welcome to the Shortest Madison Bus Path Calculator"));
			assertTrue(output.contains("Enter a new stop:"));
            assertTrue(output.contains("Stop \"one\" successfuly added")); //Frontend misspelled successfully
            assertTrue(output.contains("Number of Stops: 1"));
		}
    }

    /**
     * This is a code review of the front end developer role.
     * This junit tests if adding a street works correctly
     */
    @Test
    public void codeReviewOfFrontendDeveloper2() {
        TextUITester textUI = new TextUITester("n\none\nn\ntwo\ns\none\ntwo\n10\nm\nQ\n");
		
		try(Scanner in = new Scanner(System.in))
		{   
            SMBPBackendBD bd = new SMBPBackendBD(new BusRouteReaderDW());
			SMBPFrontendFD fd = new SMBPFrontendFD(in, bd);
			
			fd.runCommandLoop();
			String output = textUI.checkOutput();
			
			assertTrue(output.contains("Welcome to the Shortest Madison Bus Path Calculator"));
			assertTrue(output.contains("Enter a new stop:"));
            assertTrue(output.contains("Stop \"one\" successfuly added")); //Frontend misspelled successfully
            assertTrue(output.contains("Stop \"two\" successfuly added")); //Frontend misspelled successfully
            assertTrue(output.contains("Street already exists!")); //this is the output since Frontend is bugged
		}
    }


    /**
     * Main to debug
     * @param args
     */
    public static void main (String[] args) {
        BusRouteReaderDW dataWrangler = new BusRouteReaderDW();
        SMBPBackendBD backend = new SMBPBackendBD(dataWrangler);

        BusStopDW one = new BusStopDW("one", new LinkedList<>(), new LinkedList<>());
        BusStopDW two = new BusStopDW("two", new LinkedList<>(), new LinkedList<>());
        BusStopDW three = new BusStopDW("three", new LinkedList<>(), new LinkedList<>());
        BusStopDW four = new BusStopDW("four", new LinkedList<>(), new LinkedList<>());

        backend.insertStop(one);
        backend.insertStop(two);
        backend.insertStop(three);
        backend.insertStop(four);

        backend.insertStreet(one, two, new BusPathDW(one, two, 10));
        backend.insertStreet(one, three, new BusPathDW(one, three, 4));
        //making this a one way street
        //backend.insertStreet(one, four, new BusPathDW(one, four, 1));

        backend.insertStreet(two, one, new BusPathDW(two, one, 10));
        backend.insertStreet(two, three, new BusPathDW(two, three, 3));
        backend.insertStreet(two, four, new BusPathDW(two, four, 5));

        backend.insertStreet(three, one, new BusPathDW(three, one, 4));
        backend.insertStreet(three, two, new BusPathDW(three, two, 3));

        backend.insertStreet(four, one, new BusPathDW(four, one, 1));
        backend.insertStreet(four, two, new BusPathDW(four, two, 5));

        System.out.println("Cost: " + backend.shortestBusPathCostMultipleStops(Arrays.asList(one, two, four)));
        System.out.println("Path: " + backend.shortestBusPathMultipleStops(Arrays.asList(one, two, four)));

        backend.removeStop(three);
        System.out.println("Removed three");

        System.out.println("Cost: " + backend.shortestBusPathCostMultipleStops(Arrays.asList(one, two, four)));
        System.out.println("Path: " + backend.shortestBusPathMultipleStops(Arrays.asList(one, two, four)));
    }
}
