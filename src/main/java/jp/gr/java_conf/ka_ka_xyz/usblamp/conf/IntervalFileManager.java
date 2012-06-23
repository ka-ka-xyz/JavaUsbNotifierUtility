package jp.gr.java_conf.ka_ka_xyz.usblamp.conf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class IntervalFileManager {

	private static IntervalFileManager myself = null;
	private File file;
	private long lastModified = 0;
	private Properties props = new Properties();
	private static final short DEFAULT_INTERVAL = 1000;
	private static final short MIN_INTERVAL = 100;
	
	private static Logger logger = Logger.getLogger(IntervalFileManager.class.getName());
	private static final String SERVER_PROPERTIES_PATH = "conf/interval.properties";
	
	private IntervalFileManager() throws FileNotFoundException{
		file = new File(SERVER_PROPERTIES_PATH);
		if(!file.exists()){
			throw new FileNotFoundException("flag file not found");
		}
	}
	
	public static IntervalFileManager getInstance() throws FileNotFoundException{
		if(myself == null){
			myself = new IntervalFileManager();
		}
		return myself;
	}
	
	public static short getDefaultInterval(){
		return DEFAULT_INTERVAL;
	}
	
	public int getInterval(){
		
		int interval;
		try{
			load();
			interval = Integer.valueOf(props.getProperty("interval"));
		} catch(NumberFormatException nfe){
			logger.log(Level.WARNING,"failed to find parameter: [interval]. " +
					"use default duration " + DEFAULT_INTERVAL + " ms", nfe);
			interval = DEFAULT_INTERVAL;
		} catch(IOException ioe){
			logger.log(Level.WARNING,"failed to load interval.properties " +
					"use default duration" + DEFAULT_INTERVAL + " ms", ioe);
			interval = DEFAULT_INTERVAL;
		}
		if(interval < MIN_INTERVAL){
			interval = MIN_INTERVAL;
		}
		return interval;
	}
	
	private void load() throws IOException{
		if(isModified()){
			FileReader fr = new FileReader(file);
			props.load(fr);
			fr.close();
			fr = null;

		}
	}
	
	private boolean isModified(){
		long nowLastModified = file.lastModified();
		if(nowLastModified > lastModified){
			lastModified = nowLastModified;
			return true;
		} else {
			return false;
		}
	}
	
}
