package toby.oidc.dao.impl;

import org.springframework.stereotype.Repository;

import toby.oidc.dao.ClientPropertiesDao;
import toby.oidc.dao.base.BaseDaoImpl;
import toby.oidc.domain.entity.ClientProperties;
import toby.oidc.domain.qobj.QClientProperties;

@Repository
public class ClientPropertiesDaoImpl extends BaseDaoImpl<ClientProperties, Long, QClientProperties>
		implements ClientPropertiesDao {
	
	protected ClientPropertiesDaoImpl() {

		super(QClientProperties.clientProperties);
	}

	@Override
	public ClientProperties selectByClientId(Long clientId) {
		
		return sqlQueryFactory.selectFrom(qObj).where(qObj.clientId.eq(clientId)).fetchFirst();
	}

}
