package toby.querydsl.event;

import com.querydsl.sql.SQLQuery;

import toby.querydsl.domain.entity.Book;

public class BookQueryEvent {

	private SQLQuery<Book> sqlQuery;

	public BookQueryEvent() {
	}

	public BookQueryEvent(SQLQuery<Book> sqlQuery) {

		this.sqlQuery = sqlQuery;
	}

	public SQLQuery<Book> getSqlQuery() {
		return sqlQuery;
	}

	public void setSqlQuery(SQLQuery<Book> sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	@Override
	public String toString() {
		return "BookQueryEvent [sqlQuery=" + sqlQuery + "]";
	}
}
