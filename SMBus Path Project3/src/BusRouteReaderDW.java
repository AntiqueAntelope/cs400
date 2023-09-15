import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileNotFoundException;
    /**
     * BusRouteReaderDW is a class that provides method that allows the program to
     * read the bus route information from the DOT file and constructs a list
     * of BusStopInterface objects
     */
public class BusRouteReaderDW implements BusRouteReaderInterface{
        /**
         * This method reads the data set (bus routes) from the DOT file and create a list of BusStopInterface objects
         *
         * @param filename the name of the file (DOT format) which includes bus route information.
         * @return A list of BusStopInterface objects that contains the bus stops (nodes) and their connections (edges).
         * @throws FileNotFoundException is thrown if the certain name of the file can't be found.
         * @throws InvalidPropertiesFormatException is thrown if the unnecessary data set which does not follow the define DOT file forma
         */
    @Override
    public List<BusStopInterface> readRouteFromFile(String filename) throws FileNotFoundException, InvalidPropertiesFormatException {
        // Creating an instance of File object
        File file = new File(filename);
        // Create an instance of Scanner object
        Scanner scanner = new Scanner(file);
        // Create a Hashmap to store bus stop names and their BusStopInterface objects
        Map<String, BusStopInterface> busStopsMap = new HashMap<>();
        // Declaring and initializing a regular expression pattern in order to match the lines that contains bus stop edges
        Pattern edgePattern = Pattern.compile("\\s*\\\"(.*?)\\\"\\s*->\\s*\\\"(.*?)\\\"\\s*\\[label\\s*=\\s*\\\"(\\d+)\\\"\\];");
        // Continue the loop while there are more lines in the file
        while (scanner.hasNext()) {
            // reading the next line from the data set
            String line = scanner.nextLine();
            // trying to match the line with the pattern
            Matcher matcher = edgePattern.matcher(line);
            // if the line matches the pattern
            if (matcher.matches()) {
                // defining the data set origin name
                String originName = matcher.group(1);
                // defining the data set destination name
                String destinationName = matcher.group(2);
                // defining the data set time
                int time = Integer.parseInt(matcher.group(3));
                // receive or create the origin. add it to the map if it's not already present
                BusStopInterface origin = busStopsMap.computeIfAbsent(originName, name -> new BusStopDW(name, new LinkedList<>(), new LinkedList<>()));
                // receive or create the destination. and add it to the map if it's not already present
                BusStopInterface destination = busStopsMap.computeIfAbsent(destinationName, name -> new BusStopDW(name, new LinkedList<>(), new LinkedList<>()));
                // create a new instance of BusPathInterface. This helps to understand the relationship between two stops.
                BusPathInterface busPath = new BusPathDW(origin, destination, time);
                // to make sure there is no duplicate busStop exists
                if(!(origin.getOutgoingEdges().stream().anyMatch(existingPath ->
                        existingPath.getOrigin().equals(busPath.getOrigin()) &&
                                existingPath.getDestination().equals(busPath.getDestination())))){
                    // adding the path to the origin's outgoing edges
                    origin.getOutgoingEdges().add(busPath);
                    // adding the path to the destination's incoming edges
                    destination.getIncomingEdges().add(busPath);
                }
                // exception controlling which the information that should not be stored and handled to the other role such as BD
            } else if (!line.trim().isEmpty() && !line.trim().startsWith("//") && !line.trim().startsWith("digraph") && !line.trim().equals("}")) {
                throw new InvalidPropertiesFormatException("File format is invalid");
            }
        }
        // closing scanner
        scanner.close();
        // returning the bust stops as a list
        return new ArrayList<BusStopInterface>(busStopsMap.values());
    }
}
