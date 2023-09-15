import java.util.List;


public interface BusStopInterface {
  // public BusStopInterface(String name, LinkedList<BusPathInterface> outgoingEdges, LinkedList<BusPathInterface> incomingEdges)
  public String getName();


  public List<BusPathInterface> getOutgoingEdges();


 public List<BusPathInterface> getIncomingEdges();


public String toString();
}