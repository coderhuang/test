package toby.oidc.service;

import toby.oidc.domain.entity.ClientProperties;

public interface ClientPropertiesService {

	ClientProperties obtainByClientId(Long clientId);
}
