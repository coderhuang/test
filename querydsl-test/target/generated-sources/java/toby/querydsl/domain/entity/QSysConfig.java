package toby.querydsl.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QSysConfig is a Querydsl query type for QSysConfig
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QSysConfig extends com.querydsl.sql.RelationalPathBase<QSysConfig> {

    private static final long serialVersionUID = -1878825892;

    public static final QSysConfig sysConfig = new QSysConfig("sys_config");

    public final StringPath setBy = createString("setBy");

    public final DateTimePath<java.sql.Timestamp> setTime = createDateTime("setTime", java.sql.Timestamp.class);

    public final StringPath value = createString("value");

    public final StringPath variable = createString("variable");

    public final com.querydsl.sql.PrimaryKey<QSysConfig> primary = createPrimaryKey(variable);

    public QSysConfig(String variable) {
        super(QSysConfig.class, forVariable(variable), "null", "sys_config");
        addMetadata();
    }

    public QSysConfig(String variable, String schema, String table) {
        super(QSysConfig.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QSysConfig(String variable, String schema) {
        super(QSysConfig.class, forVariable(variable), schema, "sys_config");
        addMetadata();
    }

    public QSysConfig(Path<? extends QSysConfig> path) {
        super(path.getType(), path.getMetadata(), "null", "sys_config");
        addMetadata();
    }

    public QSysConfig(PathMetadata metadata) {
        super(QSysConfig.class, metadata, "null", "sys_config");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(setBy, ColumnMetadata.named("set_by").withIndex(4).ofType(Types.VARCHAR).withSize(128));
        addMetadata(setTime, ColumnMetadata.named("set_time").withIndex(3).ofType(Types.TIMESTAMP).withSize(26));
        addMetadata(value, ColumnMetadata.named("value").withIndex(2).ofType(Types.VARCHAR).withSize(128));
        addMetadata(variable, ColumnMetadata.named("variable").withIndex(1).ofType(Types.VARCHAR).withSize(128).notNull());
    }

}

