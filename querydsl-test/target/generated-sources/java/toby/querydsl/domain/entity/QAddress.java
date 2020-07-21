package toby.querydsl.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QAddress is a Querydsl query type for QAddress
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QAddress extends com.querydsl.sql.RelationalPathBase<QAddress> {

    private static final long serialVersionUID = 906491681;

    public static final QAddress address1 = new QAddress("address");

    public final StringPath address = createString("address");

    public final StringPath address2 = createString("address2");

    public final NumberPath<Short> addressId = createNumber("addressId", Short.class);

    public final NumberPath<Short> cityId = createNumber("cityId", Short.class);

    public final StringPath district = createString("district");

    public final DateTimePath<java.sql.Timestamp> lastUpdate = createDateTime("lastUpdate", java.sql.Timestamp.class);

    public final SimplePath<byte[]> location = createSimple("location", byte[].class);

    public final StringPath phone = createString("phone");

    public final StringPath postalCode = createString("postalCode");

    public final com.querydsl.sql.PrimaryKey<QAddress> primary = createPrimaryKey(addressId);

    public final com.querydsl.sql.ForeignKey<QCity> addressCityFk = createForeignKey(cityId, "city_id");

    public final com.querydsl.sql.ForeignKey<QStaff> _staffAddressFk = createInvForeignKey(addressId, "address_id");

    public final com.querydsl.sql.ForeignKey<QCustomer> _customerAddressFk = createInvForeignKey(addressId, "address_id");

    public final com.querydsl.sql.ForeignKey<QStore> _storeAddressFk = createInvForeignKey(addressId, "address_id");

    public QAddress(String variable) {
        super(QAddress.class, forVariable(variable), "null", "address");
        addMetadata();
    }

    public QAddress(String variable, String schema, String table) {
        super(QAddress.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QAddress(String variable, String schema) {
        super(QAddress.class, forVariable(variable), schema, "address");
        addMetadata();
    }

    public QAddress(Path<? extends QAddress> path) {
        super(path.getType(), path.getMetadata(), "null", "address");
        addMetadata();
    }

    public QAddress(PathMetadata metadata) {
        super(QAddress.class, metadata, "null", "address");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(address, ColumnMetadata.named("address").withIndex(2).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(address2, ColumnMetadata.named("address2").withIndex(3).ofType(Types.VARCHAR).withSize(50));
        addMetadata(addressId, ColumnMetadata.named("address_id").withIndex(1).ofType(Types.SMALLINT).withSize(5).notNull());
        addMetadata(cityId, ColumnMetadata.named("city_id").withIndex(5).ofType(Types.SMALLINT).withSize(5).notNull());
        addMetadata(district, ColumnMetadata.named("district").withIndex(4).ofType(Types.VARCHAR).withSize(20).notNull());
        addMetadata(lastUpdate, ColumnMetadata.named("last_update").withIndex(9).ofType(Types.TIMESTAMP).withSize(26).notNull());
        addMetadata(location, ColumnMetadata.named("location").withIndex(8).ofType(Types.BINARY).withSize(65535).notNull());
        addMetadata(phone, ColumnMetadata.named("phone").withIndex(7).ofType(Types.VARCHAR).withSize(20).notNull());
        addMetadata(postalCode, ColumnMetadata.named("postal_code").withIndex(6).ofType(Types.VARCHAR).withSize(10));
    }

}

