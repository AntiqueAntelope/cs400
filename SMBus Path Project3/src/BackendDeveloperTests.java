import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.w3c.dom.ls.LSException;

public class BackendDeveloperTests {

    /*
     * Tests if SMBPBackendBD can be initialized without errors.
     */
    @Test
    public void test1() {
        SMBPBackendBD b = new SMBPBackendBD(new BusRouteReaderBD());
    }

    /*
     * Tests if SMBPBackendBD can insert paths and edges without errosr.
     */
    @Test
    public void test2() {
        SMBPBackendBD backend = new SMBPBackendBD(new BusRouteReaderBD());
        BusStopBD a = new BusStopBD();
        BusStopBD b = new BusStopBD();
        backend.insertStop(a);
        backend.insertStop(b);
        backend.insertStreet(a, b, 5);

    }

    /*
     * Tests if SMBPBackendBD can count number of stops
     */
    @Test
    public void test3() {
        SMBPBackendBD backend = new SMBPBackendBD(new BusRouteReaderBD());
        BusStopBD a = new BusStopBD();
        BusStopBD b = new BusStopBD();
        backend.insertStop(a);
        backend.insertStop(b);
        assertEquals(backend.getNumStops(),2);

    }

    /*
     * Tests if SMBPBackendBD can count cost num of streets
     */
    @Test
    public void test4() {
        SMBPBackendBD backend = new SMBPBackendBD(new BusRouteReaderBD());
        BusStopBD a = new BusStopBD();
        BusStopBD b = new BusStopBD();
        backend.insertStop(a);
        backend.insertStop(b);
        backend.insertStreet(a,b,3);
        assertEquals(backend.getNumStreets(),1);

    }

    /*
     * Tests if SMBPBackendBD can count cost of trivial path.
     */
    @Test
    public void test5() {
        SMBPBackendBD backend = new SMBPBackendBD(new BusRouteReaderBD());
        BusStopBD a = new BusStopBD();
        BusStopBD b = new BusStopBD();
        backend.insertStop(a);
        backend.insertStop(b);
        backend.insertStreet(a,b,3);
        assertEquals((int)backend.shortestBusPathCost(a,b),3);

    }
}
