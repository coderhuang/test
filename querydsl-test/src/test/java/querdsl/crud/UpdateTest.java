package querdsl.crud;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
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

import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.dml.SQLUpdateClause;

import toby.querydsl.Application;
import toby.querydsl.domain.entity.Book;
import toby.querydsl.domain.qobj.QBook;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { Application.class })
@DisplayName("queryDsl的更新测试")
class UpdateTest {

	@Autowired
	private SQLQueryFactory sqlQueryFactory;

	@Autowired
	private TransactionTemplate transactionTemplate;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	private SQLUpdateClause sqlUpdateClause;
	private QBook qBook = QBook.book;

	@BeforeEach
	void initUpdateClause() {

		sqlUpdateClause = sqlQueryFactory.update(qBook);
	}

	@Test
	@DisplayName("queryDsl的更新测试")
	@Transactional
	@Rollback(false)
	void updateByIdTest() {

		long effctCount = sqlUpdateClause.set(qBook.updateTime, LocalDateTime.now()).where(qBook.id.eq(33L)).execute();
		assertTrue(effctCount > 0);
	}

	@Test
	@DisplayName("queryDsl的更新populate测试")
	@Transactional
	@Rollback(false)
	void updatePopulateTest() {

		var book = new Book();
		book.setId(34L);
		book.setUpdateTime(LocalDateTime.now());
		long effctCount = sqlUpdateClause.populate(book).where(qBook.id.eq(34L)).execute();
		assertTrue(effctCount > 0);
	}

	@Test
	@DisplayName("queryDsl的更新事务测试")
	@Rollback(false)
	void updateTransactionTest() {

		boolean flag = transactionTemplate.execute(transactionStatus -> {

			long effctCount = sqlUpdateClause.set(qBook.updateTime, LocalDateTime.now()).where(qBook.id.eq(77L))
					.execute();
//			throw new IllegalStateException("测试，所以抛出异常");
			if (effctCount > 0) {

				return true;
			}

			return false;
		});
		assertTrue(flag);
	}

}
