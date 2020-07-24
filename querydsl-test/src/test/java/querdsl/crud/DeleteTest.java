package querdsl.crud;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.dml.SQLDeleteClause;

import toby.querydsl.Application;
import toby.querydsl.domain.qobj.QBook;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { Application.class })
@DisplayName("queryDsl的删除测试")
class DeleteTest {

	@Autowired
	private SQLQueryFactory sqlQueryFactory;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	private SQLDeleteClause sqlDeleteClause;
	private QBook qBook = QBook.book;

	@BeforeEach
	void initDeleteClause() {

		sqlDeleteClause = sqlQueryFactory.delete(qBook);
	}
	
	@Test
	@DisplayName("queryDsl的删除测试-byId")
	void deleteById() {
		
//		long effectCount = sqlDeleteClause.where(qBook.id.eq(4L).or(qBook.id.eq(14L))).execute();
		long effectCount = sqlDeleteClause.where(qBook.id.in(24L, 34L, 44L)).execute();
		assertTrue(effectCount > 0);
	}
}
