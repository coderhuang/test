package toby.querydsl.domain.qobj;

import static com.querydsl.core.types.PathMetadataFactory.*;
import toby.querydsl.domain.entity.SkuProperty;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QSkuProperty is a Querydsl query type for SkuProperty
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QSkuProperty extends com.querydsl.sql.RelationalPathBase<SkuProperty> {

    private static final long serialVersionUID = -1298462604;

    public static final QSkuProperty skuProperty = new QSkuProperty("sku_property");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> skuCode = createNumber("skuCode", Long.class);

    public final StringPath skuName = createString("skuName");

    public final DateTimePath<java.time.LocalDateTime> updateTime = createDateTime("updateTime", java.time.LocalDateTime.class);

    public final com.querydsl.sql.PrimaryKey<SkuProperty> primary = createPrimaryKey(id);

    public QSkuProperty(String variable) {
        super(SkuProperty.class, forVariable(variable), "null", "sku_property");
        addMetadata();
    }

    public QSkuProperty(String variable, String schema, String table) {
        super(SkuProperty.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QSkuProperty(String variable, String schema) {
        super(SkuProperty.class, forVariable(variable), schema, "sku_property");
        addMetadata();
    }

    public QSkuProperty(Path<? extends SkuProperty> path) {
        super(path.getType(), path.getMetadata(), "null", "sku_property");
        addMetadata();
    }

    public QSkuProperty(PathMetadata metadata) {
        super(SkuProperty.class, metadata, "null", "sku_property");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createTime, ColumnMetadata.named("create_time").withIndex(4).ofType(Types.TIMESTAMP).withSize(19).notNull());
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(20).notNull());
        addMetadata(skuCode, ColumnMetadata.named("sku_code").withIndex(3).ofType(Types.BIGINT).withSize(20).notNull());
        addMetadata(skuName, ColumnMetadata.named("sku_name").withIndex(2).ofType(Types.VARCHAR).withSize(30).notNull());
        addMetadata(updateTime, ColumnMetadata.named("update_time").withIndex(5).ofType(Types.TIMESTAMP).withSize(19));
    }

}

