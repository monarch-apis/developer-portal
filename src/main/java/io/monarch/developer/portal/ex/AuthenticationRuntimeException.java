package io.monarch.developer.portal.ex;

public class AuthenticationRuntimeException extends RuntimeException {

  public AuthenticationRuntimeException(String message) {
    super(message);
  }

  public AuthenticationRuntimeException(Throwable cause) {
    super(cause);
  }
}
