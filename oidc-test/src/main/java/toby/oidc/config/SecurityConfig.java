package toby.oidc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${user.oauth.user.name}")
	private String username;

	@Value("${user.oauth.user.password}")
	private String password;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {

//		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// Spring Security should completely ignore URLs starting with /resources/
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

//		http.csrf().disable();
		http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login/**").permitAll().anyRequest()
				.authenticated().and().oauth2Login();

		http.requestMatchers().antMatchers("/oauth/**", "/login/**", "/logout/**", "/openid/**").and()
				.authorizeRequests().antMatchers("/oauth/**").authenticated().and().formLogin().permitAll();

	}

	// 配置内存模式的用户
	@Bean
	@Override
	public UserDetailsService userDetailsService() {

		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(
				User.withUsername(username).password(passwordEncoder().encode(password)).authorities("USER").build());
//		manager.createUser(User.withUsername("admin").password(this.passwordEncoder().encode("123456"))
//				.authorities("USER", "ADMIN").build());
		return manager;
//		return new MyUserDetailsServiceImpl();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

}
