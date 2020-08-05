package toby.oidc.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.sql.SQLQueryFactory;

import toby.oidc.dao.UserDao;
import toby.oidc.dao.base.BaseDaoImpl;
import toby.oidc.domain.entity.User;
import toby.oidc.domain.qobj.QUser;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Long, QUser> implements UserDao {

	@Autowired
	private SQLQueryFactory sqlQueryFactory;

	protected UserDaoImpl() {

		super(QUser.user);
	}

	@Override
	public User selectByName(String name) {

		return sqlQueryFactory.selectFrom(qObj).where(qObj.name.eq(name)).fetchOne();
	}

}
