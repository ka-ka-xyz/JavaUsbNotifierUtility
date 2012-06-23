package jp.gr.java_conf.ka_ka_xyz.usblamp.daemon;

import java.util.logging.*;

import jp.gr.java_conf.ka_ka_xyz.usblamp.core.UsbDeviceException;
import jp.gr.java_conf.ka_ka_xyz.usblamp.core.UsbTimerTask;


public class UsbMain {
	
	private static final String START = "start";
	private static final String STOP = "stop";
	
	private static UsbTimerTask task = null;
	private static Logger logger = Logger.getLogger(UsbMain.class.getName());
	
	public static void main(String[] args){
		setLogConfig();
		if(args == null || args.length < 1){
			logger.info("no argument. do nothing");
			return;
		}
		
		if(START.equalsIgnoreCase(args[0])){
			logger.info("start");
			if(task == null){
				try{
					task = new UsbTimerTask();
				} catch(UsbDeviceException ude){
					logger.log(Level.WARNING, "failed to initialize usb dvice.", ude);
				}
			}
			try{
				while(true){
					task.run();
				}
			} catch(Throwable t){
				logger.log(Level.WARNING, "failed to schedule.", t);
			}
		} else if(STOP.equalsIgnoreCase(args[0])){
			logger.info("stop");
			task.lightOff();
			System.exit(0);
		}
	}
	
	private static void setLogConfig(){
		System.setProperty("java.util.logging.config.file","conf/logging.properties");
	}
}