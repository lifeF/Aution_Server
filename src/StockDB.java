
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
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

public class StockDB {
    
    public static  HashMap<String,Double> StockData;
    public static  HashMap<String,String> CurrentCustormer;
    public static String[] SymbolList;
    public static  HashMap<String,ArrayList<String>> StocksTracker;
    
    
    private String [] Fields;
   
    private FileReader FileInput;
    private BufferedReader ReaderFile;
    private int Col;
    private int Row;
    
    //constructor
    public StockDB(String CSVFile) {
        
        StockDB.StockData = new HashMap<>();
        StockDB.CurrentCustormer=new HashMap<>();
        StockDB.StocksTracker=new HashMap<>();
        StockDB.SymbolList= new String[3500];// can hold 3500 symbol
        
        try {
            // file input stream create
            FileInput =new FileReader(CSVFile);
            ReaderFile=new BufferedReader(FileInput);
            
            //get Fields Name to Data Base
            this.Fields=ReaderFile.readLine().split(",");
            Col =Fields.length+1;
            
            //get Data From CVS
           
            int i=0;
            for(String Line =ReaderFile.readLine();Line!=null;Line=ReaderFile.readLine()){
                String Sym=Line.split(",")[0];
                StockData.put(Sym,Double.valueOf(Line.split(",")[Line.split(",").length-1]));
                CurrentCustormer.put(Sym,null);
                StocksTracker.put(Sym,new ArrayList<>());
                SymbolList[i++]=Sym;
                
            }
            
           
            
        } 
        catch (FileNotFoundException ex) {
           System.out.println("File Not Found");
        } catch (IOException ex) {
            System.out.println("IO Ex");;
        }
    
    }// end constructor
    
    // methods --------
    
    // cheack stock symbol valid
    public boolean hasStock(String Name){
        return StockData.get(Name)!=null;
    }
    
    //set stock value 
    /*
    in here , Update Stock current value and give success state , 
    and track data add to the Tack stack array
    
    */
    public static synchronized  boolean setStockValue(String StockName,String CusName,double CusBidAmmount){
        if(CusBidAmmount>StockData.get(StockName)){
            StockData.replace(StockName, CusBidAmmount);
            Date dNow = new Date( );
            SimpleDateFormat ft = 
            new SimpleDateFormat ("yy.MM.dd 'at' hh:mm:ss a");
            String S1 = ft.format(dNow);
            CurrentCustormer.replace(StockName,CusName);
            StocksTracker.get(StockName).add(CusName+","+CusBidAmmount+","+S1); // For Get track data
            return true;
        }
        else{
            return false;
        }
    }
    
    // get current Customer on given Stock
    public synchronized String getCurrentCustomer(String StockName){
        return CurrentCustormer.get(StockName);
    }
    
    // get current Stock price
    public  double getCurrentStockPrice(String StockName){
        return StockData.get(StockName);
    }
    
    // to get Number of stocks in data base
    public int getlen(){
        return StockData.size();
    }
    
    
    
    
    
}
    


