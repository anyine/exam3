package org.flycloud.web.exam3;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {

	public static void main(String[] args) {
		startWebCenter();

		if (Desktop.isDesktopSupported()) {
			try {
				URI uri = URI.create("http://127.0.0.1:8080/index.html");
				Desktop dp = Desktop.getDesktop();
				if (dp.isSupported(Desktop.Action.BROWSE)) {
					dp.browse(uri);
				}
			} catch (NullPointerException e) {
			} catch (IOException e) {
			}
		}
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
