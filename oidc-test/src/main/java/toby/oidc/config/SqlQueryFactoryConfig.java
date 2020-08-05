package toby.oidc.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.sql.MySQLTemplates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.spring.SpringExceptionTranslator;

import toby.oidc.common.provider.SpringConnectionProvider;
import toby.oidc.event.listener.PrintSqlListener;

@Configuration
public class SqlQueryFactoryConfig {

	@Bean
	public SQLTemplates mySqlTemplates() {

		return MySQLTemplates.builder()
//				.printSchema()
				.quote()
//				.escape('\\')
//				.newLineToSingleSpace()
				.build();
	}

	@Bean
	public com.querydsl.sql.Configuration queryFactoryConfiguration(SQLTemplates sqlTemplates) {

		var configuration = new com.querydsl.sql.Configuration(sqlTemplates);
		configuration.setExceptionTranslator(new SpringExceptionTranslator());
		configuration.addListener(new PrintSqlListener());
//		configuration.register("book", "category", new BookCategoryQueryDslType());

		return configuration;
	}
	
	@Bean
	public SQLQueryFactory sqlQueryFactory(com.querydsl.sql.Configuration configuration, DataSource dataSource) {

		return new SQLQueryFactory(configuration, new SpringConnectionProvider(dataSource, configuration));
	}
}
