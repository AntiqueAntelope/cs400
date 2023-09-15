// --== CS400 File Header Information ==--
// Name: Richard Feng
// Email: rtfeng@wisc.edu
// Group and Team: DE red
// Group TA: Callie Kim
// Lecturer: Florian
// Notes to Grader: <optional extra notes>

import java.util.PriorityQueue;
import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.*;


import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * This class extends the BaseGraph data structure with additional methods for
 * computing the total cost and list of node data along the shortest path
 * connecting a provided starting to ending nodes.  This class makes use of
 * Dijkstra's shortest path algorithm.
 */
public class DijkstraGraph<NodeType, EdgeType extends Number>
    extends BaseGraph<NodeType,EdgeType>
    implements GraphADT<NodeType, EdgeType> {

    /**
     * While searching for the shortest path between two nodes, a SearchNode
     * contains data about one specific path between the start node and another
     * node in the graph.  The final node in this path is stored in it's node
     * field.  The total cost of this path is stored in its cost field.  And the
     * predecessor SearchNode within this path is referened by the predecessor
     * field (this field is null within the SearchNode containing the starting
     * node in it's node field).
     *
     * SearchNodes are Comparable and are sorted by cost so that the lowest cost
     * SearchNode has the highest priority within a java.util.PriorityQueue.
     */
    protected class SearchNode implements Comparable<SearchNode> {
        public Node node;
        public double cost;
        public SearchNode predecessor;
        public SearchNode(Node node, double cost, SearchNode predecessor) {
            this.node = node;
            this.cost = cost;
            this.predecessor = predecessor;
        }
        public int compareTo(SearchNode other) {
            if( cost > other.cost ) return +1;
            if( cost < other.cost ) return -1;
            return 0;
        }
    }

    /**
     * This helper method creates a network of SearchNodes while computing the
     * shortest path between the provided start and end locations.  The
     * SearchNode that is returned by this method is represents the end of the
     * shortest path that is found: it's cost is the cost of that shortest path,
     * and the nodes linked together through predecessor references represent
     * all of the nodes along that shortest path (ordered from end to start).
     *
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return SearchNode for the final end node within the shortest path
     * @throws NoSuchElementException when no path from start to end is found
     *         or when either start or end data do not correspond to a graph node
     */
    protected SearchNode computeShortestPath(NodeType start, NodeType end) throws NoSuchElementException{
        if (start == null || end == null) {
            throw new NoSuchElementException("Null is not a node");
        } 

        if (!this.containsNode(start) || !this.containsNode(end)) {
            throw new NoSuchElementException("The graph does not contain this node(s)");
        }

        if (start.equals(end)) {
            return new SearchNode(nodes.get(start), 0, null);
        }

        Hashtable<NodeType, Node> visted = new Hashtable<NodeType, Node>();
        PriorityQueue<SearchNode> queue = new PriorityQueue<SearchNode>();
        
        SearchNode startSearch = new SearchNode(nodes.get(start), 0, null);
        queue.add(startSearch);

        //this is our return SearchNode
        SearchNode shortest = null;

        while (!queue.isEmpty()) {
            //remove minimum node
            SearchNode searchNode = queue.poll();
            SearchNode predecessor = searchNode.predecessor;
            double cost = searchNode.cost;

            //looking for a new node to connect and if there is none then the queue is empty and the method exits
            if (!visted.contains(searchNode.node)) {
                visted.put(searchNode.node.data, searchNode.node);
                searchNode.predecessor = predecessor;
                searchNode.cost = cost;
                List<Edge> listOfEdges = searchNode.node.edgesLeaving;

                //for each edge
                for (Edge edge: listOfEdges) {
                    //we don;t want to check edges with a successor in the minimum path already
                    if (!visted.contains(edge.successor)) {
                        //check all the edge successors and update the costs
                        SearchNode tempSearchNode = new SearchNode(edge.successor, cost + edge.data.doubleValue(), searchNode);
                        queue.add(tempSearchNode);

                        //if the successor is the same as the end then the algorithm is over
                        if (tempSearchNode.node.data.equals(end)) {
                            //if the new cost is shorter than the old path, replace new with old
                            if (shortest == null || shortest.cost > tempSearchNode.cost) {
                                shortest = tempSearchNode;
                            }
                        }
                    }
                }
            }
        }
        if (shortest != null && visted.contains(nodes.get(end))) {
            return shortest;
        } else {
            throw new NoSuchElementException("No minimmum path found");
        }

    }

    /**
     * Returns the list of data values from nodes along the shortest path
     * from the node with the provided start value through the node with the
     * provided end value.  This list of data values starts with the start
     * value, ends with the end value, and contains intermediary values in the
     * order they are encountered while traversing this shorteset path.  This
     * method uses Dijkstra's shortest path algorithm to find this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return list of data item from node along this shortest path
     */
    public List<NodeType> shortestPathData(NodeType start, NodeType end) {
        SearchNode shortest = computeShortestPath(start, end);
        List<NodeType> nodes = new LinkedList<>();
        
        //add the end node first
        nodes.add(0, end);

        //while the end doesnt equal the start, we add the last node's predecessor at the front of the list
        while (!end.equals(start)) {
            shortest = computeShortestPath(start, end);

            nodes.add(0, shortest.predecessor.node.data);
            //move the end closer to the start by one node
            end = shortest.predecessor.node.data;
        }
        
        return nodes;
    }

    /**
     * Returns the cost of the path (sum over edge weights) of the shortest
     * path freom the node containing the start data to the node containing the
     * end data.  This method uses Dijkstra's shortest path algorithm to find
     * this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return the cost of the shortest path between these nodes
     */
    public double shortestPathCost(NodeType start, NodeType end) {
        return computeShortestPath(start, end).cost;
    }

    // TODO: implement 3+ tests in step 8.

    /**
     * A junit test testing the graph provided by the lecture using start node A with the calculations done in lecture
     */
    @Test
    public void test1() {

        DijkstraGraph<String, Integer> graph = new DijkstraGraph<>();

        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("C");
        graph.insertNode("D");
        graph.insertNode("E");
        graph.insertNode("F");
        graph.insertNode("G");
        graph.insertNode("H");

        graph.insertEdge("A", "C", 2);
        graph.insertEdge("A", "B", 4);
        graph.insertEdge("A", "E", 15);
        graph.insertEdge("B", "D", 1);
        graph.insertEdge("B", "E", 10);
        graph.insertEdge("C", "D", 5);
        graph.insertEdge("D", "E", 3);
        graph.insertEdge("D", "F", 0);
        graph.insertEdge("F", "D", 2);
        graph.insertEdge("F", "H", 4);
        graph.insertEdge("G", "H", 4);

        //all calculations provided in lecture
        //testing if the cost and data is what we expect
        assertTrue(graph.shortestPathCost("A", "A") == 0.0);
        assertTrue(graph.shortestPathData("A", "A").equals(Arrays.asList("A")));

        assertTrue(graph.shortestPathCost("A", "B") == 4.0);
        assertTrue(graph.shortestPathData("A", "B").equals(Arrays.asList("A", "B")));
        
        assertTrue(graph.shortestPathCost("A", "C") == 2.0);
        assertTrue(graph.shortestPathData("A", "C").equals(Arrays.asList("A", "C")));

        assertTrue(graph.shortestPathCost("A", "D") == 5.0);
        assertTrue(graph.shortestPathData("A", "D").equals(Arrays.asList("A", "B", "D")));

        assertTrue(graph.shortestPathCost("A", "E") == 8.0);
        assertTrue(graph.shortestPathData("A", "E").equals(Arrays.asList("A", "B", "D", "E")));

        assertTrue(graph.shortestPathCost("A", "F") == 5.0);
        assertTrue(graph.shortestPathData("A", "F").equals(Arrays.asList("A", "B", "D", "F")));

        try {
            graph.shortestPathCost("A", "G");
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        try {
            graph.shortestPathData("A", "G");
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        assertTrue(graph.shortestPathCost("A", "H") == 9.0);
        assertTrue(graph.shortestPathData("A", "H").equals(Arrays.asList("A", "B", "D", "F", "H")));
    }

    /**
     * A junit test testing the valid paths in the graph provided by the lecture using start node C
     * This tester works in tandum with test3 which tests the exceptions of using a graph with start node C
     */
    @Test
    public void test2() {

        DijkstraGraph<String, Integer> graph = new DijkstraGraph<>();

        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("C");
        graph.insertNode("D");
        graph.insertNode("E");
        graph.insertNode("F");
        graph.insertNode("G");
        graph.insertNode("H");

        graph.insertEdge("A", "C", 2);
        graph.insertEdge("A", "B", 4);
        graph.insertEdge("A", "E", 15);
        graph.insertEdge("B", "D", 1);
        graph.insertEdge("B", "E", 10);
        graph.insertEdge("C", "D", 5);
        graph.insertEdge("D", "E", 3);
        graph.insertEdge("D", "F", 0);
        graph.insertEdge("F", "D", 2);
        graph.insertEdge("F", "H", 4);
        graph.insertEdge("G", "H", 4);

        //testing if the cost and data is what we expected

        //C -> A will be tested in test3()
        //C -> B will be tested in test3()

        assertTrue(graph.shortestPathCost("C", "C") == 0.0);
        assertTrue(graph.shortestPathData("C", "C").equals(Arrays.asList("C")));

        assertTrue(graph.shortestPathCost("C", "D") == 5.0);
        assertTrue(graph.shortestPathData("C", "D").equals(Arrays.asList("C", "D")));

        assertTrue(graph.shortestPathCost("C", "D") == 5.0);
        assertTrue(graph.shortestPathData("C", "D").equals(Arrays.asList("C", "D")));

        assertTrue(graph.shortestPathCost("C", "E") == 8.0);
        assertTrue(graph.shortestPathData("C", "E").equals(Arrays.asList("C", "D", "E")));

        assertTrue(graph.shortestPathCost("C", "F") == 5.0);
        assertTrue(graph.shortestPathData("C", "F").equals(Arrays.asList("C", "D", "F")));

        //C -> G will be tested in test3()

        assertTrue(graph.shortestPathCost("C", "H") == 9.0);
        assertTrue(graph.shortestPathData("C", "H").equals(Arrays.asList("C", "D", "F", "H")));
    }

    /**
     * A junit test testing the graph used in test2() to check for the exceptions in the graph provided by the lecture using start node C
     * This tester works in tandum with test2 which tests the valid paths of using a graph with start node C
     */
    @Test
    public void test3() {

        DijkstraGraph<String, Integer> graph = new DijkstraGraph<>();

        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("C");
        graph.insertNode("D");
        graph.insertNode("E");
        graph.insertNode("F");
        graph.insertNode("G");
        graph.insertNode("H");

        graph.insertEdge("A", "C", 2);
        graph.insertEdge("A", "B", 4);
        graph.insertEdge("A", "E", 15);
        graph.insertEdge("B", "D", 1);
        graph.insertEdge("B", "E", 10);
        graph.insertEdge("C", "D", 5);
        graph.insertEdge("D", "E", 3);
        graph.insertEdge("D", "F", 0);
        graph.insertEdge("F", "D", 2);
        graph.insertEdge("F", "H", 4);
        graph.insertEdge("G", "H", 4);

        //testing if the exceptions were what we expect

        //C -> A
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

        //C -> B
        try {
            graph.shortestPathCost("C", "B");
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        try {
            graph.shortestPathData("C", "B");
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        
        //C -> G will be tested in test3()
        try {
            graph.shortestPathCost("C", "G");
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        try {
            graph.shortestPathData("C", "G");
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        //also testing for other exceptions
        //null start
        try {
            graph.shortestPathCost(null, "G");
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        try {
            graph.shortestPathData(null, "G");
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        //null end
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

        //non-existent start
        try {
            graph.shortestPathCost("X", "G");
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        try {
            graph.shortestPathData("X", "G");
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        //non-existent end
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
    }
}
