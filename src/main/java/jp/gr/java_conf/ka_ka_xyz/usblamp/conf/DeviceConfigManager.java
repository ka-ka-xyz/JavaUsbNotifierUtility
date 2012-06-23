package jp.gr.java_conf.ka_ka_xyz.usblamp.conf;

import java.util.List;

import jp.gr.java_conf.ka_ka_xyz.usblamp.core.UsbDeviceException;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class DeviceConfigManager {
	
	private static DeviceConfigManager myself = null;
	private static DeviceInfo devInfo = null;
	
	private DeviceConfigManager(){}
	
	public static DeviceConfigManager getInstance(){
		if(myself == null){
			myself = new DeviceConfigManager();
		}
		
		return myself;
	}
	
	public DeviceInfo loadConfig(){
		Serializer serializer = new Persister();
		try {
			devInfo = serializer.read(DeviceInfo.class, 
					ClassLoader.getSystemResourceAsStream("device.xml"));
			return devInfo;
		} catch (Exception e) {
			throw new UsbDeviceException(e);
		}
	}
	
	/**
	 * @param device.xmlで指定されたbit値。（/device/signals/signal[bit]）
	 * */
	public SignalInfo getLedsByBit(short bit){
		
		if(bit < 1){
			return devInfo.getOff();
		}
		
		for(SignalInfo led : devInfo.getSignals()){
			if(DeviceUtil.parseStringToShort(led.getBit()) == bit){
				return led;
			}
		}
		return devInfo.getOff();
	}
	
	public List<InitSignalInfo> getInitSignals(){
		
		return devInfo.getInitSignals();
	}
}
