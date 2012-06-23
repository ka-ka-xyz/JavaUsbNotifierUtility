package jp.gr.java_conf.ka_ka_xyz.usblamp.core;

import jp.gr.java_conf.ka_ka_xyz.usblamp.conf.DeviceConfigManager;

public class UsbLampFactory {
	private static UsbLamp lamp = null;
	
	public static UsbLamp getUsbLamp(){
		
		if(lamp == null){
			init();
		}
		return lamp;
	}
	
	private static UsbLamp init() {
		try {
			Class clazz = 
				Class.forName(DeviceConfigManager.getInstance().loadConfig().getImpl());
			lamp = (UsbLamp) clazz.newInstance();
		} catch (ClassNotFoundException cfe) {
			throw new UsbDeviceException(cfe);
		} catch (InstantiationException ie) {
			throw new UsbDeviceException(ie);
		} catch (IllegalAccessException iae) {
			throw new UsbDeviceException(iae);
		}
		return lamp;
	}

}
