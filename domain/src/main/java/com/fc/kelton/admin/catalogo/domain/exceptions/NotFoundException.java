package com.fc.kelton.admin.catalogo.domain.exceptions;

import com.fc.kelton.admin.catalogo.domain.AggregateRoot;
import com.fc.kelton.admin.catalogo.domain.Identifier;
import com.fc.kelton.admin.catalogo.domain.validation.Error;
import java.util.Collections;
import java.util.List;

public class NotFoundException extends DomainException {

    protected NotFoundException(final String aMessage, final List<Error> anErrors) {
        super(aMessage, anErrors);
    }

    public static NotFoundException with(
            final Class<? extends AggregateRoot<?>> anAggregate,
            final Identifier id
    ) {
        final var anError = "%s with ID %s was not found".formatted(
                anAggregate.getSimpleName(),
                id.getValue()
        );
        return new NotFoundException(anError, Collections.emptyList());
    }

    @Override
    public List<Error> getErrors() {
        return super.getErrors();
    }
}
