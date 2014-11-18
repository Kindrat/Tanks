package com.github.kindrat.programmerwars.tanks.server.context;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.github.kindrat.programmerwars.tanks.server.rest.AuthFilter;
import com.github.kindrat.programmerwars.tanks.server.rest.AuthenticationExceptionMapper;
import com.github.kindrat.programmerwars.tanks.server.rest.UncaughtExceptionMapper;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class RestContext extends ResourceConfig {

    public RestContext() {
        packages(true, "com.github.kindrat.programmerwars.tanks.server.rest.api")
                .register(JacksonJaxbJsonProvider.class)
                .register(AuthenticationExceptionMapper.class)
                .register(UncaughtExceptionMapper.class)
                .register(AuthFilter.class)
                .register(RolesAllowedDynamicFeature.class);
    }
}
