package jp.gr.java_conf.ka_ka_xyz.usblamp.conf;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="signal")
public class SignalInfo {
	
	@Attribute(required=true)
	private String bit;
	
	@Attribute(required=true, name="data")
	private String data;
	
	@Attribute(required=false, name="off")
	private boolean isOff = false;

	public String getBit() {
		return bit;
	}
	
	public String getData() {
		return data;
	}
	
	public boolean isOff() {
		return isOff;
	}


	void setBit(String bit) {
		this.bit = bit;
	}
	
	void setData(String data) {
		this.data = data;
	}

	void setOff(boolean isOff) {
		this.isOff = isOff;
	}
}
