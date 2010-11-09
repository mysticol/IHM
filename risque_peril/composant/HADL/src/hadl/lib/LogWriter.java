package hadl.lib;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class LogWriter {
	
	private static HashMap<String, PrintStream> logMap = new HashMap<String, PrintStream>();
	private static LogWriter instance = null;
	private static String idUnique = null;
	private static PrintStream psUnique = null;
	private static boolean logUnique = false;
	
	/* windows path
	private String path = "src\\log\\"; */
	/* linux path */
	private String path = "src/log/";
	
	
	private boolean withDate = true;
	private DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
	private String separateur = "---------------------------------------------------------------------";
	
	// Constructeur
	private LogWriter(){
		// Pattern singleton
	}
	
	// Getters-Setters
	public void setWithDate(boolean withDate) {
		this.withDate = withDate;
	}
	public boolean isWithDate() {
		return withDate;
	}
	public void setFormat(DateFormat format) {
		this.dateFormat = format;
	}
	public DateFormat getFormat() {
		return dateFormat;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public static void setIdUnique(String idUnique) {
		LogWriter.idUnique = idUnique;
	}
	public static String getIdUnique() {
		return idUnique;
	}
	public static void setPsUnique(PrintStream psUnique) {
		LogWriter.psUnique = psUnique;
	}
	public static PrintStream getPsUnique() {
		return psUnique;
	}
	public static void setLogUnique(boolean logUnique) {
		LogWriter.logUnique = logUnique;
	}
	public static boolean isLogUnique() {
		return logUnique;
	}
	public void setSeparateur(String separateur) {
		this.separateur = separateur;
	}
	public String getSeparateur() {
		return separateur;
	}

	public static LogWriter getInstance() {
		
		if (instance == null){
			instance = new LogWriter();
		}
		
		return instance;
		
	}
	
	public void init(String id){
		// On associe un id a un fichier de log
		
        FileOutputStream out; // declare a file output object
        PrintStream p; // declare a print stream object

        try
        {
        	// On test si le directory path existe
        	// Si non, on le créé
        	File dir=new File(path);
        	if(!dir.exists()){
        		dir.mkdir();
        	}
        	
            // Create a new file output stream
            // connected to "myfile.txt"
            out = new FileOutputStream(path + id + ".log");

            // Connect print stream to the output stream
            p = new PrintStream( out );
           
            logMap.put(id, p);
        }
        catch (Exception e)
        {
                System.err.println ("Error writing to file");
        }		
		
	}
	
	public void initUnique(String id){
		// On associe un id a un fichier de log
		
        FileOutputStream out; // declare a file output object
        PrintStream p; // declare a print stream object

        try
        {
        	// On test si le directory path existe
        	// Si non, on le créé
        	File dir=new File(path);
        	if(!dir.exists()){
        		dir.mkdir();
        	}
        	
            // Create a new file output stream
            // connected to "myfile.txt"
            out = new FileOutputStream(path + id + ".log");

            // Connect print stream to the output stream
            p = new PrintStream( out );
           
            idUnique = id;
            psUnique = p;
            
            logUnique = true;
        }
        catch (Exception e)
        {
                System.err.println ("Error writing to file");
        }				
		
		
	}
	
	public void open(String id){
		// On associe un id a un fichier de log
		
        FileOutputStream out; // declare a file output object
        PrintStream p; // declare a print stream object

        try
        {
                // Create a new file output stream
                // connected to "myfile.txt"
                out = new FileOutputStream(new File(path + id + ".log"), true);

                // Connect print stream to the output stream
                p = new PrintStream( out );
               
                logMap.put(id, p);
        }
        catch (Exception e)
        {
                System.err.println ("Error writing to file");
        }		
		
	}
	
	public void open(){
		// On associe un id a un fichier de log
		
        FileOutputStream out; // declare a file output object
        PrintStream p; // declare a print stream object

        try
        {
            // Create a new file output stream
            // connected to "myfile.txt"
            out = new FileOutputStream(new File(path + idUnique + ".log"), true);

            // Connect print stream to the output stream
            p = new PrintStream( out );
            
            psUnique = p;
        }
        catch (Exception e)
        {
                System.err.println ("Error writing to file");
        }			
	}
	
	
	public void close(String id){
		// On ferme le fichier de log associe à l'id
		logMap.get(id).close();
	}
	
	public void close(){
		// On ferme le fichier de log associe à l'id
		psUnique.close();
	}
	
	
	public void write(String id, String log){
		// On écrit dans le log file
		if (withDate) {
			logMap.get(id).println(dateFormat.format(new Date()) + "  |  " +log);
		} else {
			logMap.get(id).println(log);
		}
	}
	
	public void write(String log){
		// On écrit dans le log file
		if (withDate) {
			psUnique.println(dateFormat.format(new Date()) + "  |  " +log);
		} else {
			psUnique.println(log);
		}
	}
	
	
	public void writejl(String id, String log){
		write(id, log);
		logMap.get(id).println("");
	}
	
	public void writejl(String log){
		write(log);
		psUnique.println("");
	}
	
	
	public void writesep(String id){
		logMap.get(id).println(separateur);
	}
	
	public void writesep(){
		psUnique.println(separateur);
	}
	
	
	public void writeerr(String id, String errorMessage){
		logMap.get(id).println("");
		logMap.get(id).println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		logMap.get(id).println("!! " + errorMessage);
		logMap.get(id).println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		logMap.get(id).println("");
	}
	
	public void writeerr(String errorMessage){
		psUnique.println("");
		psUnique.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		psUnique.println("!! " + errorMessage);
		psUnique.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		psUnique.println("");
	}
	
	
	
	
	
	
	

}
