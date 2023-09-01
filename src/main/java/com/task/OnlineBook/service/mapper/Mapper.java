package com.task.OnlineBook.service.mapper;

public abstract class Mapper<A, B> {
    public abstract  A toEntityObject(B dtoObject);

    public abstract B toDtoObject(A entityObject);
}
