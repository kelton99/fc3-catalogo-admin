package com.fc.kelton.admin.catalogo.infrastructure.category.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fc.kelton.admin.catalogo.ControllerTest;
import com.fc.kelton.admin.catalogo.application.category.create.CreateCategoryOutput;
import com.fc.kelton.admin.catalogo.application.category.create.CreateCategoryUseCase;
import com.fc.kelton.admin.catalogo.domain.category.CategoryID;
import com.fc.kelton.admin.catalogo.infrastructure.api.CategoryApi;
import com.fc.kelton.admin.catalogo.infrastructure.category.models.CreateCategoryApiInput;
import io.vavr.API;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Objects;

@ControllerTest(controllers = CategoryApi.class)
public class CategoryAPITest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CreateCategoryUseCase createCategoryUseCase;

    @Test
    public void givenAValidCommand_whenCallsCreateCategory_shouldReturnCategoryId() throws Exception {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var anInput =
                new CreateCategoryApiInput(expectedName, expectedDescription, expectedIsActive);

        Mockito.when(createCategoryUseCase.execute(Mockito.any()))
                .thenReturn(API.Right(CreateCategoryOutput.from("123")));

        final var request = MockMvcRequestBuilders.post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(anInput));


        this.mvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpectAll(
                        MockMvcResultMatchers.status().isCreated(),
                        MockMvcResultMatchers.header().string("Location", "/categories/123"),
                        MockMvcResultMatchers.header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE),
                        MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo("123"))
                );

        Mockito.verify(createCategoryUseCase, Mockito.times(1))
                .execute(Mockito.argThat(cmd ->
                        Objects.equals(expectedName, cmd.name())
                                && Objects.equals(expectedDescription, cmd.description())
                                && Objects.equals(expectedIsActive, cmd.isActive())
                ));
    }
}
