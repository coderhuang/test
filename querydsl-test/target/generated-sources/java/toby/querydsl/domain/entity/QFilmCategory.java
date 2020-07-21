package toby.querydsl.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QFilmCategory is a Querydsl query type for QFilmCategory
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QFilmCategory extends com.querydsl.sql.RelationalPathBase<QFilmCategory> {

    private static final long serialVersionUID = 2062281621;

    public static final QFilmCategory filmCategory = new QFilmCategory("film_category");

    public final NumberPath<Byte> categoryId = createNumber("categoryId", Byte.class);

    public final NumberPath<Short> filmId = createNumber("filmId", Short.class);

    public final DateTimePath<java.sql.Timestamp> lastUpdate = createDateTime("lastUpdate", java.sql.Timestamp.class);

    public final com.querydsl.sql.PrimaryKey<QFilmCategory> primary = createPrimaryKey(filmId, categoryId);

    public final com.querydsl.sql.ForeignKey<QFilm> filmCategoryFilmFk = createForeignKey(filmId, "film_id");

    public final com.querydsl.sql.ForeignKey<QCategory> filmCategoryCategoryFk = createForeignKey(categoryId, "category_id");

    public QFilmCategory(String variable) {
        super(QFilmCategory.class, forVariable(variable), "null", "film_category");
        addMetadata();
    }

    public QFilmCategory(String variable, String schema, String table) {
        super(QFilmCategory.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QFilmCategory(String variable, String schema) {
        super(QFilmCategory.class, forVariable(variable), schema, "film_category");
        addMetadata();
    }

    public QFilmCategory(Path<? extends QFilmCategory> path) {
        super(path.getType(), path.getMetadata(), "null", "film_category");
        addMetadata();
    }

    public QFilmCategory(PathMetadata metadata) {
        super(QFilmCategory.class, metadata, "null", "film_category");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(categoryId, ColumnMetadata.named("category_id").withIndex(2).ofType(Types.TINYINT).withSize(3).notNull());
        addMetadata(filmId, ColumnMetadata.named("film_id").withIndex(1).ofType(Types.SMALLINT).withSize(5).notNull());
        addMetadata(lastUpdate, ColumnMetadata.named("last_update").withIndex(3).ofType(Types.TIMESTAMP).withSize(26).notNull());
    }

}

