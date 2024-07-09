package org.lucasdc.controller;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.lucasdc.repository.UserRepository;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {


    @Inject
    UserRepository userRepository;

//    @GET
//    @Path("allow-all")
//    @PermitAll
//    @Produces(MediaType.TEXT_PLAIN)
//    public String allowAll() {
//        return "ROTA ABERTA";
//    }
//
//    @GET
//    @Path("apenasuser")
//    @RolesAllowed({ "User" })
//    @Produces(MediaType.TEXT_PLAIN)
//    public String user() {
//        return "APENAS USER";
//    }
}
