package toby.querydsl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import toby.querydsl.dao.BookDao;
import toby.querydsl.domain.entity.Book;
import toby.querydsl.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookDao bookDao;

	@Override
	public List<Book> allBook() {
		
		return bookDao.allBook();
	}

	@Override
	public Book queryBookById(Long id) {
		
		return bookDao.selectById(id);
	}

}
