package io.monarch.developer.portal.monarch.auth;

import com.monarchapis.api.v1.model.ServiceInfo;
import com.monarchapis.client.rest.BaseClient;
import com.monarchapis.client.rest.RequestProcessor;
import io.monarch.developer.portal.monarch.resolver.ServiceInfoResolver;

public class EnvironmentRequestProcessor implements RequestProcessor {

	private ServiceInfoResolver serviceInfoResolver;

	public EnvironmentRequestProcessor(ServiceInfoResolver serviceInfoResolver) {
		this.serviceInfoResolver = serviceInfoResolver;
	}

	public ServiceInfoResolver getServiceInfoResolver() {
		return serviceInfoResolver;
	}

	public void setServiceInfoResolver(ServiceInfoResolver serviceInfoResolver) {
		this.serviceInfoResolver = serviceInfoResolver;
	}

	@Override
	public void processRequest(BaseClient<?> client) {
		ServiceInfo serviceInfo = serviceInfoResolver.getServiceInfo(client.getPath());

		client.addHeader("X-Environment-Id", serviceInfo.getEnvironment().getId());
	}
}
