package toby.querydsl.listener.transaction;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import toby.querydsl.domain.entity.Book;
import toby.querydsl.event.SaveBookEvent;

@Component
public class BookTransactionListener {

	
	@TransactionalEventListener
	public void obatinInsertedBook(SaveBookEvent saveBookEvent) {
		
		Book book = (Book) saveBookEvent.getSource();
		System.err.println(book.getId());
	}
}
