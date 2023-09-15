import java.util.Scanner;


public class SMBPApp {

    public static void main (String[] args) {
        
        BusRouteReaderDW dataWrangler = new BusRouteReaderDW();
        SMBPBackendBD backend = new SMBPBackendBD(dataWrangler);
        SMBPFrontendFD frontend = new SMBPFrontendFD(new Scanner(System.in), backend);

        frontend.runCommandLoop();
    }

    
}
