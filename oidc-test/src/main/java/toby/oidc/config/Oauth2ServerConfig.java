package toby.oidc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@AutoConfigureAfter(SecurityConfig.class)
public class Oauth2ServerConfig {

//	private static final String DEMO_RESOURCE_ID = "order";
//
//	@Autowired
//	DataSource dataSource;
//
//	@Bean
//	public TokenStore tokenStore() {
//
//		JdbcTokenStore tokenStore = new JdbcTokenStore(dataSource);
//
//		return tokenStore;
//
//	}
//
//	/**
//	 * authorization_code认证时将认证码存储在数据库中
//	 *
//	 * @return
//	 */
//	@Bean
//	public AuthorizationCodeServices authorizationCodeServices() {
//		
//		return new JdbcAuthorizationCodeServices(dataSource);
//	}
//
//	/**
//	 * 保存已经允许的应用，避免二次处理
//	 *
//	 * @return
//	 */
//	@Bean
//	public ApprovalStore approvalStore() {
//		
//		return new JdbcApprovalStore(dataSource);
//	}
//
//	@Configuration
//	@EnableResourceServer
//	protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//		@Autowired
//		AuthenticationManager authenticationManager;
//
//		@Override
//		public void configure(ResourceServerSecurityConfigurer resources) {
//
//			resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
//		}
//
//		@Override
//		public void configure(HttpSecurity http) throws Exception {
//			http.authorizeRequests().antMatchers("/user/**").access("#oauth2.hasScope('read') and hasRole('USER')");
//			http.authorizeRequests().antMatchers("/client/**")
//					.access("#oauth2.hasScope('read') and #oauth2.clientHasRole('root')");
//			http.authorizeRequests().antMatchers("/oauth/**").permitAll();
//			http.authorizeRequests().anyRequest().permitAll();
//		}
//
//	}
//
//	@Configuration
//	@EnableAuthorizationServer
//	protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
//
//		@Autowired
//		AuthenticationManager authenticationManager;
//
//		@Autowired
//		PasswordEncoder passwordEncoder;
//		@Autowired
//		DataSource dataSource;
//
//		@Autowired
//		TokenStore tokenStore;
//
//		@Autowired
//		AuthorizationCodeServices authorizationCodeServices;
//
//		@Autowired
//		ApprovalStore approvalStore;
//		@Autowired
//		UserDetailsService userDetailsService;
//
//		@Override
//		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//
////        password 方案一：明文存储，用于测试，不能用于生产
////        String finalSecret = "123456";
////        password 方案二：用 BCrypt 对密码编码
////        String finalSecret = new BCryptPasswordEncoder().encode("123456");
//			// password 方案三：支持多种编码，通过密码的前缀区分编码方式
//			String finalSecret = passwordEncoder.encode("123456");
////            //配置两个客户端,一个用于password认证一个用于client认证
//
////            JdbcClientDetailsService jdbcClientDetailsService=  (JdbcClientDetailsService)  new JdbcClientDetailsServiceBuilder().dataSource(dataSource).build();
////            BaseClientDetails clientDetails=new BaseClientDetails();
////            clientDetails.setClientId("client_1");
////            clientDetails.setResourceIds(Arrays.asList(DEMO_RESOURCE_ID));
////            clientDetails.setAuthorizedGrantTypes(Arrays.asList("client_credentials", "refresh_token"));
////            clientDetails.setScope(Arrays.asList("select"));
////            clientDetails.setAuthorities(Arrays.asList(new SimpleGrantedAuthority("oauth2")));
////            clientDetails.setClientSecret(finalSecret);
////            jdbcClientDetailsService.addClientDetails(clientDetails);
////
////            clientDetails=new BaseClientDetails();
////
////            clientDetails.setClientId("client_2");
////            clientDetails.setResourceIds(Arrays.asList(DEMO_RESOURCE_ID));
////            clientDetails.setAuthorizedGrantTypes(Arrays.asList("password", "refresh_token"));
////            clientDetails.setScope(Arrays.asList("select"));
////            clientDetails.setAuthorities(Arrays.asList(new SimpleGrantedAuthority("oauth2")));
////            clientDetails.setClientSecret(finalSecret);
////            jdbcClientDetailsService.addClientDetails(clientDetails);
//
//			clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
//
//		}
//
//		@Override
//		public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
//
//			endpoints.approvalStore(approvalStore).tokenStore(tokenStore).authenticationManager(authenticationManager)
//					.userDetailsService(userDetailsService).authorizationCodeServices(authorizationCodeServices)
//
//					.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
//
//		}
//
//		@Override
//		public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
//
//			// 允许表单认证
//			oauthServer.allowFormAuthenticationForClients();
//			// 默认的 authenticationManager 是错的，手动设置
//			oauthServer.addTokenEndpointAuthenticationFilter(
//					new BasicAuthenticationFilter(authenticationManager, new BasicAuthenticationEntryPoint()));
//		}
//
//	}
//
//	@Configuration
//	@AutoConfigureAfter(AuthorizationServerEndpointsConfiguration.class)
//	public static class AuthorizationServerConfigurationAfter {
//		/**
//		 * token 时间设置
//		 *
//		 * @param tokenServices
//		 */
//		@Autowired
//		public void setRefreshTokenValiditySeconds(AuthorizationServerTokenServices tokenServices) {
//			if (tokenServices instanceof DefaultTokenServices) {
//				DefaultTokenServices services = (DefaultTokenServices) tokenServices;
////                services.setRefreshTokenValiditySeconds(60);
////                services.setAccessTokenValiditySeconds(30);
//			}
//
//		}
//	}
}
