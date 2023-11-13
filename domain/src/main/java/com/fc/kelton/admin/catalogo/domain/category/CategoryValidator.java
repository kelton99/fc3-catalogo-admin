package com.fc.kelton.admin.catalogo.domain.category;

import com.fc.kelton.admin.catalogo.domain.validation.Error;
import com.fc.kelton.admin.catalogo.domain.validation.ValidationHandler;
import com.fc.kelton.admin.catalogo.domain.validation.Validator;

public class CategoryValidator extends Validator {

    private final Category category;
    public static final int NAME_MAX_LENGTH = 255;
    public static final int NAME_MIN_LENGHT = 3;

    public CategoryValidator(final Category aCategory, final ValidationHandler aHandler) {
        super(aHandler);
        this.category = aCategory;
    }

    @Override
    public void validate() {
        checkNameConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.category.getName();
        if (name == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }

        final int length = name.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGHT) {
            this.validationHandler().append(new Error("'name' must between 3 and 255"));
        }
    }
}
