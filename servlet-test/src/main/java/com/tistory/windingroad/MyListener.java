package com.tistory.windingroad;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {

  public void contextInitialized(ServletContextEvent sce) {
    System.out.println("contextInitialized");
    sce.getServletContext().setAttribute("name", "ked");
  }

  public void contextDestroyed(ServletContextEvent sce) {
    System.out.println("contextDestroyed");
  }
}
