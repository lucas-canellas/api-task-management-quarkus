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

import java.util.Collections;
import java.util.HashSet;


@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthController {

    @Inject
    UserRepository userRepository;

    @POST
    @Path("register")
    @Transactional
    public Response register(User user) {

        if(userRepository.findByEmail(user.getEmail()) != null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Usuário já existe").build();
        }

        String passwordEncrypted = BcryptUtil.bcryptHash(user.getPassword());
        user.setPassword(passwordEncrypted);
        user.setRole("User");

        userRepository.persist(user);

        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @POST
    @Path("login")
    @Transactional
    public Response login(User user) {
        User foundUser = userRepository.findByEmail(user.getEmail());

        if(foundUser == null) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("O email ou a senha esta errada").build();
        }

        boolean matches = BcryptUtil.matches(user.getPassword(), foundUser.getPassword());

        if(!matches) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("O email ou a senha esta errada").build();
        }

        String token = Jwt.issuer("lucascanellasdev@gmail.com")
                .upn(foundUser.getEmail())
                .groups(new HashSet<>(Collections.singletonList(foundUser.getRole())))
                .sign();

        var response = new LoginResponse();
        response.setName(foundUser.getName());
        response.setToken(token);

        return Response.ok().entity(response).build();

    }

}
