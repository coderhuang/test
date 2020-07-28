package toby.querydsl.dao.impl;

import static java.util.Objects.isNull;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.querydsl.sql.RelationalPathBase;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.dml.SQLDeleteClause;
import com.querydsl.sql.dml.SQLInsertClause;
import com.querydsl.sql.dml.SQLUpdateClause;

import toby.querydsl.dao.BaseDao;
import toby.querydsl.domain.entity.IdAssignEntity;
import toby.querydsl.domain.qobj.IdAssignQObj;

public class BaseDaoImpl<T extends IdAssignEntity<K>, K extends Number & Comparable<?>, M extends RelationalPathBase<T> & IdAssignQObj<K>>
		implements BaseDao<T, K>, ApplicationContextAware {

	private M qObj;

	private SQLQueryFactory sqlQueryFactory;

	protected BaseDaoImpl(M qObj) {

		this.qObj = qObj;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

		sqlQueryFactory = applicationContext.getBean(SQLQueryFactory.class);
	}

	@Override
	public long insert(T entity) {

		SQLInsertClause insertClause = sqlQueryFactory.insert(qObj);
		return insertClause.populate(entity).execute();
	}

	@Override
	public int insertWithKey(final T entity) throws SQLException {

		SQLInsertClause insertClause = sqlQueryFactory.insert(qObj);
		ResultSet resultSet = insertClause.populate(entity).executeWithKeys();
		@SuppressWarnings("unchecked")
		K id = (K) resultSet.getObject(1);
		if (isNull(id)) {

			return 0;
		}

		entity.setId(id);
		return 1;
	}

	@Override
	public long updateById(T entity) {

		SQLUpdateClause updateClause = sqlQueryFactory.update(qObj);
		return updateClause.populate(entity).where(qObj.getId().eq(entity.getId())).execute();
	}

	@Override
	public long deleteById(K id) {

		SQLDeleteClause sqlDeleteClause = sqlQueryFactory.delete(qObj);
		return sqlDeleteClause.where(qObj.getId().eq(id)).execute();
	}

	@Override
	public T selectById(K id) {

		SQLQuery<T> sqlquery = sqlQueryFactory.selectFrom(qObj);
		return sqlquery.where(qObj.getId().eq(id)).fetchFirst();
	}

}
