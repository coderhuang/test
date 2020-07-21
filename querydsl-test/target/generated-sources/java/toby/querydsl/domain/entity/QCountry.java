package toby.querydsl.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QCountry is a Querydsl query type for QCountry
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QCountry extends com.querydsl.sql.RelationalPathBase<QCountry> {

    private static final long serialVersionUID = -1282952509;

    public static final QCountry country1 = new QCountry("country");

    public final StringPath country = createString("country");

    public final NumberPath<Short> countryId = createNumber("countryId", Short.class);

    public final DateTimePath<java.sql.Timestamp> lastUpdate = createDateTime("lastUpdate", java.sql.Timestamp.class);

    public final com.querydsl.sql.PrimaryKey<QCountry> primary = createPrimaryKey(countryId);

    public final com.querydsl.sql.ForeignKey<QCity> _cityCountryFk = createInvForeignKey(countryId, "country_id");

    public QCountry(String variable) {
        super(QCountry.class, forVariable(variable), "null", "country");
        addMetadata();
    }

    public QCountry(String variable, String schema, String table) {
        super(QCountry.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QCountry(String variable, String schema) {
        super(QCountry.class, forVariable(variable), schema, "country");
        addMetadata();
    }

    public QCountry(Path<? extends QCountry> path) {
        super(path.getType(), path.getMetadata(), "null", "country");
        addMetadata();
    }

    public QCountry(PathMetadata metadata) {
        super(QCountry.class, metadata, "null", "country");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(country, ColumnMetadata.named("country").withIndex(2).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(countryId, ColumnMetadata.named("country_id").withIndex(1).ofType(Types.SMALLINT).withSize(5).notNull());
        addMetadata(lastUpdate, ColumnMetadata.named("last_update").withIndex(3).ofType(Types.TIMESTAMP).withSize(26).notNull());
    }

}

