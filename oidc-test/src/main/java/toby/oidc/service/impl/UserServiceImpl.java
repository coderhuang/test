package toby.oidc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import toby.oidc.dao.UserDao;
import toby.oidc.domain.entity.User;
import toby.oidc.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User obtainUserByName(String name) {

		return userDao.selectByName(name);
	}

}
