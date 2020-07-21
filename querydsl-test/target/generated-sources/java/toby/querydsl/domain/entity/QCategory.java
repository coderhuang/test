package toby.querydsl.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QCategory is a Querydsl query type for QCategory
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QCategory extends com.querydsl.sql.RelationalPathBase<QCategory> {

    private static final long serialVersionUID = -694302863;

    public static final QCategory category = new QCategory("category");

    public final NumberPath<Byte> categoryId = createNumber("categoryId", Byte.class);

    public final DateTimePath<java.sql.Timestamp> lastUpdate = createDateTime("lastUpdate", java.sql.Timestamp.class);

    public final StringPath name = createString("name");

    public final com.querydsl.sql.PrimaryKey<QCategory> primary = createPrimaryKey(categoryId);

    public final com.querydsl.sql.ForeignKey<QFilmCategory> _filmCategoryCategoryFk = createInvForeignKey(categoryId, "category_id");

    public QCategory(String variable) {
        super(QCategory.class, forVariable(variable), "null", "category");
        addMetadata();
    }

    public QCategory(String variable, String schema, String table) {
        super(QCategory.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QCategory(String variable, String schema) {
        super(QCategory.class, forVariable(variable), schema, "category");
        addMetadata();
    }

    public QCategory(Path<? extends QCategory> path) {
        super(path.getType(), path.getMetadata(), "null", "category");
        addMetadata();
    }

    public QCategory(PathMetadata metadata) {
        super(QCategory.class, metadata, "null", "category");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(categoryId, ColumnMetadata.named("category_id").withIndex(1).ofType(Types.TINYINT).withSize(3).notNull());
        addMetadata(lastUpdate, ColumnMetadata.named("last_update").withIndex(3).ofType(Types.TIMESTAMP).withSize(26).notNull());
        addMetadata(name, ColumnMetadata.named("name").withIndex(2).ofType(Types.VARCHAR).withSize(25).notNull());
    }

}

