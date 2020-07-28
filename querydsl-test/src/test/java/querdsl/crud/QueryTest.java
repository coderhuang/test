package querdsl.crud;

import static java.util.Objects.isNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.util.Random;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.QueryFlag.Position;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.sql.SQLExpressions;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;

import toby.querydsl.Application;
import toby.querydsl.common.utils.id.SnowFlakeGenerator;
import toby.querydsl.domain.entity.Book;
import toby.querydsl.domain.qobj.QBook;
import toby.querydsl.domain.qobj.QSkuProperty;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { Application.class })
@DisplayName("queryDsl的查询测试")
class QueryTest {

	@Autowired
	private SQLQueryFactory sqlQueryFactory;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private TransactionTemplate transactionTemplate;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	QBook qBook = QBook.book;
	QSkuProperty qSkuProperty = QSkuProperty.skuProperty;
	SQLQuery<Book> sqlQuery;

	@Test
	@DisplayName("queryDsl查询测试")
	@Transactional(rollbackFor = Exception.class)
	@Rollback(false)
	void testQueryAndOrdering() {

		sqlQuery = sqlQueryFactory.selectFrom(qBook);
		var books = sqlQuery.where(qBook.name.eq("《江城》")).fetch();
		assertNotNull(books);
		books.forEach(System.err::println);
	}

	@Test
	@DisplayName("queryDsl条件查询测试")
	@Transactional(rollbackFor = Exception.class)
	@Rollback(false)
	void testWhereCondition() {

		sqlQuery = sqlQueryFactory.selectFrom(qBook);
		sqlQuery.where(qBook.name.eq("《江城》")).where(qBook.author.eq("何伟"));
		System.err.println(sqlQuery.getSQL().getSQL());
		var bookList = sqlQuery.fetch();
		
		assertFalse(CollectionUtils.isEmpty(bookList));
		
		bookList.forEach(System.err::println);
	}

	@Test
	@DisplayName("测试联表查询")
	@Transactional(rollbackFor = Exception.class)
	@Rollback(false)
	void testQueryJoin() {

		var sqlJoinQuery = sqlQueryFactory.select(qBook.id, qBook.name, qBook.author, qBook.skuCode, qBook.createTime,
				qBook.updateTime, qSkuProperty.skuName);
		var tupleList = sqlJoinQuery.from(qBook).leftJoin(qSkuProperty).on(qBook.skuCode.eq(qSkuProperty.skuCode))
				.fetch();
		System.err.println(sqlJoinQuery.getSQL().getSQL());
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
	@DisplayName("测试flag_bit的位操作查询")
	@Transactional(rollbackFor = Exception.class)
	@Rollback(false)
	void testQueryOrder() {

		sqlQuery = sqlQueryFactory.selectFrom(qBook)
				.where(Expressions.booleanTemplate(qBook.getMetadata(qBook.flagBit).getName() + " & {0} = {0}", 3)
						.and(qBook.author.isNotNull()))
				.orderBy(qBook.id.desc());
		System.err.println(sqlQuery.getSQL().getSQL());
		var bookList = sqlQuery.fetch();
		assertFalse(CollectionUtils.isEmpty(bookList));

		for (Book book : bookList) {
			System.err.println(book);
		}
	}

	@Test
	@DisplayName("测试groupBy的查询")
	@Transactional(rollbackFor = Exception.class)
	@Rollback(false)
	void testQueryGroupingBy() {

		sqlQuery = sqlQueryFactory.selectFrom(qBook).groupBy(qBook.name);
		System.err.println(sqlQuery.getSQL().getSQL());
		var bookList = sqlQuery.fetch();
		assertFalse(CollectionUtils.isEmpty(bookList));

		for (Book book : bookList) {
			System.err.println(book);
		}
	}

	@Test
	@DisplayName("测试子查询")
	@Transactional(rollbackFor = Exception.class)
	@Rollback(false)
	void testSubQuery() {

		sqlQuery = sqlQueryFactory.selectFrom(qBook)
				.where(qBook.id.in(SQLExpressions.select(qBook.id).from(qBook).groupBy(qBook.name)));
		System.err.println(sqlQuery.getSQL().getSQL());
		var bookList = sqlQuery.fetch();
		assertFalse(CollectionUtils.isEmpty(bookList));

		for (Book book : bookList) {
			System.err.println(book);
		}
	}

	@Test
	@DisplayName("测试select literal")
	void testSelectLiteral() {

		var constantSqlQuery = sqlQueryFactory.select(Expressions.constant(1), Expressions.TRUE,
				Expressions.constant("lol"));
		var tupleList = constantSqlQuery.fetch();

		assertFalse(CollectionUtils.isEmpty(tupleList));

		for (Tuple tuple : tupleList) {

			System.err.println(tuple.get(0, Integer.class));
			System.err.println(tuple.get(1, Boolean.class));
			System.err.println(tuple.get(2, String.class));
		}
	}

	@Test
	@DisplayName("测试for update语句等的使用")
	@Rollback(false)
	void testForUpdate() {

		transactionTemplate.execute(transactionStatus -> {

			sqlQuery = sqlQueryFactory.selectFrom(qBook).where(qBook.id.eq(1L)).addFlag(Position.AFTER_FILTERS,
					" FOR UPDATE");
			System.err.println(sqlQuery.getSQL().getSQL());

			var tupleList = sqlQuery.fetch();
			assertFalse(CollectionUtils.isEmpty(tupleList));
			return true;
		});
	}

	@Test
	@DisplayName("给skuProperty的skuCode数据赋值")
	@Transactional
	@Rollback(false)
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
	@Transactional
	@Rollback(false)
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

	@Test
	@DisplayName("给book的flagBit数据赋值")
	@Transactional
	@Rollback(false)
	void testAssignFlagBit2Book() throws JsonProcessingException {

		sqlQuery = sqlQueryFactory.selectFrom(qBook);
		var books = sqlQueryFactory.selectFrom(qBook).where(qBook.flagBit.gt(0)).fetch();

		assertFalse(CollectionUtils.isEmpty(books));

		Random random = new Random(System.currentTimeMillis());
		for (Book book : books) {

			sqlQueryFactory.update(qBook).set(qBook.flagBit, random.nextInt(Integer.MAX_VALUE))
					.where(qBook.id.eq(book.getId())).execute();
		}
	}
}
