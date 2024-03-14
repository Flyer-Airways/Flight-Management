package com.matcodem.flightmanagement.application.queries;

import com.matcodem.flightmanagement.domain.entity.BaseEntity;

import java.util.List;

@FunctionalInterface
public interface QueryHandlerMethod<T extends BaseQuery> {
    List<BaseEntity> handle(T query);
}
