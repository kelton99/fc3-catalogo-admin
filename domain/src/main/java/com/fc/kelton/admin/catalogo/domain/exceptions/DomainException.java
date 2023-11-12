package com.fc.kelton.admin.catalogo.domain.exceptions;

import com.fc.kelton.admin.catalogo.domain.validation.Error;

import java.util.List;

public class DomainException extends NoStackTraceException {

    private final List<Error> errors;

    private DomainException(final List<Error> anErrors) {
        super("");
        this.errors = anErrors;
    }

    public static DomainException with(final Error anErrors) {
        return new DomainException(List.of(anErrors));
    }

    public static DomainException with(final List<Error> anErrors) {
        return new DomainException(anErrors);
    }

    public List<Error> getErrors() {
        return errors;
    }
}
