package toby.querydsl.domain.entity;

public interface IdAssignEntity<K extends Number & Comparable<?>> {

	void setId(K id);
	
	K getId();
}
