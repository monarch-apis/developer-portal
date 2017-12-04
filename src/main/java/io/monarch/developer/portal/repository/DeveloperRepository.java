package io.monarch.developer.portal.repository;

import io.monarch.developer.portal.model.User;

public interface DeveloperRepository {
  User getUserByUsernameAndPassword(String username, String password);
}
