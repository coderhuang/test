package querdsl.crud;

import static java.util.Objects.isNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Random;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.Tuple;
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
	QSkuProperty qSkuProperty = QSkuProperty.skuProperty;
	SQLQuery<Book> sqlQuery;

	@Test
	@DisplayName("queryDsl查询测试")
	void testQueryAndOrdering() {

		sqlQuery = sqlQueryFactory.selectFrom(qBook);
		var books = sqlQuery.where(qBook.name.eq("《江城》")).fetch();
		assertNotNull(books);
		books.forEach(System.err::println);
	}

	@Test
	@DisplayName("测试联表查询")
	void testQueryJoin() {

		var sqlJoinQuery = sqlQueryFactory.select(qBook.id, qBook.name, qBook.author, qBook.skuCode, qBook.createTime,
				qBook.updateTime, qSkuProperty.skuName);
		var tupleList = sqlJoinQuery.from(qBook).leftJoin(qSkuProperty).on(qBook.skuCode.eq(qSkuProperty.skuCode))
				.fetch();
		assertFalse(CollectionUtils.isEmpty(tupleList));

		for (Tuple tuple : tupleList) {

			System.err.println(String.format("%1$s,%2$s,%3$s,%4$s,%5$s,%6$s,%7$s", tuple.get(qBook.id),
					tuple.get(qBook.name), tuple.get(qBook.author), tuple.get(qBook.skuCode),
					tuple.get(qBook.createTime), tuple.get(qBook.updateTime), tuple.get(qSkuProperty.skuName)));
		}
		System.err.println("----------------邪恶的分割线-------------------------");
		for (Tuple tuple : tupleList) {

			System.err.println(String.format("%1$s,%2$s,%3$s,%4$s,%5$s,%6$s,%7$s", tuple.get(0, Long.class),
					tuple.get(1, String.class), tuple.get(2, String.class), tuple.get(3, Long.class),
					tuple.get(4, LocalDateTime.class), tuple.get(5, LocalDateTime.class), tuple.get(6, String.class)));
		}
	}

	@Test
	@DisplayName("给skuProperty的skuCode数据赋值")
	void testSssignSkucode2SkuProperty() {

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
	void testAssignSkuCode2Book() throws JsonProcessingException {

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
