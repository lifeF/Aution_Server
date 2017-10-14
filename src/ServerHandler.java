
import java.io.*;
import java.net.*;


/**
 * UNIVERSITY OF PERADENIYA 
 * DEPARTMENT OF COMPUTER ENGINEERING
 * 
 * @author Kalana Dhananjaya
 * Group 11 E/14/021 E/14/287
 * CO225: Software Construction 
 * Project II - Stack Exchange Server
 */

public class ServerHandler extends Thread {// extends thread class
    
    private final Socket socket;// to hold access socket 
    public static ClientDetailsBook Book1=new ClientDetailsBook();
    private final int CID;// client ID
    private String Name;// hold name of client
    
            
    // constructor---------
    public ServerHandler(Socket Socket ,int id ){
        this.socket=Socket;
        this.CID =id;
    }
    
    // server loop method
    public void Serverloop() throws IOException{
                
                try  {
                    //Buffer Stream create for communicate
                    BufferedReader input = new 
                    BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new 
                    PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                    
                    out.println("Welcome to Auction Server");
                    out.println("------------------------------>");
                    out.println("Please Enter Your Name");
                    out.flush();
                    
                    Name=input.readLine(); // get first one as name of client
                    Book1.setClient(Name, CID);
                    String SYM;
                    
                    
                    
                    while(true){
                        out.println("Enter Stock Symbol ? ");
                        out.flush();
                        
                        SYM=input.readLine();
                        if(Manage.SDB.hasStock(SYM)){
                            out.println(SYM +" Current Price : "+Manage.SDB.getCurrentStockPrice(SYM));
                            out.flush();
                            
                            for( String answer = input.readLine();;answer = input.readLine()){
                               if(answer!=null && ! "Q".equals(answer)){
                                    StockDB.setStockValue(SYM, Name, Double.valueOf(answer));// set current bid value
                                    
                                    out.println(SYM +" Current Price : "+Manage.SDB.getCurrentStockPrice(SYM));
                                    out.println(SYM +" Current TOP Bidder : "+Manage.SDB.getCurrentCustomer(SYM));
                                    out.println("To Exit in Stock Bid > Q ");
                                    out.flush();
                                }

                                if("Q".equals(answer)||answer==null){
                                    out.println("To Exit in Auction Server  > EXIT ");
                                    out.flush();
                                    break;

                                }        
                            }
                        }
                        else{
                            
                            if("EXIT".equals(SYM)||SYM==null){
                                    out.println("Bye Come Back again");
                                    out.flush();
                                    break;

                            }
                            else{
                                out.println("-1");out.flush();
                            }
                        }    
                        
                    }
                    
                }
                finally{
                   Book1.setState(Name);// togle state of client
                   socket.close();// close socket
                }
    }

    @Override
    public void run() {
        try {
            Serverloop();
        } catch (IOException ex) {
           
        }
    }
    
    
    
}
