package com.fc.kelton.admin.catalogo.application.category.retrieve.get;

import com.fc.kelton.admin.catalogo.domain.category.CategoryGateway;
import com.fc.kelton.admin.catalogo.domain.category.CategoryID;
import com.fc.kelton.admin.catalogo.domain.exceptions.DomainException;
import com.fc.kelton.admin.catalogo.domain.validation.Error;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetCategoryByIdUseCase extends GetCategoryByIdUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultGetCategoryByIdUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public CategoryOutput execute(String anIn) {
        final var aCategoryId = CategoryID.from(anIn);
        return this.categoryGateway.findById(aCategoryId)
                .map(CategoryOutput::from)
                .orElseThrow(notFound(aCategoryId));
    }

    private static Supplier<DomainException> notFound(final CategoryID anId) {
        return () -> DomainException
                .with(new Error("Category with ID %s was not found".formatted(anId.getValue())));
    }
}
