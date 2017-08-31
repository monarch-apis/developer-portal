package io.monarch.developer.portal.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
@JsonInclude(Include.NON_NULL)
public class Application {

  @NonNull
  private String id;

  @NonNull
  private String name;

  private String description;

  @NonNull
  private DeveloperRole role;

  private String applicationUrl;

  private String applicationImageUrl;

  @NonNull
  private Date createdTimestamp;

  private Date modifiedTimestamp;
}
