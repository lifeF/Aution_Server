


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/**
 * UNIVERSITY OF PERADENIYA 
 * DEPARTMENT OF COMPUTER ENGINEERING
 * 
 * @author Kalana Dhananjaya
 * Group 11 E/14/021 E/14/287
 * CO225: Software Construction 
 * Project II - Stack Exchange Server
 */

public class Manage  implements ActionListener{
    public static JTable StackDetails; // For Hold Stocks Details
    public static JTable ClientDetails; // For Hold Client Details Online or Not
    public static JTable TrackerStock; // For Hold Stocks Details
    
    
    public static StockDB SDB; // Stocks DataBase
    
    private final  String [][] TableContentStockDetails;
    private final  String [][] TableContentClientDetails;
    
    private final String [] titleForStock={"Index","Symbol","Current Bidder","Current Top Bid Price"};
    private final String [] titleForClient={"Index","ClientName","State"};
    private final String [] titleForTeackerStock={"Name","Offer Price","Date / time"};
   
    
    @SuppressWarnings("empty-statement")
    public Manage(){
        
        //Set Up Stock Details Table
        StackDetails=new JTable();
        ClientDetails=new JTable();
        TrackerStock= new JTable();
        
        StackDetails.setShowGrid(true);
        StackDetails.setEnabled(false);
        ClientDetails.setEnabled(false);
        TrackerStock.setEnabled(false);
        
        // Set up dataBase and 
        SDB= new StockDB("stocks.csv");
        
        TableContentStockDetails= new String[SDB.getlen()][4];
        TableContentClientDetails= new String[100][3];
        
        for (int i = 0; i < SDB.getlen(); i++) {
            TableContentStockDetails[i][0]=String.valueOf(i);
            TableContentStockDetails[i][1]=StockDB.SymbolList[i];   
        }
        
        for (int i = 0; i < 10; i++) {
            TableContentStockDetails[i][0]=String.valueOf(i);
        }
        
        Timer T1= new Timer(500,this);
        T1.start();
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        for(int i=0;i<SDB.getlen();i++){
            TableContentStockDetails[i][2]=StockDB.CurrentCustormer.get(StockDB.SymbolList[i]);
            TableContentStockDetails[i][3]=String.valueOf(StockDB.StockData.get(StockDB.SymbolList[i]));
        }
        
        for(int i=0;i<100;i++){
            TableContentClientDetails[i][0]="<html><font color=\"green\">"+String.valueOf(i)+"</font></html>"; //index
            TableContentClientDetails[i][1]=ClientDetailsBook.registerClient[i];
            boolean k =ClientDetailsBook.registerClient[i]==null;
            boolean ClientState= (ClientDetailsBook.ClientState.get(ClientDetailsBook.registerClient[i])==null)||
                    (ClientDetailsBook.ClientState.get(ClientDetailsBook.registerClient[i])==false);
            TableContentClientDetails[i][2]=!k?(!ClientState?"<html><font color=\"green\">ONLINE</font></html>":
                    "<html><font color=\"red\">OFFLINE</font></html>"):"";
        }
        //set every 0.5 s Lable value
        Main.YHOO1.setText(String.valueOf(SDB.getCurrentStockPrice("YHOO")));
        Main.ONLINECLIENT.setText(String.valueOf(ClientDetailsBook.Online));
        Main.YHOO.setText(String.valueOf(SDB.getCurrentStockPrice("GOOGL")));
        Main.TXN.setText(String.valueOf(SDB.getCurrentStockPrice("TXN")));
        Main.TSLA.setText(String.valueOf(SDB.getCurrentStockPrice("TSLA")));
        Main.XLNX.setText(String.valueOf(SDB.getCurrentStockPrice("XLNX")));
        Main.VRTU.setText(String.valueOf(SDB.getCurrentStockPrice("VRTU")));
        Main.FB.setText(String.valueOf(SDB.getCurrentStockPrice("FB")));
        Main.YHOO2.setText(String.valueOf(SDB.getCurrentStockPrice("MSFT")));
        
        // Table model update 
        StackDetails.setModel(new DefaultTableModel(TableContentStockDetails,titleForStock));
        ClientDetails.setModel(new DefaultTableModel(TableContentClientDetails,titleForClient));
        TrackerStock.setModel(new DefaultTableModel(Main.setTrackerTableData,titleForTeackerStock));
        
        
        
        
        
    }
}
