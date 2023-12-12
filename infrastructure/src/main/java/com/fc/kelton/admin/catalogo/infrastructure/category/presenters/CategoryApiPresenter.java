package com.fc.kelton.admin.catalogo.infrastructure.category.presenters;

import com.fc.kelton.admin.catalogo.application.category.retrieve.get.CategoryOutput;
import com.fc.kelton.admin.catalogo.infrastructure.category.models.CategoryApiOutput;

public interface CategoryApiPresenter {

    static CategoryApiOutput present(final CategoryOutput output) {
        return new CategoryApiOutput(
                output.id().getValue(),
                output.name(),
                output.description(),
                output.isActive(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }
}
