package toby.oidc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import toby.oidc.dao.ClientPropertiesDao;
import toby.oidc.domain.entity.ClientProperties;
import toby.oidc.service.ClientPropertiesService;

@Service
public class ClientPropertiesServiceImpl implements ClientPropertiesService {

	@Autowired
	private ClientPropertiesDao clientPropertiesDao;

	@Override
	public ClientProperties obtainByClientId(Long clientId) {

		return clientPropertiesDao.selectByClientId(clientId);
	}

}
