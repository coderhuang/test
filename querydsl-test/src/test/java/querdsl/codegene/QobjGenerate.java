package querdsl.codegene;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.jupiter.api.Test;

import com.mysql.jdbc.Driver;
import com.querydsl.sql.codegen.MetaDataExporter;


class QobjGenerate {
	
	@Test
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
		
		exporter.export(connection.getMetaData());
	}

}
