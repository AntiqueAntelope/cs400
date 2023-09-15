// --== CS400 File Header Information ==--
// Name: Neil D'Souza
// Email: ngdsouza@wisc.edu
// Group and Team: DE Red
// Group TA: Callie Kim
// Lecturer: Florian Heimerl

import java.util.PriorityQueue;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class extends the BaseGraph data structure with additional methods for
 * computing the total cost and list of node data along the shortest path
 * connecting a provided starting to ending nodes.  This class makes use of
 * Dijkstra's shortest path algorithm.
 */
public class DijkstraGraphBD<NodeType, EdgeType extends Number>
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
    protected SearchNode computeShortestPath(NodeType start, NodeType end) {
        PriorityQueue<SearchNode> pQueue = new PriorityQueue<SearchNode>();
        Hashtable<Node,SearchNode> alreadyVisited = new Hashtable<Node,SearchNode>();

        SearchNode s = new SearchNode(nodes.get(start),0.0,null);
        while (true) {
            if (s == null) {
                break;
            }
            
            alreadyVisited.put(s.node,s);
            List<Edge> edgesToCheck = s.node.edgesLeaving;
            for (int i = 0; i < edgesToCheck.size(); i++) {
                Edge current = edgesToCheck.get(i);
                EdgeType cost = current.data;
                SearchNode n = new SearchNode(current.successor,cost.doubleValue() + s.cost,s);
                pQueue.add(n);
            }
            do {
                s = pQueue.poll();
            } while ((s != null && alreadyVisited.containsKey(s.node)));
        }
        if (!alreadyVisited.containsKey(nodes.get(end)) || !alreadyVisited.containsKey(nodes.get(start))) {
            throw new NoSuchElementException("Path does not exist.");
        }
        return alreadyVisited.get(nodes.get(end));
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
        try {
            SearchNode endNode = this.computeShortestPath(start, end);
            LinkedList<NodeType> l = new LinkedList<NodeType>();
            
            do {
                l.add(0,endNode.node.data);
                endNode = endNode.predecessor;
            } while(endNode != null);
            return l;
        } catch (Exception e) {
            throw new NoSuchElementException("Path does not exist.");
        }
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
        try {
            SearchNode endNode = this.computeShortestPath(start, end);
            return endNode.cost;
        } catch (Exception e) {
            throw new NoSuchElementException("Path does not exist.");
        }
    }

    public List<NodeType> shortestMultiPathData(List<NodeType> nodes) {
        return null;
    }

    public int shortestMultiPathCost(List<NodeType> nodes) {
        return 0;
    }

    
    public static DijkstraGraphBD<Character,Integer> getTesterGraph() {
        DijkstraGraphBD<Character,Integer> d = new DijkstraGraphBD<Character,Integer>();
        d.insertNode('A');
        d.insertNode('B');
        d.insertNode('C');
        d.insertNode('D');
        d.insertNode('E');
        d.insertNode('F');
        d.insertNode('G');
        d.insertNode('H');

        d.insertEdge('A', 'B', 4);
        d.insertEdge('A', 'C', 2);
        d.insertEdge('A', 'E', 15);

        d.insertEdge('B', 'D', 1);
        d.insertEdge('B', 'E', 10);
        
        d.insertEdge('C', 'D', 5);

        d.insertEdge('D', 'E', 3);
        d.insertEdge('D', 'F', 0);
        

        d.insertEdge('F', 'D', 2);
        d.insertEdge('F', 'H', 4);

        d.insertEdge('G', 'H', 4);
        return d;
    }
    //Tests class example
    @Test
    public void test1() {
        DijkstraGraphBD<Character,Integer> d = getTesterGraph();
        assertEquals((int) d.shortestPathCost('A', 'H'), 9);
        assertEquals(d.shortestPathData('A', 'H').toString(), "[A, B, D, F, H]");
        
    }
    //Tests class example with different start and end.
    @Test
    public void test2() {
        DijkstraGraphBD<Character,Integer> d = getTesterGraph();
        assertEquals((int) d.shortestPathCost('G', 'H'), 4);
        assertEquals(d.shortestPathData('G', 'H').toString(), "[G, H]");
    
    }
    //Tests that exception is thrown when no path exists.
    @Test
    public void test3() {
        DijkstraGraphBD<Character,Integer> d = getTesterGraph();
        try {
            d.shortestPathCost('G', 'A');
            assertEquals(false,true);
        } catch (Exception e) {
            assertEquals(true,true);
        }
    }


}