package com.fc.kelton.admin.catalogo.application.category.update;

import com.fc.kelton.admin.catalogo.application.UseCase;
import com.fc.kelton.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase extends UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {
}
