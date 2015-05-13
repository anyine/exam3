package org.flycloud.web.exam3;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;

public class MyPath {
	public static String getPath() {
		URL url = MyPath.class.getProtectionDomain().getCodeSource()
				.getLocation();
		String filePath = null;
		try {
			filePath = URLDecoder.decode(url.getPath(), "utf-8");// 转化为utf-8编码
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (filePath.endsWith(".jar")) {
			filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
		}

		File file = new File(filePath);

		filePath = file.getAbsolutePath();// 得到windows下的正确路径
		return filePath;
	}
}