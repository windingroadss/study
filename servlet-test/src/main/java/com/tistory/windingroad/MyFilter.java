package com.tistory.windingroad;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFilter implements Filter {
  public void init(FilterConfig filterConfig) throws ServletException {
    System.out.println("Filter init");
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException {
    System.out.println("Filter doFilter");
    chain.doFilter(request, response); // servlet으로 연결됨
  }

  public void destroy() {
    System.out.println("Filter destroy");
  }
}
