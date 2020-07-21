package toby.querydsl.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QRental is a Querydsl query type for QRental
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QRental extends com.querydsl.sql.RelationalPathBase<QRental> {

    private static final long serialVersionUID = -1006860201;

    public static final QRental rental = new QRental("rental");

    public final NumberPath<Short> customerId = createNumber("customerId", Short.class);

    public final NumberPath<Integer> inventoryId = createNumber("inventoryId", Integer.class);

    public final DateTimePath<java.sql.Timestamp> lastUpdate = createDateTime("lastUpdate", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> rentalDate = createDateTime("rentalDate", java.sql.Timestamp.class);

    public final NumberPath<Integer> rentalId = createNumber("rentalId", Integer.class);

    public final DateTimePath<java.sql.Timestamp> returnDate = createDateTime("returnDate", java.sql.Timestamp.class);

    public final NumberPath<Byte> staffId = createNumber("staffId", Byte.class);

    public final com.querydsl.sql.PrimaryKey<QRental> primary = createPrimaryKey(rentalId);

    public final com.querydsl.sql.ForeignKey<QCustomer> rentalCustomerFk = createForeignKey(customerId, "customer_id");

    public final com.querydsl.sql.ForeignKey<QStaff> rentalStaffFk = createForeignKey(staffId, "staff_id");

    public final com.querydsl.sql.ForeignKey<QInventory> rentalInventoryFk = createForeignKey(inventoryId, "inventory_id");

    public final com.querydsl.sql.ForeignKey<QPayment> _paymentRentalFk = createInvForeignKey(rentalId, "rental_id");

    public QRental(String variable) {
        super(QRental.class, forVariable(variable), "null", "rental");
        addMetadata();
    }

    public QRental(String variable, String schema, String table) {
        super(QRental.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QRental(String variable, String schema) {
        super(QRental.class, forVariable(variable), schema, "rental");
        addMetadata();
    }

    public QRental(Path<? extends QRental> path) {
        super(path.getType(), path.getMetadata(), "null", "rental");
        addMetadata();
    }

    public QRental(PathMetadata metadata) {
        super(QRental.class, metadata, "null", "rental");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(customerId, ColumnMetadata.named("customer_id").withIndex(4).ofType(Types.SMALLINT).withSize(5).notNull());
        addMetadata(inventoryId, ColumnMetadata.named("inventory_id").withIndex(3).ofType(Types.INTEGER).withSize(8).notNull());
        addMetadata(lastUpdate, ColumnMetadata.named("last_update").withIndex(7).ofType(Types.TIMESTAMP).withSize(26).notNull());
        addMetadata(rentalDate, ColumnMetadata.named("rental_date").withIndex(2).ofType(Types.TIMESTAMP).withSize(26).notNull());
        addMetadata(rentalId, ColumnMetadata.named("rental_id").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(returnDate, ColumnMetadata.named("return_date").withIndex(5).ofType(Types.TIMESTAMP).withSize(26));
        addMetadata(staffId, ColumnMetadata.named("staff_id").withIndex(6).ofType(Types.TINYINT).withSize(3).notNull());
    }

}

