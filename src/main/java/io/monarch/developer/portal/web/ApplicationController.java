package io.monarch.developer.portal.web;

import io.monarch.developer.portal.model.Application;
import io.monarch.developer.portal.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/application")
public class ApplicationController {

  @Autowired
  private ApplicationService applicationService;

  @RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
  public List<Application> getApplications() {
    return applicationService.getApplicationsForAuthenticatedDeveloper();
  }
}
