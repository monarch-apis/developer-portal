package io.monarch.developer.portal.web;

import io.monarch.developer.portal.ex.AuthenticationRuntimeException;
import io.monarch.developer.portal.service.UserService;
import io.monarch.developer.portal.web.request.AuthenticationRequest;
import io.monarch.developer.portal.web.response.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
  public void authenticateUserByUsernameAndPassword(@RequestBody AuthenticationRequest authenticationRequest) {

    String username = authenticationRequest.getUsername();
    String password = authenticationRequest.getPassword();

    userService.authenticateByUsernameAndPassword(username, password);
  }

  @ExceptionHandler(AuthenticationRuntimeException.class)
  public ResponseEntity<ErrorResponse> handleAuthenticationException(HttpServletRequest request, Exception e) {
    ErrorResponse error = new ErrorResponse(e.getCause().getMessage(), request.getRequestURI());
    return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
  }
}
