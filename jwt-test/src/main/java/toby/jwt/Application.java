package toby.jwt;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;

import toby.jwt.config.filter.JWTFilter;

@SpringBootApplication
@ServletComponentScan(basePackageClasses = { JWTFilter.class })
public class Application {

	public static void main(String... args) {

//		SpringApplication.run(Application.class, args);
		new SpringApplicationBuilder().sources(Application.class).web(WebApplicationType.SERVLET).run(args);
	}
}
