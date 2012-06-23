package jp.gr.java_conf.ka_ka_xyz.usblamp.conf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FlagFileManager {

	private static FlagFileManager myself = null;
	private File file;
	private long lastModified = 0;
	private Properties props = new Properties();
	private static final String DEFAULT_FLAG = "0";
	private static Logger logger = Logger.getLogger(FlagFileManager.class.getName());
	private static final String FLAG_PROPERTIES_PATH = "conf/flag.properties";
	
	private FlagFileManager() throws FileNotFoundException{
		file = new File(FLAG_PROPERTIES_PATH);
		if(!file.exists()){
			throw new FileNotFoundException("flag file not found");
		}
	}
	
	public static FlagFileManager getInstance() throws FileNotFoundException{
		if(myself == null){
			myself = new FlagFileManager();
		}
		return myself;
	}
	
	public short[] getFlags(){
		String flagStr;
		try {
			load();
			flagStr = props.getProperty("flag");
		} catch(IOException ioe){
			logger.log(Level.WARNING, "failed to read flag.properties", ioe);
			flagStr = DEFAULT_FLAG;
		}
		if(flagStr == null){
			logger.log(Level.WARNING, "failed to read parameter [flag]. use default value " +
					DEFAULT_FLAG);
			
		}
		
		String[] flagStrs = flagStr.split("[,:;]");
		short[] flag = new short[flagStrs.length];
		for(int i=0; i < flagStrs.length; i++){
			try{
				flag[i] = Short.valueOf(flagStrs[i]==null?DEFAULT_FLAG:flagStrs[i].trim());
			} catch (NumberFormatException nfe){
				logger.log(Level.WARNING, "failed to read value of flag. use default value " +
						DEFAULT_FLAG, nfe);
				flag[i] = Short.valueOf(DEFAULT_FLAG);
			}
		}
		return flag;
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
