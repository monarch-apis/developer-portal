package io.monarch.developer.portal.monarch.repository;

import com.monarchapis.api.exception.ApiErrorException;
import com.monarchapis.api.v1.client.ManagementApi;
import com.monarchapis.api.v1.model.Authentication;
import com.monarchapis.api.v1.model.Developer;
import io.monarch.developer.portal.ex.AuthenticationRuntimeException;
import io.monarch.developer.portal.model.User;
import io.monarch.developer.portal.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class MonarchDeveloperRepository implements DeveloperRepository {

  @Autowired
  private ManagementApi managementApi;

  @Override
  public User getUserByUsernameAndPassword(String username, String password) {

    try {
      Authentication authentication = new Authentication();
      authentication.setUsername(username);
      authentication.setPassword(password);

      Developer developer = managementApi.getDevelopersResource().authenticate(authentication);

      return new User(developer.getId(), developer.getUsername(), developer.getFirstName(), developer.getLastName());
    }
    catch (ApiErrorException e) {
      throw new AuthenticationRuntimeException(e);
    }
  }
}
