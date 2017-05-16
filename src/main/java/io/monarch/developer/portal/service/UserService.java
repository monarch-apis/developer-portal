package io.monarch.developer.portal.service;

import io.monarch.developer.portal.ex.AuthenticationRuntimeException;
import io.monarch.developer.portal.model.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {

  private final static String CACHE = "mdp-session-user-service";

  private final static String CACHE_KEY = "T(io.monarch.developer.portal.context.ContextHolder).getSessionId()";

  @Cacheable(value = CACHE, key = CACHE_KEY)
  public User authenticateByUsernameAndPassword(String username, String password) {
    return new User(String.valueOf(new Random().nextLong()), username, "Johnny", "Developer");
  }

  @Cacheable(value = CACHE, key = CACHE_KEY)
  public User getUser() {
    throw new AuthenticationRuntimeException("No current user found. Please Authenticate.");
  }

  @CacheEvict(value = CACHE, key = CACHE_KEY)
  public void signOut() {
  }
}
