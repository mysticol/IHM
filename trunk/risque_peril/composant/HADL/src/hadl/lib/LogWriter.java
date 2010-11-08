package hadl.lib;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class LogWriter {
	
	private static HashMap<String, PrintStream> logMap = new HashMap<String, PrintStream>();
	private static LogWriter instance = null;
	
	/* windows path
	private String path = "src\\log\\"; */
	/* linux path */
	private String path = "src/log/";
	
	
	private boolean withDate = true;
	private DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
	
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
	
	public void close(String id){
		// On ferme le fichier de log associe a l'id
		logMap.get(id).close();
	}
	
	public void write(String id, String log){
		// On ecrit dans le log file
		if (withDate) {
			logMap.get(id).println(dateFormat.format(new Date()) + "  |  " +log);
		} else {
			logMap.get(id).println(log);
		}
	}
	
	

}
