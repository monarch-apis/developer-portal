package io.monarch.developer.portal;

import io.monarch.developer.portal.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

  @Value("${portal.repository.developer}")
  private String developerRepository;

  @Bean
  public DeveloperRepository developerRepository() throws Exception {
    return (DeveloperRepository) Class.forName(developerRepository).newInstance();
  }
}
