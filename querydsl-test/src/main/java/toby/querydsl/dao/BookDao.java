package toby.querydsl.dao;

import java.util.List;

import toby.querydsl.domain.entity.Book;

public interface BookDao extends BaseDao<Book, Long> {
	
	List<Book> allBook();
	
}
