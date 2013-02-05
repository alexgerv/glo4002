package ca.ulaval.glo4002.emergencyServer.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.sun.jersey.spi.container.servlet.ServletContainer;

public class EmergencyServerMain {

	private static int PORT = 8081;
	private static Server server;

	public static void startServer() throws Exception {
		server = new Server(PORT);
		ServletContextHandler servletContextHandler = new ServletContextHandler(
				server, "/");
		ServletHolder jerseyServletHolder = new ServletHolder(
				ServletContainer.class);
		jerseyServletHolder.setInitParameter(
				"com.sun.jersey.config.property.resourceConfigClass",
				"com.sun.jersey.api.core.PackagesResourceConfig");
		jerseyServletHolder.setInitParameter(
				"com.sun.jersey.config.property.packages",
				"ca.ulaval.glo4002.emergencyServer.rest");
		servletContextHandler.addServlet(jerseyServletHolder, "/*");
		server.start();
	}

	public static void stopServer() throws Exception {
		server.stop();
	}
}