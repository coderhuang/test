package toby.oidc.dao;

import toby.oidc.dao.base.BaseDao;
import toby.oidc.domain.entity.ClientProperties;

public interface ClientPropertiesDao extends BaseDao<ClientProperties, Long> {
	
	
	ClientProperties selectByClientId(Long clientId);

}
