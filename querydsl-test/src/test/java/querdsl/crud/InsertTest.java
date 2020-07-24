package querdsl.crud;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.querydsl.sql.SQLExpressions;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.dml.SQLInsertClause;

import toby.querydsl.Application;
import toby.querydsl.domain.entity.Book;
import toby.querydsl.domain.entity.SkuProperty;
import toby.querydsl.domain.enums.BookCategory;
import toby.querydsl.domain.qobj.QBook;
import toby.querydsl.domain.qobj.QSkuProperty;
import toby.querydsl.event.SaveBookEvent;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { Application.class })
@DisplayName("queryDsl的插入测试")
class InsertTest {

	@Autowired
	private SQLQueryFactory sqlQueryFactory;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private TransactionTemplate transactionTemplate;

	SQLInsertClause sqlInsertClause;
	QBook qBook = QBook.book;

	@BeforeEach
	void initInsertClause() {

		sqlInsertClause = sqlQueryFactory.insert(qBook);
	}

	Book initBookInfo() {

		var now = LocalDateTime.now();
		var book = new Book();
		book.setName("《江城》");
		book.setAuthor("何伟");
		book.setCategory(BookCategory.OTHERS);
		book.setPrice(new BigDecimal("100.123"));
		book.setCreateTime(now);
		book.setUpdateTime(now);

		return book;
	}

	@Test
	@DisplayName("book数据新增测试")
	@Transactional
	void insertBookTest() {

		sqlInsertClause.set(qBook.name, "《江城》").set(qBook.author, "何伟").set(qBook.category, BookCategory.OTHERS)
				.set(qBook.price, new BigDecimal("100.02")).set(qBook.updateTime, LocalDateTime.now());
		assertTrue(sqlInsertClause.execute() > 0);
	}

	@Test
	@DisplayName("book数据新增测试-3")
	@Transactional
	void insertBookTest_3() {

		sqlInsertClause.set(qBook.name, "《江城》").set(qBook.author, "何伟").set(qBook.category, BookCategory.OTHERS)
				.set(qBook.price, new BigDecimal("100.02")).set(qBook.updateTime, LocalDateTime.now());

		Long id = sqlInsertClause.executeWithKey(Long.class);
		assertNotNull(id);
		System.err.println(id);
	}

	@Test
	@DisplayName("book数据新增测试-1")
	void insertBookTest_1() throws SQLException {

		var book = initBookInfo();
		transactionTemplate.execute(trsansactionStatus -> {

			sqlInsertClause.populate(book).getSQL().forEach(sqlBindings -> System.err.println(sqlBindings.getSQL()));
			var rs = sqlInsertClause.executeWithKeys();
			int effectCount = 0;
			try {
				if (rs.next()) {

					assertTrue((effectCount = rs.getInt(1)) > 0);
					System.err.println(rs.getInt(1));
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			applicationEventPublisher.publishEvent(new SaveBookEvent(book));
			if (TransactionSynchronizationManager.isActualTransactionActive()) {

				TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {

					@Override
					public void afterCompletion(int status) {
						System.err.println("啊哈哈哈哈哈");
					}

					@Override
					public void afterCommit() {
						System.err.println("嗝嗝嗝嗝嗝");
					}

				});
			}
			return effectCount;
		});
		System.err.println("------------done-------------");
	}

	@Test
	@DisplayName("book数据新增测试-2")
	@Transactional
	@RepeatedTest(3)
	void insertBookTest_2() throws SQLException {

		var book = new Book();
		book.setAuthor("何伟");
		book.setName("《甲骨文》");
		book.setPrice(new BigDecimal("123.12345"));
		book.setCategory(BookCategory.HUMANITIES);

		Long id = sqlInsertClause.populate(book).executeWithKey(Long.class);
		assertNotNull(id);
		applicationEventPublisher.publishEvent(new SaveBookEvent(book));
		System.err.println(id);
	}

	@Test
	@DisplayName("book数据新增测试-通过子查询插入")
	void insertBySubquery() {

		Long id = sqlInsertClause.columns(qBook.name, qBook.author, qBook.category, qBook.price, qBook.createTime)
				.select(SQLExpressions.select(qBook.name, qBook.author, qBook.category, qBook.price, qBook.createTime)
						.from(qBook).where(qBook.id.eq(38L)))
				.executeWithKey(Long.class);
		assertNotNull(id);
		System.err.println(id);
	}
	
	AtomicInteger ai = new AtomicInteger(0);
	@Test
	@DisplayName("skuProperty插入")
	@RepeatedTest(30)
	void insert() {
		
		String skuNameBase = "商品目_";
		var skuName = skuNameBase + ai.incrementAndGet();
		var now = LocalDateTime.now();
		
		var skuProperty = new SkuProperty();
		
		skuProperty.setCreateTime(now);
		skuProperty.setUpdateTime(now);
		skuProperty.setSkuName(skuName);
		long effectCount = sqlQueryFactory.insert(QSkuProperty.skuProperty).populate(skuProperty).execute();
		assertTrue(effectCount > 0);
	}

}
