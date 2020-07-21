package toby.querydsl.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QBook is a Querydsl query type for QBook
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QBook extends com.querydsl.sql.RelationalPathBase<QBook> {

    private static final long serialVersionUID = 1862170332;

    public static final QBook book = new QBook("book");

    public final StringPath author = createString("author");

    public final DateTimePath<java.sql.Timestamp> createTime = createDateTime("createTime", java.sql.Timestamp.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final DateTimePath<java.sql.Timestamp> updateTime = createDateTime("updateTime", java.sql.Timestamp.class);

    public final com.querydsl.sql.PrimaryKey<QBook> primary = createPrimaryKey(id);

    public QBook(String variable) {
        super(QBook.class, forVariable(variable), "null", "book");
        addMetadata();
    }

    public QBook(String variable, String schema, String table) {
        super(QBook.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QBook(String variable, String schema) {
        super(QBook.class, forVariable(variable), schema, "book");
        addMetadata();
    }

    public QBook(Path<? extends QBook> path) {
        super(path.getType(), path.getMetadata(), "null", "book");
        addMetadata();
    }

    public QBook(PathMetadata metadata) {
        super(QBook.class, metadata, "null", "book");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(author, ColumnMetadata.named("author").withIndex(3).ofType(Types.VARCHAR).withSize(60).notNull());
        addMetadata(createTime, ColumnMetadata.named("create_time").withIndex(5).ofType(Types.TIMESTAMP).withSize(26).notNull());
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(20).notNull());
        addMetadata(name, ColumnMetadata.named("name").withIndex(2).ofType(Types.VARCHAR).withSize(60).notNull());
        addMetadata(price, ColumnMetadata.named("price").withIndex(4).ofType(Types.DECIMAL).withSize(10));
        addMetadata(updateTime, ColumnMetadata.named("update_time").withIndex(6).ofType(Types.TIMESTAMP).withSize(26));
    }

}

