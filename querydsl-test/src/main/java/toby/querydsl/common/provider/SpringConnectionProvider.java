package toby.querydsl.common.provider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

import javax.inject.Provider;
import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

import com.querydsl.core.QueryException;
import com.querydsl.sql.Configuration;
import com.querydsl.sql.SQLBaseListener;
import com.querydsl.sql.SQLListenerContext;

public class SpringConnectionProvider extends SQLBaseListener implements Provider<Connection> {

	private DataSource dataSource;

	public SpringConnectionProvider(DataSource dataSource, Configuration configuration) {

		this.dataSource = Objects.requireNonNull(dataSource, "dataSource require nonnull");
		Objects.requireNonNull(configuration).addListener(this);
	}

	@Override
	public Connection get() {

		return DataSourceUtils.getConnection(dataSource);
	}

	@Override
	public void end(SQLListenerContext context) {

		Connection connection = context.getConnection();
		try {
			// 主动释放没有被Spring事务管理的连接
			if (connection != null && !connection.isClosed()
					&& !DataSourceUtils.isConnectionTransactional(connection, this.dataSource)) {
				DataSourceUtils.doCloseConnection(connection, this.dataSource);
			}
		} catch (SQLException e) {

			throw new QueryException(e);
		}
	}
}
