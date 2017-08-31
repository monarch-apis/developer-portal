package io.monarch.developer.portal.service;

import io.monarch.developer.portal.model.Application;
import io.monarch.developer.portal.model.DeveloperRole;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ApplicationService {

  public List<Application> getApplicationsForAuthenticatedDeveloper() {

    // Build and return some mock data

    Application[] applications = new Application[3];

    applications[0] = new Application("1000", "URL Shortener", DeveloperRole.ADMINISTRATOR, new Date());
    applications[0].setDescription("Shortens URLs to facilitate sharing on social media");
    applications[0].setApplicationUrl("https://shortener.example.com");
    applications[0].setModifiedTimestamp(new Date());

    applications[1] = new Application("1001", "Employee Directory", DeveloperRole.DEVELOPER, new Date());
    applications[1].setDescription("The ultimate employee directory application");
    applications[1].setApplicationUrl("https://employees.example.com");
    applications[1].setApplicationImageUrl("https://employees.example.com/img/logo.jpg");

    applications[2] = new Application("1002", "Timecard Manager", DeveloperRole.OPERATOR, new Date());
    applications[2].setDescription("The ultimate timecard management application");
    applications[2].setApplicationUrl("https://time.example.com");
    applications[2].setApplicationImageUrl("https://time.example.com/img/logo.jpg");

    return Arrays.asList(applications);
  }
}
