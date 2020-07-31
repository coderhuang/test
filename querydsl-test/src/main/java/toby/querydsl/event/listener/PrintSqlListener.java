package toby.querydsl.event.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.querydsl.sql.SQLBaseListener;
import com.querydsl.sql.SQLListenerContext;

public class PrintSqlListener extends SQLBaseListener {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void preExecute(SQLListenerContext context) {

		logger.info("SQL执行前-SQL语句输出:{}", context.getSQL());
	}

	@Override
	public void executed(SQLListenerContext context) {
		
		context.getPreparedStatements().forEach(ps -> logger.info("SQL执行后-SQL语句输出: PreparedStatement:{}", ps.toString()));
	}
}
