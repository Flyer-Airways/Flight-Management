package com.matcodem.flightmanagement.application.queries.handler;

import com.matcodem.flightmanagement.application.queries.BaseQuery;
import com.matcodem.flightmanagement.domain.entity.BaseEntity;
import org.springframework.data.domain.Page;

@FunctionalInterface
public interface QueryHandlerMethod<T extends BaseQuery> {
    Page<BaseEntity> handle(T query);
}
