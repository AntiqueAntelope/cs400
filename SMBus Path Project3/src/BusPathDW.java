/**
 * BusPathDW is a class that provides constructor that contains relationship between two bus stops
 * (origin and destination) and the time it takes to travel between them. It also provides the users
 * to have access to the fields: origin, destination, and time.
 */
public class BusPathDW extends Number implements BusPathInterface {
    // this variable represents the origin busStop
    private BusStopInterface origin;
    // this variable represents the destination busStop
    private BusStopInterface destination;
    // this variable represents the time that takes from origin to destination
    private int time;

    /**
     * This is the constructor for the BusPathDW. It initializes origin, destination and time.
     * @param origin - represents origin busStop
     * @param destination - represents destination busStop
     * @param time - represents the time that takes from origin to destination
     */
     public BusPathDW(BusStopInterface origin, BusStopInterface
     destination, int time){
        this.origin = origin;
        this.destination = destination;
        this.time = time;
     }

    /**
     * This method allows the user to get access to the field time of this
     * @return time - represents the time that takes from origin to destination
     */
    @Override
    public int getTime() {
        return this.time;
    }
    /**
     * This method allows the user to get access to the field origin of this
     * @return origin -
     */
    @Override
    public BusStopInterface getOrigin() {
        return this.origin;
    }
    /**
     * This method allows the user to get access to the field destination of this
     * @return destination - represents the destination busStop
     */
    @Override
    public BusStopInterface getDestination() {
        return this.destination;
    }

    /**
     * This method returns the String that stores the information of the BustStops.
     *
     * @return the information of the node including its origin, destination, and time.
     */
    @Override
    public String toString() {
        return "BusPathDW{" +
                "origin=" + origin.getName() +
                ", destination=" + destination.getName() +
                ", time=" + time +
                '}';
    }

    @Override
    public int intValue() {
        return time;
    }

    @Override
    public long longValue() {
        return time;
    }

    @Override
    public float floatValue() {
        return time;
    }

    @Override
    public double doubleValue() {
        return time;
    }
}
