package com.github.kindrat.programmerwars.tanks.server.rest;

import com.github.kindrat.programmerwars.tanks.common.dto.Role;
import com.github.kindrat.programmerwars.tanks.server.persistence.entity.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class SecurityContextImpl implements SecurityContext {
    private static final Logger LOG = LoggerFactory.getLogger(SecurityContextImpl.class);
    private final Player user;
    private final boolean isSecure;

    public SecurityContextImpl(Player user, boolean isSecure) {
        this.user = user;
        this.isSecure = isSecure;
    }

    @Override
    public Principal getUserPrincipal() {
        return user;
    }

    @Override
    public boolean isUserInRole(String role) {
        LOG.debug(String.format("SecurityContextImpl / isUserInRole - role:%s, user:%s", role, user));
        String userRole = user.getUserGroup().getRole().name();
        return userRole.equalsIgnoreCase(Role.ADMIN.name())
                || userRole.equalsIgnoreCase(role)
                || role.equalsIgnoreCase(Role.NONE.name());
    }

    @Override
    public boolean isSecure() {
        return isSecure;
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }
}
