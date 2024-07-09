package org.lucasdc.controller;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.Claims;
import org.lucasdc.dto.LoginResponse;
import org.lucasdc.model.User;
import org.lucasdc.repository.UserRepository;
import org.lucasdc.service.AuthService;

import java.util.Collections;
import java.util.HashSet;


@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthController {

    @Inject
    AuthService authService;

    @POST
    @Path("register")
    @Transactional
    public Response register(User user) {
        User registeredUser = authService.save(user);
        return Response.status(Response.Status.CREATED).entity(registeredUser).build();
    }

    @POST
    @Path("login")
    public Response login(User user) {
        LoginResponse loginResponse = authService.login(user);
        return Response.ok().entity(loginResponse).build();
    }

}
