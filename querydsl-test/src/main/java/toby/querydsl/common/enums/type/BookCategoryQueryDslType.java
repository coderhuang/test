package toby.querydsl.common.enums.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.querydsl.sql.types.Type;

import toby.querydsl.common.enums.BookCategory;
import toby.querydsl.common.enums.base.IntegerBaseEnumInterface;

public class BookCategoryQueryDslType implements Type<BookCategory> {

	@Override
	public int[] getSQLTypes() {

		return new int[] { Types.BLOB };
	}

	@Override
	public Class<BookCategory> getReturnedClass() {

		return BookCategory.class;
	}

	@Override
	public String getLiteral(BookCategory value) {

		return value.getLabel();
	}

	@Override
	public BookCategory getValue(ResultSet rs, int startIndex) throws SQLException {

		return IntegerBaseEnumInterface.getEnum(BookCategory.class, rs.getInt(startIndex));
	}

	@Override
	public void setValue(PreparedStatement st, int startIndex, BookCategory value) throws SQLException {

		st.setInt(startIndex, value.getCode());
	}

}
