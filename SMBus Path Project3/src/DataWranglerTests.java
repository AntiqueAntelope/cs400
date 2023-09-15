import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.FileNotFoundException;
import java.util.InvalidPropertiesFormatException;
import java.util.LinkedList;
import java.util.List;

/**
 * This is the clas to test the DataWrangler classes including BustPathDW, BusRouteReaderDW, and BusStopDW.
 */
public class DataWranglerTests {
    /**
     * This method is to test if readRouteFromFile(String filename) works properly. This method will check if the data
     * (DOT file) was loaded as intended. And toString() method from BustPathDW works as intended.
     */
    @Test
    public void test1() throws InvalidPropertiesFormatException, FileNotFoundException {
        BusRouteReaderDW test1 = new BusRouteReaderDW();
        List<BusStopInterface> dataSet = test1.readRouteFromFile("BusStopDataFile.dot");
        String expectedVal1 = "Gorham & Ingersoll[BusPathDW{origin=Gorham & Ingersoll, destination=Mifflin & Pinckney," +
                " time=7}][BusPathDW{origin=Fordem & McGuire, destination=Gorham & Ingersoll, time=5}, BusPathDW{origin=" +
                "North & East Washington, destination=Gorham & Ingersoll, time=7}]";
        String expectedVal2 = "Sherman & Sherman Ter[BusPathDW{origin=Sherman & Sherman Ter, destination=North Transfer" +
                " Point, time=5}][BusPathDW{origin=Fordem & McGuire, destination=Sherman & Sherman Ter, time=5}]";
        String expectedVal3 = "Moorland & Rimrock[BusPathDW{origin=Moorland & Rimrock, destination=Broadway & Bridge, " +
                "time=9}][BusPathDW{origin=South Transfer Point, destination=Moorland & Rimrock, time=8}]";
        String storingData = "";
        for (int i = 0; i < dataSet.size(); i++){
            storingData+=dataSet.get(i).toString();
        }
        // checking if the readRouteFromFile works as intended and stores the expected data
        assertTrue(storingData.contains(expectedVal1));
        assertTrue(storingData.contains(expectedVal2));
        assertTrue(storingData.contains(expectedVal3));
    }

    /**
     * This method tests if readRouteFromFile(String filename) throws an exception if there is any
     * unnecessary elements in the dataSet. And checks if the method throws FileNotFoundException if there is no valid file.
     */
    @Test
    public void test2() throws InvalidPropertiesFormatException, FileNotFoundException {
        // creating instance of BusRouteReaderDW
        BusRouteReaderDW test2 = new BusRouteReaderDW();
        // Testing for InvalidPropertiesFormatException e
        try {
            // Testing the file that has an invalid format (subgraph cluster{}) which the method will throw an exception rather than storing it to the arrayList (return value).
            List<BusStopInterface> dataSet = test2.readRouteFromFile("BusStopDataFileTesterVer.dot");
            String storingData = "";
            for (int i = 0; i < dataSet.size(); i++) {
                storingData += dataSet.get(i).toString();
            }
            // trying if an appropriate exception was thrown.
        }catch(InvalidPropertiesFormatException e){
            // Excepted exception message
            String expectedMessage = "File format is invalid";
            assertTrue(e.getMessage().contains(expectedMessage));
        }
        // Testing if the method throws FileNotFoundException if there is no valid file.
        try{
            List<BusStopInterface> dataSet = test2.readRouteFromFile("anyName.dot");
            // trying if FileNotFoundException was thrown.
        }catch(Exception e){
            String expectedMessage = "anyName.dot (No such file or directory)";
            assertTrue(e.getMessage().contains(expectedMessage));
        }
    }
    /**
     * This method is to test if the constructor and the toString method of BusPathDW works as intended.
     */
    @Test
    public void test3()  {
        // Creating instance of BusStopInterface
        BusStopInterface originTest3 = new BusStopDW("BusStop1", new LinkedList<BusPathInterface>(),
                new LinkedList<BusPathInterface>());
        // Creating instance of BusStopInterface
        BusStopInterface destinationTest3 = new BusStopDW("BusStop2", new LinkedList<BusPathInterface>(),
                new LinkedList<BusPathInterface>());
        // Creating example of time that takes to go between originTest3 and destinationTest3
        int timeTest3 = 6;
        // Creating instance of BusPathDW
        BusPathDW test3 = new BusPathDW(originTest3, destinationTest3, timeTest3);
        // This is the expected toString() Value
        String expectedStringVal = "BusPathDW{origin=BusStop1, destination=BusStop2, time=6}";
        // checking for toString() method
        assertEquals(test3.toString(), expectedStringVal);
    }
    /**
     * This method is to test if the getTime(), getOrigin(), and getDestination() of BusPathDW works as intended.
     */
    @Test
    public void test4()  {
        // Creating instance of BusStopInterface
        BusStopInterface originTest4 = new BusStopDW("BusStop1", new LinkedList<BusPathInterface>(),
                new LinkedList<BusPathInterface>());
        // Creating instance of BusStopInterface
        BusStopInterface destinationTest4 = new BusStopDW("BusStop2", new LinkedList<BusPathInterface>(),
                new LinkedList<BusPathInterface>());
        // Creating example of time that takes to go between originTest3 and destinationTest3
        int timeTest3 = 8;
        // Creating instance of BusPathDW
        BusPathDW test4 = new BusPathDW(originTest4, destinationTest4, timeTest3);
        // checking for getTime
        assertEquals(timeTest3, test4.getTime());
        // checking for getOrigin
        assertEquals(originTest4, test4.getOrigin());
        // checking for getDestination
        assertEquals(destinationTest4, test4.getDestination());
    }
    /**
     * This method is to test if the getName(), getOutgoingEdges(), and getIncomingEdges(), and toString() of BusStopDW
     * works as intended.
     */
    @Test
    public void test5()  {
        // creating instance of LinkedList<BusPathInterface> to use it as outgoingEdges
        LinkedList<BusPathInterface> outgoingEdgesOriginTest5 = new LinkedList<>();
        // creating instance of LinkedList<BusPathInterface> to use it as incomingEdges
        LinkedList<BusPathInterface> incomingEdgesOriginTest5 = new LinkedList<>();
        // creating instance of LinkedList<BusPathInterface> to use it as outgoingEdges
        LinkedList<BusPathInterface> outgoingEdgesDestinationTest5 = new LinkedList<>();
        // creating instance of LinkedList<BusPathInterface> to use it as incomingEdges
        LinkedList<BusPathInterface> incomingEdgesDestinationTest5 = new LinkedList<>();
        // creating instance of BusStopDW which represents as origin
        BusStopDW originTest5 = new BusStopDW("BusStop1", outgoingEdgesOriginTest5, incomingEdgesOriginTest5);
        // creating instance of BusStopDW which represents as destination
        BusStopDW destinationTest5 = new BusStopDW("BusStop2", outgoingEdgesDestinationTest5, incomingEdgesDestinationTest5);
        // creating instance of BusPathInterface
        BusPathInterface busPath1 = new BusPathDW(originTest5, destinationTest5, 10);
        BusPathInterface busPath2 = new BusPathDW(destinationTest5, originTest5, 11);
        // adding instance of BusPathInterface to outgoing and incoming edges
        originTest5.getOutgoingEdges().add(busPath1);
        destinationTest5.getIncomingEdges().add(busPath1);
        destinationTest5.getOutgoingEdges().add(busPath2);
        originTest5.getIncomingEdges().add(busPath2);
        // testing getName()
        assertEquals("BusStop1", originTest5.getName());
        assertEquals("BusStop2", destinationTest5.getName());
        // testing getOutgoingEdges() and getIncomingEdges()
        assertTrue(originTest5.getOutgoingEdges().contains(busPath1));
        assertTrue(destinationTest5.getIncomingEdges().contains(busPath1));
        assertTrue(destinationTest5.getOutgoingEdges().contains(busPath2));
        assertTrue(originTest5.getIncomingEdges().contains(busPath2));
        // testing toString()
        String expectedOrigin = "BusStop1[BusPathDW{origin=BusStop1, destination=BusStop2, time=10}]" +
                "[BusPathDW{origin=BusStop2, destination=BusStop1, time=11}]";
        String expectedDestination = "BusStop2[BusPathDW{origin=BusStop2, destination=BusStop1, time=11}]" +
                "[BusPathDW{origin=BusStop1, destination=BusStop2, time=10}]";
        assertEquals(expectedOrigin, originTest5.toString());
        assertEquals(expectedDestination, destinationTest5.toString());
    }
}
