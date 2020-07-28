package toby.querydsl.dao;

import java.sql.SQLException;

public interface BaseDao<T, K extends Number & Comparable<?>> {

	/**
	 * 插入数据
	 * 
	 * @param entity
	 * @return 影响条数
	 */
	long insert(T entity);

	/**
	 * 插入数据并将生成的Id写入对象内
	 * 
	 * @param entity
	 * @return 插入成功条数
	 */
	int insertWithKey(T entity) throws SQLException;

	long updateById(T entity);

	long deleteById(K id);

	T selectById(K id);

}
