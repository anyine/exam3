package org.flycloud.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Exporter {

	private String dump = "mysqldump";
	private String user = "root";
	private String password;
	private String database;
	private String host = "127.0.0.1";
	private int port = 3306;
	private String path = "back";

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDump() {
		return dump;
	}

	public void setDump(String dump) {
		this.dump = dump;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void export() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		Date d = new Date();
		new File(path).mkdirs();
		String file = path + "/" + this.database + "-" + format.format(d)
				+ ".sql";
		export(this.dump, this.host, this.port, this.user, this.password,
				this.database, file);
	}

	public static void export(String cmd, String host, int port, String user,
			String pass, String db, String file) {
		String cd = String.format(
				"\"%s\" --host=%s --port=%d -u%s -p%s  --databases %s", cmd,
				host, port, user, pass, db);
		System.out.println(cd);
		Runtime run = Runtime.getRuntime();
		try {
			Process p = run.exec(cd);
			BufferedInputStream in = new BufferedInputStream(p.getInputStream());
			FileUtil.writeStream(in, file);
			if (p.waitFor() != 0) {
				if (p.exitValue() == 1)
					System.err.println("命令执行失败!");
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Exporter e = new Exporter();
		e.setDump("C:\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysqldump.exe");
		e.setDatabase("exam");
		e.setPassword("poiuyt");
		e.setPath("z://back");
		e.export();
	}

}
