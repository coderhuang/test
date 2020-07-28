package toby.querydsl.dao.impl;

import org.springframework.stereotype.Repository;

import toby.querydsl.dao.BookDao;
import toby.querydsl.domain.entity.Book;
import toby.querydsl.domain.qobj.QBook;

@Repository
public class BookDaoImpl extends BaseDaoImpl<Book, Long, QBook> implements BookDao {

	public BookDaoImpl() {
		
		super(QBook.book);
	}
	
}
