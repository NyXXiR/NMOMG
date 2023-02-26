package com.study.springboot.service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.*;

@Component
public class SessionManager {


  public static final String SESSION_COOKIE_NAME = "SessionId";
  private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

  public void createSession(Object value, HttpServletResponse response) {
    // 세션 생성
    String sessionId = UUID.randomUUID().toString();
    sessionStore.put(sessionId, value);

    // 쿠키 생성 후 저장
    Cookie cookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
    response.addCookie(cookie);
  }

  public Object getSession(HttpServletRequest request) {
    Cookie cookie = findCookie(request, SESSION_COOKIE_NAME);
    if (cookie == null) {
      return null;
    }
    return sessionStore.get(cookie.getValue());
  }

  public void expire(HttpServletRequest request) {
    Cookie cookie = findCookie(request, SESSION_COOKIE_NAME);
    if (cookie != null) {
      sessionStore.remove(cookie.getValue());
    }
  }

  public Cookie findCookie(HttpServletRequest request, String cookieName) {
    if (request.getCookies() == null) {
      return null;
    }

    return Arrays.stream(request.getCookies()).filter(c -> c.getName().equals(cookieName)).findAny()
        .orElse(null);
  }
}

