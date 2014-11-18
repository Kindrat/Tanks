package com.github.kindrat.programmerwars.tanks.server.rest;

import com.github.kindrat.programmerwars.tanks.server.persistence.entity.Player;
import com.github.kindrat.programmerwars.tanks.server.persistence.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.util.StringTokenizer;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String auth = requestContext.getHeaderString("authorization");

        if (auth == null) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }

        StringTokenizer tokenizer = decode(auth);

        if (tokenizer == null || tokenizer.countTokens() != 2) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }

        String login = tokenizer.nextToken();
        String password = tokenizer.nextToken();

        Player principal = playerRepository.findByNicknameAndPassword(login, password);

        if (principal == null) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
        requestContext.setSecurityContext(new SecurityContextImpl(principal, requestContext.getSecurityContext().isSecure()));
    }

    private StringTokenizer decode(String authHeader) {
        //Replacing "Basic THE_BASE_64" to "THE_BASE_64" directly
        authHeader = authHeader.replaceFirst("[B|b]asic ", "");
        //Decode the Base64 into byte[]
        byte[] decodedBytes = DatatypeConverter.parseBase64Binary(authHeader);
        //If the decode fails in any case
        if (decodedBytes == null || decodedBytes.length == 0) {
            return null;
        }
        return new StringTokenizer(new String(decodedBytes), ":");
    }
}
