package io.monarch.developer.portal;

import io.monarch.developer.portal.filter.ContextFilter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

@SpringBootApplication
@EnableCaching
public class Application extends SpringBootServletInitializer {
  public static void main(String[] args) {
    new Application().configure(new SpringApplicationBuilder(Application.class)).run(args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(Application.class);
  }

  @Bean
  public Filter contextFilter() {
    return new ContextFilter();
  }
}
