package io.monarch.developer.portal.filter;

import io.monarch.developer.portal.context.ContextHolder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class ContextFilter implements Filter {

  private static final String SESSION_ID_COOKIE_NAME = "mdpSessionId";

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException {

    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    HttpServletResponse httpServletResponse = (HttpServletResponse) response;

    try {
      String sessionId = getSessionIdFromRequestCookie(httpServletRequest);
      if (sessionId == null) {
        sessionId = generateNewSessionId();
        setSessionIdCookieInHttpResponse(httpServletResponse, sessionId);
      }

      ContextHolder.setSessionId(sessionId);
      chain.doFilter(httpServletRequest, httpServletResponse);
    }
    finally {
      ContextHolder.remove();
    }
  }

  @Override
  public void destroy() {
  }

  private static String getSessionIdFromRequestCookie(HttpServletRequest request) {
    String sessionId = null;

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (SESSION_ID_COOKIE_NAME.equals(cookie.getName())) {
          sessionId = cookie.getValue();
        }
      }
    }
    return sessionId;
  }

  private static String generateNewSessionId() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }

  private static void setSessionIdCookieInHttpResponse(HttpServletResponse response, String sessionId) {
    Cookie sessionIdCookie = new Cookie(SESSION_ID_COOKIE_NAME, sessionId);
    sessionIdCookie.setHttpOnly(false);
    sessionIdCookie.setSecure(false);
    response.addCookie(sessionIdCookie);
  }
}
