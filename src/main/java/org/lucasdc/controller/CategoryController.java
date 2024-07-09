package org.lucasdc.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
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
    @RolesAllowed("User")
    public Response createCategory(CategoryRequest categoryRequest) {

        Category category = Category.fromTaskRequest(categoryRequest);

        Category categorySaved= categoryService.save(category);

        return Response.status(Response.Status.CREATED).entity(categorySaved).build();
    }


}
