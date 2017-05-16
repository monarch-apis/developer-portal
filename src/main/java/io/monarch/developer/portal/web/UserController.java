package io.monarch.developer.portal.web;

import io.monarch.developer.portal.ex.AuthenticationRuntimeException;
import io.monarch.developer.portal.model.User;
import io.monarch.developer.portal.service.UserService;
import io.monarch.developer.portal.web.response.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
  public User getUser() {
    return userService.getUser();
  }

  @RequestMapping(value = "/signout", method = RequestMethod.POST)
  public void authenticateUserByUsernameAndPassword() {
    userService.signOut();
  }

  @ExceptionHandler(AuthenticationRuntimeException.class)
  public ResponseEntity<ErrorResponse> handleAuthenticationException(HttpServletRequest request, Exception e) {

    String errorMessage = e.getCause() == null ? e.getMessage() : e.getCause().getMessage();
    ErrorResponse error = new ErrorResponse(errorMessage, request.getRequestURI());
    return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
  }
}
