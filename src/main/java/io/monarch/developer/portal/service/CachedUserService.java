package io.monarch.developer.portal.service;

import io.monarch.developer.portal.ex.AuthenticationRuntimeException;
import io.monarch.developer.portal.model.User;
import io.monarch.developer.portal.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CachedUserService implements UserService {

  private final static String CACHE = "mdp-session-user-service";

  private final static String CACHE_KEY = "T(io.monarch.developer.portal.context.ContextHolder).getSessionId()";

  @Autowired
  private DeveloperRepository developerRepository;

  @Override
  @Cacheable(value = CACHE, key = CACHE_KEY)
  public User authenticateByUsernameAndPassword(String username, String password) {
    return developerRepository.getUserByUsernameAndPassword(username, password);
  }

  @Override
  @Cacheable(value = CACHE, key = CACHE_KEY)
  public User getUser() {
    throw new AuthenticationRuntimeException("No current user found. Please Authenticate.");
  }

  @Override
  @CacheEvict(value = CACHE, key = CACHE_KEY)
  public void signOut() {
  }
}
