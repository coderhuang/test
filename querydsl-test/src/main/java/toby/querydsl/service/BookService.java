package toby.querydsl.service;

import java.util.List;

import toby.querydsl.domain.entity.Book;

public interface BookService {

	List<Book> allBook();
	
	Book queryBookById(Long id);
}
