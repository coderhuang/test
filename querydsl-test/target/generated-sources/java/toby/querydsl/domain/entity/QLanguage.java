package toby.querydsl.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QLanguage is a Querydsl query type for QLanguage
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QLanguage extends com.querydsl.sql.RelationalPathBase<QLanguage> {

    private static final long serialVersionUID = 1936563659;

    public static final QLanguage language = new QLanguage("language");

    public final NumberPath<Byte> languageId = createNumber("languageId", Byte.class);

    public final DateTimePath<java.sql.Timestamp> lastUpdate = createDateTime("lastUpdate", java.sql.Timestamp.class);

    public final StringPath name = createString("name");

    public final com.querydsl.sql.PrimaryKey<QLanguage> primary = createPrimaryKey(languageId);

    public final com.querydsl.sql.ForeignKey<QFilm> _filmLanguageFk = createInvForeignKey(languageId, "language_id");

    public final com.querydsl.sql.ForeignKey<QFilm> _filmLanguageOriginalFk = createInvForeignKey(languageId, "original_language_id");

    public QLanguage(String variable) {
        super(QLanguage.class, forVariable(variable), "null", "language");
        addMetadata();
    }

    public QLanguage(String variable, String schema, String table) {
        super(QLanguage.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QLanguage(String variable, String schema) {
        super(QLanguage.class, forVariable(variable), schema, "language");
        addMetadata();
    }

    public QLanguage(Path<? extends QLanguage> path) {
        super(path.getType(), path.getMetadata(), "null", "language");
        addMetadata();
    }

    public QLanguage(PathMetadata metadata) {
        super(QLanguage.class, metadata, "null", "language");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(languageId, ColumnMetadata.named("language_id").withIndex(1).ofType(Types.TINYINT).withSize(3).notNull());
        addMetadata(lastUpdate, ColumnMetadata.named("last_update").withIndex(3).ofType(Types.TIMESTAMP).withSize(26).notNull());
        addMetadata(name, ColumnMetadata.named("name").withIndex(2).ofType(Types.CHAR).withSize(20).notNull());
    }

}

