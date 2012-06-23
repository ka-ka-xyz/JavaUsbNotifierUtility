package jp.gr.java_conf.ka_ka_xyz.usblamp.conf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root(name="device")
public class DeviceInfo {

	@Attribute(required=true)
	private String vendorId;
	
	@Attribute(required=true)
	private String productId;

	@Attribute(required=true)
	private String outEndPoint;
	
	@Attribute(required=true)
	private String impl;

	
	@ElementList(inline=true, required=true)
	@Path("signals")
	private List<SignalInfo> signals = new ArrayList<SignalInfo>();

	@ElementList(inline=true, required=false)
	@Path("init-signals")
	private List<InitSignalInfo> initSignals = new ArrayList<InitSignalInfo>();
	
	public String getVendorId() {
		return vendorId;
	}

	public String getProductId() {
		return productId;
	}

	public String getOutEndPoint() {
		return outEndPoint;
	}

	public List<SignalInfo> getSignals() {
		return Collections.unmodifiableList(signals);
	}
	
	public List<InitSignalInfo> getInitSignals() {
		return Collections.unmodifiableList(initSignals);
	}
	
	public String getImpl() {
		return impl;
	}

	public SignalInfo getOff(){
		for(SignalInfo led : signals){
			if(led.isOff()){
				return led;
			}
		}
		return null;
	}

	void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	void setProductId(String productId) {
		this.productId = productId;
	}

	void setOutEndPoint(String outEndPoint) {
		this.outEndPoint = outEndPoint;
	}

	void setSignals(List<SignalInfo> signals) {
		this.signals = signals;
	}
	
	void setInitSignals(List<InitSignalInfo> signals) {
		this.initSignals = signals;
	}

	void setImpl(String impl) {
		this.impl = impl;
	}
}
