package io.monarch.developer.portal.context;

public class ContextHolder {
  private static InheritableThreadLocal<String> current = new InheritableThreadLocal<>();

  public static String getSessionId() {
    return current.get();
  }

  public static void setSessionId(String sessionId) {
    current.set(sessionId);
  }

  public static void remove() {
    current.remove();
  }
}
