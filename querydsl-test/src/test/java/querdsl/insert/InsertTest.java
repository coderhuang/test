package querdsl.insert;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.dml.SQLInsertClause;

import toby.querydsl.Application;
import toby.querydsl.domain.entity.Book;
import toby.querydsl.domain.enums.BookCategory;
import toby.querydsl.domain.qobj.QBook;
import toby.querydsl.event.SaveBookEvent;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { Application.class })
@DisplayName("queryDsl的插入测试")
class InsertTest {

	@Autowired
	private SQLQueryFactory sqlQueryFactory;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

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
	@DisplayName("book数据新增测试-1")
	@Repeat(3)
	void insertBookTest_1() {
		
		var book = initBookInfo();
		assertTrue(sqlInsertClause.populate(book).execute() > 0);
		applicationEventPublisher.publishEvent(new SaveBookEvent(book));
	}

}
