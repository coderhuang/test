package querdsl.crud;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.support.TransactionTemplate;

import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;

import toby.querydsl.Application;
import toby.querydsl.domain.entity.Book;
import toby.querydsl.domain.qobj.QBook;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { Application.class })
@DisplayName("queryDsl的查询测试")
class QueryTest {

	@Autowired
	private SQLQueryFactory sqlQueryFactory;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private TransactionTemplate transactionTemplate;

	
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
}
