package io.monarch.developer.portal.service;

import io.monarch.developer.portal.model.User;

public interface UserService {
  User authenticateByUsernameAndPassword(String username, String password);
  User getUser();
  void signOut();
}
