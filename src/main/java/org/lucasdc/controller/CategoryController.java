package org.lucasdc.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.lucasdc.dto.CategoryRequest;
import org.lucasdc.model.Category;
import org.lucasdc.service.CategoryService;

@Path("/categories")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryController {

    @Inject
    CategoryService categoryService;

    @POST
    public Response createCategory(CategoryRequest categoryRequest) {

        Category category = Category.fromTaskRequest(categoryRequest);

        Category categorySaved= categoryService.save(category);

        return Response.status(Response.Status.CREATED).entity(categorySaved).build();
    }



}
