package toby.oidc.domain.base;

public interface IdAssignEntity<K extends Number & Comparable<?>> {

	void setId(K id);

	K getId();
}
