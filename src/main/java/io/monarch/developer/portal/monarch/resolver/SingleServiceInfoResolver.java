package io.monarch.developer.portal.monarch.resolver;

import com.monarchapis.api.v1.client.OpenApi;
import com.monarchapis.api.v1.client.OpenResource;
import com.monarchapis.api.v1.model.ServiceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SingleServiceInfoResolver implements ServiceInfoResolver {
	private static Logger logger = LoggerFactory.getLogger(SingleServiceInfoResolver.class);

	private OpenApi openApi;
	private String environmentName;
	private String providerKey;

	private ServiceInfo serviceInfo;

	public SingleServiceInfoResolver(OpenApi openApi, String environmentName, String providerKey) {
		this.openApi = openApi;
		this.environmentName = environmentName;
        this.providerKey = providerKey;
	}

	@Override
	public ServiceInfo getServiceInfo(String path) {
		if (serviceInfo == null) {
			OpenResource open = openApi.getOpenResource();
			serviceInfo = open.getServiceInfo(environmentName, null, providerKey);
            logger.debug("Retrieved Service Information for environment {}", environmentName);
		}

		return serviceInfo;
	}
}
