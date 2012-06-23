package jp.gr.java_conf.ka_ka_xyz.usblamp.core;

import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import jp.gr.java_conf.ka_ka_xyz.usblamp.conf.FlagFileManager;
import jp.gr.java_conf.ka_ka_xyz.usblamp.conf.IntervalFileManager;

public class UsbTimerTask extends TimerTask{
	private static Logger logger = Logger.getLogger(UsbTimerTask.class.getName());
	UsbLamp usb;
	
	public UsbTimerTask(){
		usb = UsbLampFactory.getUsbLamp();
	}

	@Override
	public void run() {
		try{
			short[] flags = FlagFileManager.getInstance().getFlags();
			int interval = IntervalFileManager.getInstance().getInterval();
			usb.open();
			for(short flag : flags){
				usb.lightOn(flag).halt(interval);
			}
			usb.lightOff().halt(interval).close();
		} catch (Throwable t){
			logger.log(Level.WARNING, "falied to lighting.", t);
			usb.halt(IntervalFileManager.getDefaultInterval());
		}
	}
	
	public void lightOff(){
		usb.lightOff();
	}

}
