package org.lucasdc.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.lucasdc.dto.TaskRequest;
import org.lucasdc.dto.TaskResponse;
import org.lucasdc.model.Category;
import org.lucasdc.model.Task;
import org.lucasdc.repository.CategoryRepository;
import org.lucasdc.repository.UserRepository;
import org.lucasdc.service.TaskService;

import java.util.List;

@Path("/tasks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TaskController {

    @Inject
    TaskService taskService;
    @Inject
    JsonWebToken jwtWebToken;

    @POST
    @RolesAllowed("User")
    public Response createTask(TaskRequest taskRequest) {
        Task task = taskService.createTask(taskRequest);
        return Response.status(Response.Status.CREATED).entity(task).build();
    }

    @GET
    @RolesAllowed("User")
    public Response listTasksByUser() {

        String email = jwtWebToken.getClaim("email").toString();
        List<Task> tasksByUserEmail = taskService.getTasksByUserEmail(email);

        return Response.status(Response.Status.OK).entity(tasksByUserEmail).build();
    }

    @GET()
    @Path("{taskId}")
    @RolesAllowed("User")
    public Response getTaskById(@PathParam("taskId") Long taskId) {
        Task task = taskService.getTaskById(taskId);
        TaskResponse taskResponse = TaskResponse.fromTask(task);
        return Response.status(Response.Status.OK).entity(taskResponse).build();
    }

    @DELETE()
    @Path("{taskId}")
    @RolesAllowed("User")
    public Response deleteTaskById(@PathParam("taskId") Long taskId) {
        String email = jwtWebToken.getClaim("email").toString();
        taskService.deleteTask(taskId, email);
        return Response.status(Response.Status.NO_CONTENT).build();
    }




}
