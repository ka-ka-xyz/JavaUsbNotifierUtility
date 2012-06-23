package jp.gr.java_conf.ka_ka_xyz.usblamp.core;

import java.util.List;

import jp.gr.java_conf.ka_ka_xyz.usblamp.conf.DeviceConfigManager;
import jp.gr.java_conf.ka_ka_xyz.usblamp.conf.DeviceInfo;
import jp.gr.java_conf.ka_ka_xyz.usblamp.conf.DeviceUtil;
import jp.gr.java_conf.ka_ka_xyz.usblamp.conf.InitSignalInfo;
import jp.gr.java_conf.ka_ka_xyz.usblamp.conf.SignalInfo;

import ch.ntb.usb.Device;
import ch.ntb.usb.USB;
import ch.ntb.usb.USBException;

public class UsbLampDeviceImpl implements UsbLamp {

	private DeviceInfo devInfo;
	private Device dev;

	protected UsbLampDeviceImpl() {
		devInfo = DeviceConfigManager.getInstance().loadConfig();
		short productId = DeviceUtil.parseStringToShort(devInfo.getProductId());
		short vendorId = DeviceUtil.parseStringToShort(devInfo.getVendorId());
		this.dev = USB.getDevice(vendorId, productId);
	}

	@Override
	public UsbLamp open() {
		try {
			dev.open(1, 0, -1);
			List<InitSignalInfo> initSignals = devInfo.getInitSignals();
			
			for(InitSignalInfo signal : initSignals){
				byte[] data = DeviceUtil.parseShortArrayToByteArray(
						signal.getData());
				send(data);
			}
		} catch (USBException e) {
			throw new UsbDeviceException(e);
		}
		return this;
	}
	
	private void send(byte[] data) throws USBException{
		dev.controlMsg(
				USB.REQ_TYPE_DIR_HOST_TO_DEVICE |  
                USB.REQ_TYPE_TYPE_CLASS |  
                USB.REQ_TYPE_RECIP_INTERFACE,  
				0x09, 0x0200, 0, 
				data, data.length, 2000, false);
		
	}
	
	/* (non-Javadoc)
	 * @see jp.gr.java_conf.ka_ka_xyz.usb.UsbLamp#close()
	 */
	@Override
	public UsbLamp close() {
		try {
			dev.close();
		} catch (USBException e) {
			throw new UsbDeviceException(e);
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see jp.gr.java_conf.ka_ka_xyz.usb.UsbLamp#lightOff()
	 */
	@Override
	public UsbLamp lightOff() {
		byte[] data = DeviceUtil.parseShortArrayToByteArray(this.devInfo.getOff()
				.getData());
		try {
			send(data);
		} catch (USBException e) {
			throw new UsbDeviceException(e);
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see jp.gr.java_conf.ka_ka_xyz.usb.UsbLamp#lightOn(short)
	 */
	@Override
	public UsbLamp lightOn(short bit) {
		SignalInfo signal = DeviceConfigManager.getInstance().getLedsByBit(bit);
		byte[] data = DeviceUtil.parseShortArrayToByteArray(
				signal.getData());
		try {
			send(data);
		} catch (USBException e) {
			throw new UsbDeviceException(e);
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see jp.gr.java_conf.ka_ka_xyz.usb.UsbLamp#halt(long)
	 */
	@Override
	public UsbLamp halt(long milisec) {
		try {
			Thread.sleep(milisec);
		} catch (InterruptedException e) {
		}

		return this;
	}

}
