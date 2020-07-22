package toby.querydsl.event;

import org.springframework.context.ApplicationEvent;

import toby.querydsl.domain.entity.Book;

public class SaveBookEvent extends ApplicationEvent {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1116161946995565909L;

	public SaveBookEvent(Book book) {
		
		super(book);
	}

}
