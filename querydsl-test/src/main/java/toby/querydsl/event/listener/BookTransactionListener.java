package toby.querydsl.event.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import toby.querydsl.domain.entity.Book;
import toby.querydsl.event.BookQueryEvent;
import toby.querydsl.event.SaveBookEvent;

@Component
public class BookTransactionListener {

	
	@EventListener
	public void obatinInsertedBook(SaveBookEvent saveBookEvent) {
		
		Book book = (Book) saveBookEvent.getSource();
		System.err.println(book);
	}
	
	@EventListener
	public void listenQuery(BookQueryEvent bookQueryEvent) {
		
		System.err.println("application event listener:" + bookQueryEvent.getSqlQuery().getSQL().getSQL());
	}
}
