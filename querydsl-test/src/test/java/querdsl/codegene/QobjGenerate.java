package querdsl.codegene;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Properties;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.mysql.jdbc.Driver;
import com.querydsl.codegen.BeanSerializer;
import com.querydsl.sql.Configuration;
import com.querydsl.sql.MySQLTemplates;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.codegen.MetaDataExporter;
import com.querydsl.sql.spring.SpringExceptionTranslator;

import toby.querydsl.common.enums.BookCategory;
import toby.querydsl.common.enums.type.BookCategoryQueryDslType;
import toby.querydsl.domain.entity.IdAssignEntity;

@DisplayName("queryDsl的bean和queryObj的生成类")
class QobjGenerate {

	SQLTemplates mySqlTemplates() {

		return MySQLTemplates.builder()
//				.printSchema()
				.quote()
//				.escape('\\')
//				.newLineToSingleSpace()
				.build();
	}

	Configuration queryFactoryConfiguration() {

		var configuration = new com.querydsl.sql.Configuration(mySqlTemplates());
		configuration.setExceptionTranslator(new SpringExceptionTranslator());
		configuration.register(new BookCategoryQueryDslType());
		configuration.registerType("datetime", LocalDateTime.class);
		configuration.register("book", "category", BookCategory.class);

		return configuration;
	}

	@Test
	@DisplayName("queryDsl的bean和queryObj的生成")
	void generateQobj() throws SQLException {

		Driver driver = new Driver();
		String url = "jdbc:mysql://127.0.0.1:3306/toby_test?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&autoReconnect=true&failOverReadOnly=false&useSSL=false&serverTimezone=UTC";
		Properties info = new Properties();
		info.setProperty("user", "root");
		info.setProperty("password", "nicai123");
		Connection connection = driver.connect(url, info);

		MetaDataExporter exporter = new MetaDataExporter();
		exporter.setPackageName("toby.querydsl.domain.qobj");
		exporter.setBeanPackageName("toby.querydsl.domain.entity");
		exporter.setTargetFolder(new File("src/main/java"));

		var beanSerializer = new BeanSerializer();
		beanSerializer.setAddToString(true);
		beanSerializer.addInterface(IdAssignEntity.class);
		exporter.setBeanSerializer(beanSerializer);
		
		exporter.setConfiguration(queryFactoryConfiguration());

		exporter.export(connection.getMetaData());
	}

}
