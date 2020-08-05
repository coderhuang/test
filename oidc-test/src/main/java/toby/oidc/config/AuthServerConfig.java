package toby.oidc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Value("${user.oauth.client-id}")
	private String ClientID;

	@Value("${user.oauth.client-secret}")
	private String ClientSecret;

	@Value("${user.oauth.redirect-uri}")
	private String RedirectURL;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {

		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		clients.inMemory().withClient(ClientID).secret(passwordEncoder.encode(ClientSecret))
				.authorizedGrantTypes("authorization_code").scopes("user_info").autoApprove(true)
				.redirectUris(RedirectURL);
//		clients.withClientDetails(new MyClientDetailsService());
	}
}
