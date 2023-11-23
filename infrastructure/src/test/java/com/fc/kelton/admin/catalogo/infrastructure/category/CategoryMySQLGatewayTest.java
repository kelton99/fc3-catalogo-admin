package com.fc.kelton.admin.catalogo.infrastructure.category;

import com.fc.kelton.admin.catalogo.domain.category.Category;
import com.fc.kelton.admin.catalogo.domain.category.CategoryID;
import com.fc.kelton.admin.catalogo.infrastructure.MySQLGatewayTest;
import com.fc.kelton.admin.catalogo.infrastructure.category.persistence.CategoryJpaEntity;
import com.fc.kelton.admin.catalogo.infrastructure.category.persistence.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


@MySQLGatewayTest
public class CategoryMySQLGatewayTest {

    @Autowired
    private CategoryMySQLGateway categoryMySQLGateway;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void givenAValidCategory_whenCallsCreate_shouldReturnANewCategory() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var aCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertEquals(0, categoryRepository.count());

        final var actualCategory = categoryMySQLGateway.create(aCategory);

        Assertions.assertEquals(1, categoryRepository.count());

        Assertions.assertNotNull(actualCategory);
        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(aCategory.getName(), actualCategory.getName());
        Assertions.assertEquals(aCategory.getDescription(), actualCategory.getDescription());
        Assertions.assertEquals(aCategory.isActive(), actualCategory.isActive());
        Assertions.assertEquals(aCategory.getCreatedAt(), actualCategory.getCreatedAt());
        Assertions.assertEquals(aCategory.getUpdatedAt(), actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());

        final var actualEntity = categoryRepository.findById(aCategory.getId().getValue()).get();

        Assertions.assertNotNull(actualCategory);
        Assertions.assertEquals(aCategory.getId().getValue(), actualEntity.getId());
        Assertions.assertEquals(aCategory.getName(), actualEntity.getName());
        Assertions.assertEquals(aCategory.getDescription(), actualEntity.getDescription());
        Assertions.assertEquals(aCategory.isActive(), actualEntity.isActive());
        Assertions.assertEquals(aCategory.getCreatedAt(), actualEntity.getCreatedAt());
        Assertions.assertEquals(aCategory.getUpdatedAt(), actualEntity.getUpdatedAt());
        Assertions.assertNull(actualEntity.getDeletedAt());
    }

    @Test
    public void givenAValidCategory_whenCallsUpdate_shouldReturnUpdatedCategory() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var aCategory = Category.newCategory("Film", null, expectedIsActive);

        Assertions.assertEquals(0, categoryRepository.count());

        categoryRepository.saveAndFlush(CategoryJpaEntity.from(aCategory));

        Assertions.assertEquals(1, categoryRepository.count());

        final var anUpdatedCategory = aCategory.clone().update(expectedName, expectedDescription, expectedIsActive);

        final var actualCategory = categoryMySQLGateway.update(anUpdatedCategory);

        Assertions.assertEquals(1, categoryRepository.count());

        Assertions.assertNotNull(actualCategory);
        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertEquals(aCategory.getCreatedAt(), actualCategory.getCreatedAt());
        Assertions.assertTrue(aCategory.getUpdatedAt().isBefore(actualCategory.getUpdatedAt()));
        Assertions.assertNull(actualCategory.getDeletedAt());

        final var actualEntity = categoryRepository.findById(aCategory.getId().getValue()).get();

        Assertions.assertNotNull(actualCategory);
        Assertions.assertEquals(aCategory.getId().getValue(), actualEntity.getId());
        Assertions.assertEquals(expectedName, actualEntity.getName());
        Assertions.assertEquals(expectedDescription, actualEntity.getDescription());
        Assertions.assertEquals(expectedIsActive, actualEntity.isActive());
        Assertions.assertEquals(aCategory.getCreatedAt(), actualEntity.getCreatedAt());
        Assertions.assertTrue(aCategory.getUpdatedAt().isBefore(actualCategory.getUpdatedAt()));
        Assertions.assertNull(actualEntity.getDeletedAt());
    }

    @Test
    public void givenAPrePersistedCategoryAndValidCategoryId_whenTryToDeleteIt_shouldDeleteCategory() {
        final var aCategory = Category.newCategory("Filmes", null, true);

        Assertions.assertEquals(0, categoryRepository.count());

        categoryRepository.saveAndFlush(CategoryJpaEntity.from(aCategory));

        Assertions.assertEquals(1, categoryRepository.count());

        categoryMySQLGateway.deleteById(aCategory.getId());

        Assertions.assertEquals(0, categoryRepository.count());
    }

    @Test
    public void givenAnInvalidCategoryId_whenTryToDeleteIt_shouldDeleteCategory() {
        Assertions.assertEquals(0, categoryRepository.count());

        categoryMySQLGateway.deleteById(CategoryID.from("invalid"));

        Assertions.assertEquals(0, categoryRepository.count());
    }

}
