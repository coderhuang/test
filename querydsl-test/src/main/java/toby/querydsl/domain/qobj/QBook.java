package toby.querydsl.domain.qobj;

import static com.querydsl.core.types.PathMetadataFactory.*;
import toby.querydsl.domain.entity.Book;
import toby.querydsl.domain.enums.BookCategory;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QBook is a Querydsl query type for Book
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QBook extends com.querydsl.sql.RelationalPathBase<Book> {

    private static final long serialVersionUID = -217428281;

    public static final QBook book = new QBook("book");

    public final StringPath author = createString("author");

    public final EnumPath<BookCategory> category = createEnum("category", BookCategory.class);

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final DateTimePath<java.time.LocalDateTime> updateTime = createDateTime("updateTime", java.time.LocalDateTime.class);

    public final com.querydsl.sql.PrimaryKey<Book> primary = createPrimaryKey(id);

    public QBook(String variable) {
        super(Book.class, forVariable(variable), "null", "book");
        addMetadata();
    }

    public QBook(String variable, String schema, String table) {
        super(Book.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QBook(String variable, String schema) {
        super(Book.class, forVariable(variable), schema, "book");
        addMetadata();
    }

    public QBook(Path<? extends Book> path) {
        super(path.getType(), path.getMetadata(), "null", "book");
        addMetadata();
    }

    public QBook(PathMetadata metadata) {
        super(Book.class, metadata, "null", "book");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(author, ColumnMetadata.named("author").withIndex(3).ofType(Types.VARCHAR).withSize(60).notNull());
        addMetadata(category, ColumnMetadata.named("category").withIndex(5).ofType(Types.INTEGER).withSize(7).notNull());
        addMetadata(createTime, ColumnMetadata.named("create_time").withIndex(6).ofType(Types.TIMESTAMP).withSize(19).notNull());
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(20).notNull());
        addMetadata(name, ColumnMetadata.named("name").withIndex(2).ofType(Types.VARCHAR).withSize(60).notNull());
        addMetadata(price, ColumnMetadata.named("price").withIndex(4).ofType(Types.DECIMAL).withSize(10).withDigits(5));
        addMetadata(updateTime, ColumnMetadata.named("update_time").withIndex(7).ofType(Types.TIMESTAMP).withSize(19));
    }

}

