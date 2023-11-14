package com.fc.kelton.admin.catalogo.application.category.create;

import com.fc.kelton.admin.catalogo.application.UseCase;
import com.fc.kelton.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateCategoryUseCase extends UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {

}
