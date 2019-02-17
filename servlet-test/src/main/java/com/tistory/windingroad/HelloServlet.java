package com.tistory.windingroad;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

public class HelloServlet extends HttpServlet {

  @Override
  public void init() throws ServletException {
    System.out.println("servlet init");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    System.out.println("servlet doGet");

    ApplicationContext context = (ApplicationContext) getServletContext().getAttribute(
      WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
    HelloService helloService = context.getBean(HelloService.class);

    resp.getWriter().println("<html>");
    resp.getWriter().println("<body>");
    resp.getWriter().println("Hello Servlet, " + getName());
    resp.getWriter().println("<br/>");
    resp.getWriter().println("Hello Service, " + helloService.getName());
    resp.getWriter().println("</body>");
    resp.getWriter().println("</html>");
  }

  @Override
  public void destroy() {
    System.out.println("destroy");
  }

  private Object getName() {
    return getServletContext().getAttribute("name");
  }
}
