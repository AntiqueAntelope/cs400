import java.util.LinkedList;
import java.util.List;

/**
 * BusStopDW is a class that represents a bus stop (node) in the program.
 */
public class BusStopDW implements BusStopInterface {
    // this variable represents the name of the node
    private String name;
    // this variable represents the bus stops that is going from origin bus stop
    public LinkedList<BusPathInterface> outgoingEdges;
    // this variable represents the bus stops that is coming from destination bus stop
    public LinkedList<BusPathInterface> incomingEdges;
    /**
     * This is the constructor of class BusStopDW
     * @param name - represents the name of the node
     * @param outgoingEdges - represents the bus stops that is going from origin bus stop
     * @param incomingEdges - represents the bus stops that is coming from destination bus stop
     */
     public BusStopDW(String name, LinkedList<BusPathInterface> outgoingEdges,
                      LinkedList<BusPathInterface> incomingEdges){
        this.name = name;
        this.outgoingEdges = outgoingEdges;
        this.incomingEdges = incomingEdges;
     }
    /**
     * This method allows the user to get access to the field time of this
     * @return name - represents name of the bus stop
     */
    @Override
    public String getName() {
        return this.name;
    }
    /**
     * This method allows the user to get access to the field getOutgoingEdges of this
     * @return getOutgoingEdges - represents the bus stops that is going from origin bus stop
     */
    @Override
    public List<BusPathInterface> getOutgoingEdges() {
        return this.outgoingEdges;
    }
    /**
     * This method allows the user to get access to the field getIncomingEdges of this
     * @return getIncomingEdges - represents the bus stops that is coming from destination bus stop
     */
    @Override
    public List<BusPathInterface> getIncomingEdges() {
        return this.incomingEdges;
    }

    /**
     * This method returns the String that stores the information of the BustStop.
     *
     * @return the information of the node including its name, outgoingEdges, and incomingEdges
     */
    // @Override
    // public String toString(){
    //     return getName() + getOutgoingEdges().toString() + getIncomingEdges().toString();
    // }

    @Override
    public String toString() {
        return name;
    }

}