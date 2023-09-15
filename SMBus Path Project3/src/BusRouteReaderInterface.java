import java.io.FileNotFoundException;


import java.util.List;
import java.util.InvalidPropertiesFormatException;


public interface BusRouteReaderInterface {
  // public BusRouteReaderInterface();
  public List<BusStopInterface> readRouteFromFile(String filename) throws FileNotFoundException, InvalidPropertiesFormatException;


}
