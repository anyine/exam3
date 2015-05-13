package org.flycloud.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class FileUtil {
	public static void writeString(String src, String filePath, String charset)
			throws Exception {
		FileOutputStream fs = new FileOutputStream(filePath);
		fs.write(src.getBytes(charset));
		fs.close();
	}

	public static void writeString(String src, String filePath, Charset charset)
			throws Exception {
		FileOutputStream fs = new FileOutputStream(filePath);
		fs.write(src.getBytes(charset));
		fs.close();
	}

	public static String toString(InputStream is, String charset)
			throws UnsupportedEncodingException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is,
				charset));
		StringBuilder sb = new StringBuilder();
		String line = null;

		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public static void writeStream(InputStream stream, String file) {
		try {
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String toString(InputStream is, Charset charset) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is,
				charset));
		StringBuilder sb = new StringBuilder();
		String line = null;

		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public static String toString(String file, Charset charset) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), charset));
			StringBuilder sb = new StringBuilder();
			String line = null;

			try {
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return sb.toString();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return "";
	}
}
