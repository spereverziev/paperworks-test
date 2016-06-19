package com.payworks.test.web.error;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class entity, long id) {
        super("No such data: " + entity.getSimpleName() + ", with id: " + id);
    }
}
