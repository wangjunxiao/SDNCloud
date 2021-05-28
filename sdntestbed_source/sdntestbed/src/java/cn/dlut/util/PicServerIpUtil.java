package cn.dlut.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PicServerIpUtil {
	public static String getSrc(String value) {
		String src = CyberspaceConfig.getValue(value);
		if (src == null) {
			return "";
		}
		if (!src.startsWith("http://")) {
			src = "http://" + src;
		}
		if (!src.endsWith("/")) {
			src = src + "/";
		}
		return src;
	}
}
