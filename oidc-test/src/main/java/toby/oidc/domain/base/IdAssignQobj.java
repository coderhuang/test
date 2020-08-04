package toby.oidc.domain.base;

import com.querydsl.core.types.dsl.NumberPath;

public interface IdAssignQobj<K extends Number & Comparable<?>> {
	
	NumberPath<K> getId();
}

