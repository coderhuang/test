package querdsl.crud;

import static java.util.Objects.isNull;
import static org.junit.Assert.assertNotNull;

import java.util.Random;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;

import toby.querydsl.Application;
import toby.querydsl.domain.entity.Book;
import toby.querydsl.domain.qobj.QBook;
import toby.querydsl.domain.qobj.QSkuProperty;
import toby.querydsl.util.id.SnowFlakeGenerator;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { Application.class })
@DisplayName("queryDsl的查询测试")
class QueryTest {

	@Autowired
	private SQLQueryFactory sqlQueryFactory;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	QBook qBook = QBook.book;
	SQLQuery<Book> sqlQuery;

	@Test
	@DisplayName("queryDsl查询测试")
	void queryAndOrdering() {

		sqlQuery = sqlQueryFactory.selectFrom(qBook);
		var books = sqlQuery.where(qBook.name.eq("《江城》")).fetch();
		assertNotNull(books);
		books.forEach(System.err::println);
	}

	@Test
	@DisplayName("给skuProperty的skuCode数据赋值")
	void assignSkucode2SkuProperty() {

		QSkuProperty qSkuProperty = QSkuProperty.skuProperty;
		var skuList = sqlQueryFactory.selectFrom(qSkuProperty).fetch();
		assertNotNull(skuList);

		skuList.forEach(sku -> {

			if (isNull(sku.getSkuCode())) {
				sqlQueryFactory.update(qSkuProperty).set(qSkuProperty.skuCode, SnowFlakeGenerator.nextId())
						.where(qSkuProperty.id.eq(sku.getId())).execute();
			}
		});
	}

	@Test
	@DisplayName("给book的skuCode数据赋值")
	void assignSkuCode2Book() throws JsonProcessingException {

		QSkuProperty qSkuProperty = QSkuProperty.skuProperty;
		var skuList = sqlQueryFactory.selectFrom(qSkuProperty).fetch();

		sqlQuery = sqlQueryFactory.selectFrom(qBook);
		var books = sqlQueryFactory.selectFrom(qBook).fetch();

		assertNotNull(skuList);
		assertNotNull(books);

		int skuListSize = skuList.size();
		Random random = new Random(System.currentTimeMillis());
		for (Book book : books) {

			if (isNull(book.getSkuCode())) {

				var sku = skuList.get(random.nextInt(skuListSize));
				sqlQueryFactory.update(qBook).set(qBook.skuCode, sku.getSkuCode()).where(qBook.id.eq(book.getId()))
						.execute();
			}
		}
		
		System.err.println(objectMapper.writeValueAsString(skuList));
		System.err.println(objectMapper.writeValueAsString(books));
	}
}
