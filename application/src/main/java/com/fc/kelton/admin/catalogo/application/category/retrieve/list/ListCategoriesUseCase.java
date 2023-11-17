package com.fc.kelton.admin.catalogo.application.category.retrieve.list;

import com.fc.kelton.admin.catalogo.application.UseCase;
import com.fc.kelton.admin.catalogo.domain.Pagination;
import com.fc.kelton.admin.catalogo.domain.category.CategorySearchQuery;

public abstract class ListCategoriesUseCase extends UseCase<CategorySearchQuery, Pagination<CategoryListOutput>> {
}
