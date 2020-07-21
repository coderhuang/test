package toby.querydsl.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QCity is a Querydsl query type for QCity
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QCity extends com.querydsl.sql.RelationalPathBase<QCity> {

    private static final long serialVersionUID = 1862194526;

    public static final QCity city1 = new QCity("city");

    public final StringPath city = createString("city");

    public final NumberPath<Short> cityId = createNumber("cityId", Short.class);

    public final NumberPath<Short> countryId = createNumber("countryId", Short.class);

    public final DateTimePath<java.sql.Timestamp> lastUpdate = createDateTime("lastUpdate", java.sql.Timestamp.class);

    public final com.querydsl.sql.PrimaryKey<QCity> primary = createPrimaryKey(cityId);

    public final com.querydsl.sql.ForeignKey<QCountry> cityCountryFk = createForeignKey(countryId, "country_id");

    public final com.querydsl.sql.ForeignKey<QAddress> _addressCityFk = createInvForeignKey(cityId, "city_id");

    public QCity(String variable) {
        super(QCity.class, forVariable(variable), "null", "city");
        addMetadata();
    }

    public QCity(String variable, String schema, String table) {
        super(QCity.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QCity(String variable, String schema) {
        super(QCity.class, forVariable(variable), schema, "city");
        addMetadata();
    }

    public QCity(Path<? extends QCity> path) {
        super(path.getType(), path.getMetadata(), "null", "city");
        addMetadata();
    }

    public QCity(PathMetadata metadata) {
        super(QCity.class, metadata, "null", "city");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(city, ColumnMetadata.named("city").withIndex(2).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(cityId, ColumnMetadata.named("city_id").withIndex(1).ofType(Types.SMALLINT).withSize(5).notNull());
        addMetadata(countryId, ColumnMetadata.named("country_id").withIndex(3).ofType(Types.SMALLINT).withSize(5).notNull());
        addMetadata(lastUpdate, ColumnMetadata.named("last_update").withIndex(4).ofType(Types.TIMESTAMP).withSize(26).notNull());
    }

}

