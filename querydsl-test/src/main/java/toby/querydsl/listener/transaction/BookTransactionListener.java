package toby.querydsl.listener.transaction;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import toby.querydsl.domain.entity.Book;
import toby.querydsl.event.SaveBookEvent;

@Component
public class BookTransactionListener {

	
	@EventListener
	public void obatinInsertedBook(SaveBookEvent saveBookEvent) {
		
		Book book = (Book) saveBookEvent.getSource();
		System.err.println(book);
	}
}
