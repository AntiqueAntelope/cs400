import java.util.ArrayList;
import java.util.List;

public class BusStopBD implements BusStopInterface {

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "State street @ north park";
    }

    @Override
    public List<BusPathInterface> getOutgoingEdges() {
        // TODO Auto-generated method stub
        List<BusPathInterface> l = new ArrayList<BusPathInterface>();
        l.add(new BusPathBD());
        return l;
    }

    @Override
    public List<BusPathInterface> getIncomingEdges() {
        List<BusPathInterface> l = new ArrayList<BusPathInterface>();
        l.add(new BusPathBD());
        return l;
    }

}
