package toby.oidc.domain.qobj;

import static com.querydsl.core.types.PathMetadataFactory.*;

import toby.oidc.domain.base.IdAssignQobj;
import toby.oidc.domain.entity.ClientProperties;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QClientProperties is a Querydsl query type for ClientProperties
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QClientProperties extends com.querydsl.sql.RelationalPathBase<ClientProperties> implements IdAssignQobj<Long> {

    private static final long serialVersionUID = -1418914856;

    public static final QClientProperties clientProperties = new QClientProperties("client_properties");

    public final NumberPath<Long> clientId = createNumber("clientId", Long.class);

    public final StringPath clientSecret = createString("clientSecret");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> updateTime = createDateTime("updateTime", java.time.LocalDateTime.class);

    public final com.querydsl.sql.PrimaryKey<ClientProperties> primary = createPrimaryKey(id);

    public QClientProperties(String variable) {
        super(ClientProperties.class, forVariable(variable), "null", "client_properties");
        addMetadata();
    }

    public QClientProperties(String variable, String schema, String table) {
        super(ClientProperties.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QClientProperties(String variable, String schema) {
        super(ClientProperties.class, forVariable(variable), schema, "client_properties");
        addMetadata();
    }

    public QClientProperties(Path<? extends ClientProperties> path) {
        super(path.getType(), path.getMetadata(), "null", "client_properties");
        addMetadata();
    }

    public QClientProperties(PathMetadata metadata) {
        super(ClientProperties.class, metadata, "null", "client_properties");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(clientId, ColumnMetadata.named("client_id").withIndex(2).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(clientSecret, ColumnMetadata.named("client_secret").withIndex(3).ofType(Types.VARCHAR).withSize(256).notNull());
        addMetadata(createTime, ColumnMetadata.named("create_time").withIndex(4).ofType(Types.TIMESTAMP).withSize(19).notNull());
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(20).notNull());
        addMetadata(updateTime, ColumnMetadata.named("update_time").withIndex(5).ofType(Types.TIMESTAMP).withSize(19).notNull());
    }

	@Override
	public NumberPath<Long> getId() {
		
		return id;
	}

}

