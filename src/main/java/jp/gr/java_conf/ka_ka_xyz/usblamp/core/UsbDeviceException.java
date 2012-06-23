package jp.gr.java_conf.ka_ka_xyz.usblamp.core;

public class UsbDeviceException extends RuntimeException{
	
	private static final long serialVersionUID = -7960754494360870234L;

	public UsbDeviceException(){
		super();
	}
	
	public UsbDeviceException(String str){
		super(str);
	}
	
	public UsbDeviceException(String str, Throwable t){
		super(str, t);
	}

	public UsbDeviceException(Throwable t){
		super(t);
	}

}
