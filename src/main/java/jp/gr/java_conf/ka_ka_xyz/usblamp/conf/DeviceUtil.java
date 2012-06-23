package jp.gr.java_conf.ka_ka_xyz.usblamp.conf;

public final class DeviceUtil {
	
	private DeviceUtil(){}

	public static byte[] parseShortArrayToByteArray(String key) {
		if ("".equals(key) || key == null) {
			throw new IllegalArgumentException("key is null or void");
		}
		String[] strs = key.split(" ");
		byte[] bytes = new byte[strs.length];
		for (int i = 0; i < strs.length; i++) {
			bytes[i] = (byte) parseStringToShort(strs[i]);
		}
		return bytes;
	}

	public static short parseStringToShort(String key) {
		if ("".equals(key) || key == null)
			throw new IllegalArgumentException("key is null or void");
		if (key.indexOf('x') > 0) {
			if (key.length() <= 2) {
				return 0;
			} else {
				return Short.parseShort(
						key.substring(key.indexOf('x') + 1, key.length()), 16);
			}
		}
		return Short.parseShort(key);
	}

	public static int parseStringToInt(String key) {
		if ("".equals(key) || key == null)
			throw new IllegalArgumentException("key is null or void");
		if (key.indexOf('x') > 0) {
			if (key.length() <= 2) {
				return 0;
			} else {
				return Integer.parseInt(
						key.substring(key.indexOf('x') + 1, key.length()), 16);
			}
		}
		return Integer.parseInt(key);
	}
}
