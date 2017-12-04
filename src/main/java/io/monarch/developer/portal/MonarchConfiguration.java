/*
 * Copyright (C) 2017 CapTech Ventures, Inc.
 * (http://www.captechconsulting.com) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.monarch.developer.portal;

import com.monarchapis.api.v1.client.ManagementApi;
import com.monarchapis.api.v1.client.OpenApi;
import com.monarchapis.api.v1.client.impl.ManagementApiImpl;
import com.monarchapis.api.v1.client.impl.OpenApiImpl;
import com.monarchapis.client.authentication.HawkV1RequestProcessor;
import com.monarchapis.client.rest.RestClientFactory;

import io.monarch.developer.portal.monarch.auth.EnvironmentRequestProcessor;
import io.monarch.developer.portal.monarch.resolver.ServiceInfoResolver;
import io.monarch.developer.portal.monarch.resolver.SingleServiceInfoResolver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MonarchConfiguration {

  @Value("${monarch.environmentName}")
  private String environmentName;

  @Value("${monarch.providerKey}")
  private String providerKey;

  @Value("${monarch.sharedSecret}")
  private String sharedSecret;

  @Value("${monarch.url}/open/v1")
  private String openApiUrl;

  @Value("${monarch.url}/management/v1")
  private String managementApiUrl;

  @Bean
  public RestClientFactory restClientFactory() {
    return new RestClientFactory();
  }

  @Bean
  public ServiceInfoResolver serviceInfoResolver(OpenApi openApi) {
    return new SingleServiceInfoResolver(openApi, environmentName, providerKey);
  }

  @Bean
  public EnvironmentRequestProcessor environmentRequestProcessor(ServiceInfoResolver serviceInfoResolver) {
    return new EnvironmentRequestProcessor(serviceInfoResolver);
  }

  @Bean
  public HawkV1RequestProcessor hawkV1RequestProcessor() {
    return new HawkV1RequestProcessor(providerKey, sharedSecret, "sha256");
  }

  @Bean
  public OpenApi openApi(RestClientFactory restClientFactory) {
    return new OpenApiImpl(openApiUrl, restClientFactory);
  }

  @Bean
  public ManagementApi managementApi(RestClientFactory restClientFactory, EnvironmentRequestProcessor eventProcessor,
                                     HawkV1RequestProcessor hawkV1RequestSigner) {
    return new ManagementApiImpl(managementApiUrl, restClientFactory, eventProcessor, hawkV1RequestSigner);
  }
}
