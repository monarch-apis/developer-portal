package io.monarch.developer.portal.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AngularRedirectViewController {

  @RequestMapping({
    "/home",
    "/applications",
    "/application/{id:\\w+}",
    "/services",
    "/service/{id:\\w+}"
  })
  public String index() {
    return "forward:/";
  }
}
