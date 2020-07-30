package toby.querydsl.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.sql.MySQLTemplates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.spring.SpringExceptionTranslator;

import toby.querydsl.common.enums.type.BookCategoryQueryDslType;
import toby.querydsl.common.provider.SpringConnectionProvider;
import toby.querydsl.event.listener.PrintSqlListener;

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
		configuration.register(new BookCategoryQueryDslType());
		configuration.addListener(new PrintSqlListener());
//		configuration.register("book", "category", new BookCategoryQueryDslType());

		return configuration;
	}
	
	@Bean
	public SQLQueryFactory sqlQueryFactory(com.querydsl.sql.Configuration configuration, DataSource dataSource) {

		return new SQLQueryFactory(configuration, new SpringConnectionProvider(dataSource, configuration));
	}
}
