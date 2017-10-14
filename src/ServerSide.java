import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * UNIVERSITY OF PERADENIYA 
 * DEPARTMENT OF COMPUTER ENGINEERING
 * 
 * @author Kalana Dhananjaya
 * CO225: Software Construction 
 * Project II - Stack Exchange Server
 */

public class ServerSide extends Thread  {

    /**
     * Runs the server.
     * @throws java.io.IOException
     */
    
    @Override
    public void run(){
        
        
        
        try (ServerSocket listener = new ServerSocket(2000)) {
            int ClientID=0012345;
            while(true){
                new ServerHandler(listener.accept(), ClientID++).start();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}