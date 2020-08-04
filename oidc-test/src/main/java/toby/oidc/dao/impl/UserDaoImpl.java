package toby.oidc.dao.impl;

import toby.oidc.dao.UserDao;
import toby.oidc.dao.base.BaseDaoImpl;
import toby.oidc.domain.entity.User;
import toby.oidc.domain.qobj.QUser;

public class UserDaoImpl extends BaseDaoImpl<User, Long, QUser> implements UserDao {

	protected UserDaoImpl() {

		super(QUser.user);
	}

}
