package toby.querydsl.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QFilm is a Querydsl query type for QFilm
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QFilm extends com.querydsl.sql.RelationalPathBase<QFilm> {

    private static final long serialVersionUID = 1862283639;

    public static final QFilm film = new QFilm("film");

    public final StringPath description = createString("description");

    public final NumberPath<Short> filmId = createNumber("filmId", Short.class);

    public final NumberPath<Byte> languageId = createNumber("languageId", Byte.class);

    public final DateTimePath<java.sql.Timestamp> lastUpdate = createDateTime("lastUpdate", java.sql.Timestamp.class);

    public final NumberPath<Short> length = createNumber("length", Short.class);

    public final NumberPath<Byte> originalLanguageId = createNumber("originalLanguageId", Byte.class);

    public final StringPath rating = createString("rating");

    public final DatePath<java.sql.Date> releaseYear = createDate("releaseYear", java.sql.Date.class);

    public final NumberPath<Byte> rentalDuration = createNumber("rentalDuration", Byte.class);

    public final NumberPath<java.math.BigDecimal> rentalRate = createNumber("rentalRate", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> replacementCost = createNumber("replacementCost", java.math.BigDecimal.class);

    public final StringPath specialFeatures = createString("specialFeatures");

    public final StringPath title = createString("title");

    public final com.querydsl.sql.PrimaryKey<QFilm> primary = createPrimaryKey(filmId);

    public final com.querydsl.sql.ForeignKey<QLanguage> filmLanguageOriginalFk = createForeignKey(originalLanguageId, "language_id");

    public final com.querydsl.sql.ForeignKey<QLanguage> filmLanguageFk = createForeignKey(languageId, "language_id");

    public final com.querydsl.sql.ForeignKey<QInventory> _inventoryFilmFk = createInvForeignKey(filmId, "film_id");

    public final com.querydsl.sql.ForeignKey<QFilmActor> _filmActorFilmFk = createInvForeignKey(filmId, "film_id");

    public final com.querydsl.sql.ForeignKey<QFilmCategory> _filmCategoryFilmFk = createInvForeignKey(filmId, "film_id");

    public QFilm(String variable) {
        super(QFilm.class, forVariable(variable), "null", "film");
        addMetadata();
    }

    public QFilm(String variable, String schema, String table) {
        super(QFilm.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QFilm(String variable, String schema) {
        super(QFilm.class, forVariable(variable), schema, "film");
        addMetadata();
    }

    public QFilm(Path<? extends QFilm> path) {
        super(path.getType(), path.getMetadata(), "null", "film");
        addMetadata();
    }

    public QFilm(PathMetadata metadata) {
        super(QFilm.class, metadata, "null", "film");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(description, ColumnMetadata.named("description").withIndex(3).ofType(Types.LONGVARCHAR).withSize(65535));
        addMetadata(filmId, ColumnMetadata.named("film_id").withIndex(1).ofType(Types.SMALLINT).withSize(5).notNull());
        addMetadata(languageId, ColumnMetadata.named("language_id").withIndex(5).ofType(Types.TINYINT).withSize(3).notNull());
        addMetadata(lastUpdate, ColumnMetadata.named("last_update").withIndex(13).ofType(Types.TIMESTAMP).withSize(26).notNull());
        addMetadata(length, ColumnMetadata.named("length").withIndex(9).ofType(Types.SMALLINT).withSize(5));
        addMetadata(originalLanguageId, ColumnMetadata.named("original_language_id").withIndex(6).ofType(Types.TINYINT).withSize(3));
        addMetadata(rating, ColumnMetadata.named("rating").withIndex(11).ofType(Types.CHAR).withSize(5));
        addMetadata(releaseYear, ColumnMetadata.named("release_year").withIndex(4).ofType(Types.DATE).withSize(4));
        addMetadata(rentalDuration, ColumnMetadata.named("rental_duration").withIndex(7).ofType(Types.TINYINT).withSize(3).notNull());
        addMetadata(rentalRate, ColumnMetadata.named("rental_rate").withIndex(8).ofType(Types.DECIMAL).withSize(4).withDigits(2).notNull());
        addMetadata(replacementCost, ColumnMetadata.named("replacement_cost").withIndex(10).ofType(Types.DECIMAL).withSize(5).withDigits(2).notNull());
        addMetadata(specialFeatures, ColumnMetadata.named("special_features").withIndex(12).ofType(Types.CHAR).withSize(54));
        addMetadata(title, ColumnMetadata.named("title").withIndex(2).ofType(Types.VARCHAR).withSize(255).notNull());
    }

}

