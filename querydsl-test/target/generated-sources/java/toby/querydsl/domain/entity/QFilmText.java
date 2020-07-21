package toby.querydsl.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QFilmText is a Querydsl query type for QFilmText
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QFilmText extends com.querydsl.sql.RelationalPathBase<QFilmText> {

    private static final long serialVersionUID = -1472964796;

    public static final QFilmText filmText = new QFilmText("film_text");

    public final StringPath description = createString("description");

    public final NumberPath<Short> filmId = createNumber("filmId", Short.class);

    public final StringPath title = createString("title");

    public final com.querydsl.sql.PrimaryKey<QFilmText> primary = createPrimaryKey(filmId);

    public QFilmText(String variable) {
        super(QFilmText.class, forVariable(variable), "null", "film_text");
        addMetadata();
    }

    public QFilmText(String variable, String schema, String table) {
        super(QFilmText.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QFilmText(String variable, String schema) {
        super(QFilmText.class, forVariable(variable), schema, "film_text");
        addMetadata();
    }

    public QFilmText(Path<? extends QFilmText> path) {
        super(path.getType(), path.getMetadata(), "null", "film_text");
        addMetadata();
    }

    public QFilmText(PathMetadata metadata) {
        super(QFilmText.class, metadata, "null", "film_text");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(description, ColumnMetadata.named("description").withIndex(3).ofType(Types.LONGVARCHAR).withSize(65535));
        addMetadata(filmId, ColumnMetadata.named("film_id").withIndex(1).ofType(Types.SMALLINT).withSize(5).notNull());
        addMetadata(title, ColumnMetadata.named("title").withIndex(2).ofType(Types.VARCHAR).withSize(255).notNull());
    }

}

