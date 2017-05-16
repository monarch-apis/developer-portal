package io.monarch.developer.portal.web.response;

import lombok.Data;
import lombok.NonNull;

@Data
public class ErrorResponse {

  private Long timestamp;

  @NonNull
  private String message;

  @NonNull
  private String path;

  public ErrorResponse(String message, String path) {
    this.timestamp = System.currentTimeMillis();
    this.message = message;
    this.path = path;
  }
}
