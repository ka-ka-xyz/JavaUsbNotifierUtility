package jp.gr.java_conf.ka_ka_xyz.usblamp.conf;

import org.junit.Test;

import jp.gr.java_conf.ka_ka_xyz.usblamp.conf.DeviceUtil;
import junit.framework.TestCase;

public class TypeUtilTest extends TestCase{

	@Test
	public void testParseShortArrayToByteArray1(){
		byte[] results = DeviceUtil.parseShortArrayToByteArray("0x01 0x00 0x00 0x00 0x00");
		byte[] expected = {1, 0, 0, 0, 0};
		assertEquals(results.length, expected.length);
		assertEquals(results[0], expected[0]);
		assertEquals(results[1], expected[1]);
		assertEquals(results[2], expected[2]);
		assertEquals(results[3], expected[3]);
		assertEquals(results[4], expected[4]);
	}
	
	@Test
	public void testParseShortArrayToByteArray2(){
		byte[] results = DeviceUtil.parseShortArrayToByteArray(null);
		byte[] expected = new byte[0];
		assertEquals(results.length, expected.length);
	}
	
	@Test
	public void testParseShortArrayToByteArray3(){
		byte[] results = DeviceUtil.parseShortArrayToByteArray("");
		byte[] expected = new byte[0];
		assertEquals(results.length, expected.length);
	}
	
	
	@Test
	public void testParseStringToInt1(){
		int expected = 10;
		int result = DeviceUtil.parseStringToInt("10");
		assertEquals(result, expected);
	}
	
	@Test
	public void testParseStringToInt2(){
		int expected = 0x10;
		int result = DeviceUtil.parseStringToInt("0x10");
		assertEquals(result, expected);
	}
	
	@Test
	public void testParseStringToInt3(){
		int expected = 0x1a;
		int result = DeviceUtil.parseStringToInt("0x1a");
		assertEquals(result, expected);
	}
	
	@Test
	public void testParseStringToShort1(){
		int expected = 10;
		int result = DeviceUtil.parseStringToShort("10");
		assertEquals(result, expected);
	}
	
	@Test
	public void testParseStringToShort2(){
		int expected = 0x10;
		int result = DeviceUtil.parseStringToShort("0x10");
		assertEquals(result, expected);
	}
	
	@Test
	public void testParseStringToShort3(){
		int expected = 0x1a;
		int result = DeviceUtil.parseStringToShort("0x1a");
		assertEquals(result, expected);
	}
}
