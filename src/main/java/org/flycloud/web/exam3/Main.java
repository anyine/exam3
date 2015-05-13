package org.flycloud.web.exam3;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {

	public static void main(String[] args) {
		startWebCenter();
	}

	public static void startWebCenter() {
		Server server = new Server(8080);
		WebAppContext webApp = new WebAppContext();

		webApp.setContextPath("/");
		webApp.setWar("src/main/webapp");
		server.setHandler(webApp);

		try {
			server.start();
		} catch (Exception e) {
			System.out.println("Web 服务启动失败  ");
		}
	}
}
