package toby.oidc.domain.qobj;

import static com.querydsl.core.types.PathMetadataFactory.*;

import toby.oidc.domain.base.IdAssignQobj;
import toby.oidc.domain.entity.User;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QUser extends com.querydsl.sql.RelationalPathBase<User> implements IdAssignQobj<Long> {

    private static final long serialVersionUID = -483325115;

    public static final QUser user = new QUser("user");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final DateTimePath<java.time.LocalDateTime> updateTime = createDateTime("updateTime", java.time.LocalDateTime.class);

    public final com.querydsl.sql.PrimaryKey<User> primary = createPrimaryKey(id);

    public QUser(String variable) {
        super(User.class, forVariable(variable), "null", "user");
        addMetadata();
    }

    public QUser(String variable, String schema, String table) {
        super(User.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QUser(String variable, String schema) {
        super(User.class, forVariable(variable), schema, "user");
        addMetadata();
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata(), "null", "user");
        addMetadata();
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata, "null", "user");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createTime, ColumnMetadata.named("create_time").withIndex(4).ofType(Types.TIMESTAMP).withSize(19).notNull());
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(20).notNull());
        addMetadata(name, ColumnMetadata.named("name").withIndex(2).ofType(Types.VARCHAR).withSize(100).notNull());
        addMetadata(password, ColumnMetadata.named("password").withIndex(3).ofType(Types.VARCHAR).withSize(512));
        addMetadata(updateTime, ColumnMetadata.named("update_time").withIndex(5).ofType(Types.TIMESTAMP).withSize(19).notNull());
    }

	@Override
	public NumberPath<Long> getId() {
		
		return id;
	}

}

