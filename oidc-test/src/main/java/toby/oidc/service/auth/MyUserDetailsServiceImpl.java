package toby.oidc.service.auth;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import toby.oidc.domain.dto.MyUserDetail;
import toby.oidc.domain.entity.User;
import toby.oidc.service.UserService;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsManager {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userService.obtainUserByName(username);
		Objects.requireNonNull(user);
		
		String password = user.getPassword();
		logger.info("name:{};passwod:{}", username, password);
		
		return new MyUserDetail(user);
	}

	@Override
	public void createUser(UserDetails user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(UserDetails user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean userExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}

}
