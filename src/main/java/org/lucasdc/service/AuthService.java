package org.lucasdc.service;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.Claims;
import org.lucasdc.dto.LoginResponse;
import org.lucasdc.exception.BusinessException;
import org.lucasdc.model.User;
import org.lucasdc.repository.UserRepository;

import java.util.Collections;
import java.util.HashSet;

@ApplicationScoped
public class AuthService {

    @Inject
    UserRepository userRepository;

    @Transactional
    public User save(User user) {
        if(userRepository.findByEmail(user.getEmail()) != null) {
            throw new BusinessException("Usuário já existe");
        }

        String passwordEncrypted = BcryptUtil.bcryptHash(user.getPassword());
        user.setPassword(passwordEncrypted);
        user.setRole("User");

        userRepository.persist(user);

        return user;
    }

    @Transactional
    public LoginResponse login(User user) {
        User foundUser = userRepository.findByEmail(user.getEmail());

        if(foundUser == null || !BcryptUtil.matches(user.getPassword(), foundUser.getPassword())) {
            throw new BusinessException("O email ou a senha estão incorretos");
        }

        String token = Jwt.issuer("lucascanellasdev@gmail.com")
                .upn(foundUser.getName())
                .groups(new HashSet<>(Collections.singletonList(foundUser.getRole())))
                .claim(Claims.nickname.name(), foundUser.getName())
                .sign();

        var response = new LoginResponse();
        response.setToken(token);

        return response;
    }
}
