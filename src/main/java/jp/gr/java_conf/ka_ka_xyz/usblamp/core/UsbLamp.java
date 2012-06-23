package jp.gr.java_conf.ka_ka_xyz.usblamp.core;

public interface UsbLamp {

	/**
	 * open USB device.
	 * */
	public abstract UsbLamp open();

	/**
	 * close USB device.
	 * */
	public abstract UsbLamp close();

	/**
	 * turn off USB lamp
	 * */
	public abstract UsbLamp lightOff();

	/**
	 * turn on USB lamp.
	 * @param bit led bit flag defined in device.xml.
	 * */
	public abstract UsbLamp lightOn(short bit);

	/**
	 * wait.
	 * @param milisec waiting time (milisecond). 
	 * */
	public abstract UsbLamp halt(long milisec);

}