package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.AuthFormServlet;
import servlets.ParamReturnServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        AuthFormServlet authFormServlet = new AuthFormServlet();
        ParamReturnServlet paramReturnServlet = new ParamReturnServlet();

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(authFormServlet),"/*");
        contextHandler.addServlet(new ServletHolder(paramReturnServlet), "/mirror");

        Server server = new Server(8080);
        server.setHandler(contextHandler);

        server.start();
        server.join();

    }
}
