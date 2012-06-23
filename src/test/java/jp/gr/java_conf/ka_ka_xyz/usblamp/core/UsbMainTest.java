package jp.gr.java_conf.ka_ka_xyz.usblamp.core;

import jp.gr.java_conf.ka_ka_xyz.usblamp.core.UsbLamp;
import jp.gr.java_conf.ka_ka_xyz.usblamp.core.UsbLampFactory;
import junit.framework.TestCase;

import org.junit.Test;

public class UsbMainTest  extends TestCase{
	
	@Test
	public void testLight(){
		try{
		UsbLamp usb = UsbLampFactory.getUsbLamp();
		usb.open()
		.lightOn((short)1)
		.halt(1000)
		.lightOn((short)2)
		.halt(1000)
		.lightOn((short)3)
		.halt(1000)
		.lightOn((short)4)
		.halt(1000)
		.lightOn((short)5)
		.halt(1000)
		.lightOn((short)6)
		.halt(1000)
		.lightOn((short)7)
		.halt(1000)
		.lightOff();
		} catch (Throwable t){
			t.printStackTrace();
			fail();
		}
		
	}

}
