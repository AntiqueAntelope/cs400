import java.io.FileNotFoundException;
import java.util.List;

public class SMBPBackendBD implements SMBPBackendInterface {
    private BusRouteReaderInterface r;
    private SMBPAlgorithmAE<BusStopInterface, BusPathDW> d;

    public SMBPBackendBD(BusRouteReaderInterface x) {
        r = x;
        d = new SMBPAlgorithmAE<>();
    }

    @Override
    public void loadData(String filename) throws FileNotFoundException {
        try {
            List<BusStopInterface> l = r.readRouteFromFile(filename);
            for (int i = 0; i < l.size(); i++) {
                d.insertNode(l.get(i));
                d.edgeCount += l.get(i).getIncomingEdges().size();
            }
        } catch(Exception e) {
            throw new FileNotFoundException("File not found.");
        }
    }


    @Override
    public void insertStop(BusStopInterface stop) throws IllegalArgumentException {
        d.insertNode(stop);
    }

    @Override
    public void removeStop(BusStopInterface stop) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        d.removeNode(stop);
    }

    @Override
    public int getNumStops() {
        // TODO Auto-generated method stub
        return d.getNodeCount();
    }

    
    public void insertStreet(BusStopInterface start, BusStopInterface end, BusPathDW weight) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        d.insertEdge(start, end, weight);
    }

    @Override
    public void removeStreet(BusStopInterface start, BusStopInterface end) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        d.removeEdge(start, end);
    }

    @Override
    public int getNumStreets() {
        // TODO Auto-generated method stub
        return d.getEdgeCount();
    }

    @Override
    public List<BusStopInterface> shortestBusPath(BusStopInterface start, BusStopInterface end) {
        // TODO Auto-generated method stub
        return d.shortestPathData(start, end);
    }

    @Override
    public double shortestBusPathCost(BusStopInterface start, BusStopInterface end) {
        // TODO Auto-generated method stub
        return d.shortestPathCost(start, end);
    }

    @Override
    public List<BusStopInterface> shortestBusPathMultipleStops(List<BusStopInterface> stops) {
        // TODO Auto-generated method stub
        return d.shortestMultiPathData(stops);
    }

    @Override
    public double shortestBusPathCostMultipleStops(List<BusStopInterface> stops) {
        // TODO Auto-generated method stub
        return d.shortestMultiPathCost(stops);
    }

    @Override
    public void insertStreet(BusStopInterface start, BusStopInterface end, int weight) throws IllegalArgumentException {
        //changed method
        throw new UnsupportedOperationException("Unimplemented method 'insertStreet'");
    }
    
}
