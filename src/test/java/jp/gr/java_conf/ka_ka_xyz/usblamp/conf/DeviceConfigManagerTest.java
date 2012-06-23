package jp.gr.java_conf.ka_ka_xyz.usblamp.conf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jp.gr.java_conf.ka_ka_xyz.usblamp.conf.DeviceInfo;
import jp.gr.java_conf.ka_ka_xyz.usblamp.conf.SignalInfo;
import junit.framework.TestCase;

import org.junit.Test;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class DeviceConfigManagerTest extends TestCase{

	@Test
	public void testXmlSerializer(){
		
		SignalInfo off = new SignalInfo();
		off.setBit("0x00");
		off.setData("0x00 0x00 0x00 0x00 0x00");
		off.setOff(true);
		
		List<SignalInfo> leds = new ArrayList();
		SignalInfo led1 = new SignalInfo();
		led1.setBit("0x01");
		led1.setData("0x01 0x00 0x00 0x00 0x00");

		SignalInfo led2 = new SignalInfo();
		led2.setBit("0x02");
		led2.setData("0x02 0x00 0x00 0x00 0x00");

		SignalInfo led3 = new SignalInfo();
		led3.setBit("0x04");
		led3.setData("0x04 0x00 0x00 0x00 0x00");
		
		leds.add(off);
		leds.add(led1);
		leds.add(led2);
		leds.add(led3);
		
		DeviceInfo dev = new DeviceInfo();

		dev.setSignals(leds);
		dev.setOutEndPoint("0x81");
		dev.setProductId("0x0004");
		dev.setVendorId("0x1d34");

		Serializer serializer = new Persister();
		try {
			serializer.write(dev, new File("src/test/resources/device.out.xml"));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
}
