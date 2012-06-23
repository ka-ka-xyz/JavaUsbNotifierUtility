package jp.gr.java_conf.ka_ka_xyz.usblamp.conf;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="init-signal")
public class InitSignalInfo {
	
	@Attribute(required=true, name="data")
	private String data;
	
	public String getData() {
		return data;
	}
	void setData(String data) {
		this.data = data;
	}
}
