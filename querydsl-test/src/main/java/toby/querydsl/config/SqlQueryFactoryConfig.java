package toby.querydsl.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.sql.MySQLTemplates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;

import toby.querydsl.domain.enums.type.BookCategoryQueryDslType;

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
	public com.querydsl.sql.Configuration querFactoryConfiguration(SQLTemplates sqlTemplates) {

		var configuration = new com.querydsl.sql.Configuration(sqlTemplates);
		configuration.register(new BookCategoryQueryDslType());
//		configuration.register("book", "category", new BookCategoryQueryDslType());

		return configuration;
	}

	@Bean
	public SQLQueryFactory sqlQueryFactory(com.querydsl.sql.Configuration configuration, DataSource dataSource) {

		return new SQLQueryFactory(configuration, dataSource);
	}
}
