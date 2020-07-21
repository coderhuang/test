package toby.querydsl.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QInventory is a Querydsl query type for QInventory
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QInventory extends com.querydsl.sql.RelationalPathBase<QInventory> {

    private static final long serialVersionUID = 659971401;

    public static final QInventory inventory = new QInventory("inventory");

    public final NumberPath<Short> filmId = createNumber("filmId", Short.class);

    public final NumberPath<Integer> inventoryId = createNumber("inventoryId", Integer.class);

    public final DateTimePath<java.sql.Timestamp> lastUpdate = createDateTime("lastUpdate", java.sql.Timestamp.class);

    public final NumberPath<Byte> storeId = createNumber("storeId", Byte.class);

    public final com.querydsl.sql.PrimaryKey<QInventory> primary = createPrimaryKey(inventoryId);

    public final com.querydsl.sql.ForeignKey<QFilm> inventoryFilmFk = createForeignKey(filmId, "film_id");

    public final com.querydsl.sql.ForeignKey<QStore> inventoryStoreFk = createForeignKey(storeId, "store_id");

    public final com.querydsl.sql.ForeignKey<QRental> _rentalInventoryFk = createInvForeignKey(inventoryId, "inventory_id");

    public QInventory(String variable) {
        super(QInventory.class, forVariable(variable), "null", "inventory");
        addMetadata();
    }

    public QInventory(String variable, String schema, String table) {
        super(QInventory.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QInventory(String variable, String schema) {
        super(QInventory.class, forVariable(variable), schema, "inventory");
        addMetadata();
    }

    public QInventory(Path<? extends QInventory> path) {
        super(path.getType(), path.getMetadata(), "null", "inventory");
        addMetadata();
    }

    public QInventory(PathMetadata metadata) {
        super(QInventory.class, metadata, "null", "inventory");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(filmId, ColumnMetadata.named("film_id").withIndex(2).ofType(Types.SMALLINT).withSize(5).notNull());
        addMetadata(inventoryId, ColumnMetadata.named("inventory_id").withIndex(1).ofType(Types.INTEGER).withSize(8).notNull());
        addMetadata(lastUpdate, ColumnMetadata.named("last_update").withIndex(4).ofType(Types.TIMESTAMP).withSize(26).notNull());
        addMetadata(storeId, ColumnMetadata.named("store_id").withIndex(3).ofType(Types.TINYINT).withSize(3).notNull());
    }

}

