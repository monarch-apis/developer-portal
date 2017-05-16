package io.monarch.developer.portal.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class User {

  @NonNull
  private String id;

  @NonNull
  private String username;

  @NonNull
  private String firstName;

  @NonNull
  private String lastName;
}
