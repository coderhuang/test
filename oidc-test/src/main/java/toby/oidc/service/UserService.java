package toby.oidc.service;

import toby.oidc.domain.entity.User;

public interface UserService {

	User obtainUserByName(String name);

}
