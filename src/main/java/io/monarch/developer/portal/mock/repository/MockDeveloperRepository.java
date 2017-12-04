package io.monarch.developer.portal.mock.repository;

import io.monarch.developer.portal.model.User;
import io.monarch.developer.portal.repository.DeveloperRepository;

public class MockDeveloperRepository implements DeveloperRepository {

  @Override
  public User getUserByUsernameAndPassword(String username, String password) {
    return new User("1000", username, "Johnny", "Developer");
  }
}
