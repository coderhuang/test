package toby.oidc.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import toby.oidc.service.ClientPropertiesService;

@Service
public class MyClientDetailsService implements ClientDetailsService {
	
	@Autowired
	private ClientPropertiesService clientPropertiesService;

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		
		
		
		return null;
	}

}
