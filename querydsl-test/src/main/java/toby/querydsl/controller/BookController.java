package toby.querydsl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import toby.querydsl.domain.entity.Book;
import toby.querydsl.service.BookService;

@RestController
@RequestMapping(path = "/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping(path = "/all")
	public List<Book> allBook() {

		return bookService.allBook();
	}

}
