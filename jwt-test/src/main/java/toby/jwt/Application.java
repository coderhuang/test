package toby.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import toby.jwt.config.filter.JWTFilter;

@SpringBootApplication
@ServletComponentScan(basePackageClasses = {
		JWTFilter.class
})
public class Application {

	public static void main(String... args) {

		SpringApplication.run(Application.class, args);
	}
}
