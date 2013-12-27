package kotlin.nosql.demo

import javax.servlet.http.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;

class HelloWorld : HttpServlet() {
    protected override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        resp!!.getWriter()!!.print("Hello from Kotlin built with Gradle!\n");
    }
}

fun main(args: Array<String>) {
    val server = Server(Integer.valueOf(System.getenv("PORT")!!)!!);
    val context = ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");
    server.setHandler(context);
    context.addServlet(ServletHolder(HelloWorld()), "/*");
    server.start();
    server.join();
}
