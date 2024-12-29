package com.ssafy.sandbox.paging.dto.v0;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPagingJPA is a Querydsl query type for PagingJPA
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPagingJPA extends EntityPathBase<PagingJPA> {

    private static final long serialVersionUID = 655980478L;

    public static final QPagingJPA pagingJPA = new QPagingJPA("pagingJPA");

    public final DateTimePath<java.sql.Timestamp> createdAt = createDateTime("createdAt", java.sql.Timestamp.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath title = createString("title");

    public QPagingJPA(String variable) {
        super(PagingJPA.class, forVariable(variable));
    }

    public QPagingJPA(Path<? extends PagingJPA> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPagingJPA(PathMetadata metadata) {
        super(PagingJPA.class, metadata);
    }

}

