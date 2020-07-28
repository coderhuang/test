package toby.querydsl.domain.qobj;

import com.querydsl.core.types.dsl.NumberPath;

public interface IdAssignQObj<K extends Number & Comparable<?>> {
	
	NumberPath<K> getId();
}
