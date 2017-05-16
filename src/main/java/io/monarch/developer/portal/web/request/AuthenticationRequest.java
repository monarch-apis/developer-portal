package io.monarch.developer.portal.web.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class AuthenticationRequest {

  @NonNull
  private String username;

  @NonNull
  private String password;
}
