
import java.util.*;

/**
 * UNIVERSITY OF PERADENIYA 
 * DEPARTMENT OF COMPUTER ENGINEERING
 * 
 * @author Kalana Dhananjaya
 * Group 11 E/14/021 E/14/287
 * CO225: Software Construction 
 * Project II - Stack Exchange Server
 */

public class ClientDetailsBook {
    
    public static Map <String, Integer> Client =new HashMap<>();
    public static Map <String, Boolean> ClientState=new HashMap<>();
    public static int Online;// hold online count
    public static String []registerClient=new String[100];
    
    private int nextitem;// point next
    
    // constractor---------
    public ClientDetailsBook(){
        nextitem=0;
    }
    
    // methods-------
    
    // set client 
    public void setClient(String ClientName, int ClientID){
        if(Client.get(ClientName)==null){
            Client.put(ClientName, ClientID);
            ClientState.put(ClientName, true);
            registerClient[nextitem]=ClientName;
            Online++;
            nextitem++;
        }
        else{
            ClientState.put(ClientName, true);
        }
        
    }
    
    // get state of client (online -> true )
    public boolean getState(String ClientName){
        return ClientState.get(ClientName);
    }
    
    // set State  then togle state of client
    public boolean setState(String ClientName){
        if(ClientState.get(ClientName)){
            Online--;
        }
        if(!ClientState.get(ClientName)){
            Online++;
        }
        return !ClientState.replace(ClientName,!ClientState.get(ClientName));
    }
    
    // to get online count
    public int getOnlineCount(){
        return Online;
    }
    
    
}
