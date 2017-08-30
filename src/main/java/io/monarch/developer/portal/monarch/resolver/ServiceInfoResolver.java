package io.monarch.developer.portal.monarch.resolver;

import com.monarchapis.api.v1.model.ServiceInfo;

public interface ServiceInfoResolver {
	ServiceInfo getServiceInfo(String path);
}
