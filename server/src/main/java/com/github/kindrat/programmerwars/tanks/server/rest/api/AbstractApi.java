package com.github.kindrat.programmerwars.tanks.server.rest.api;


import com.github.kindrat.programmerwars.tanks.common.dto.BaseDto;
import com.github.kindrat.programmerwars.tanks.common.dto.EmptyDto;
import com.github.kindrat.programmerwars.tanks.server.persistence.entity.DtoConvertible;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public abstract class AbstractApi {

    @Context
    private UriInfo uriInfo;

    protected Response getResponse(Response.Status code) {
        return getResponse(new EmptyDto(), code);
    }

    protected Response getResponse(DtoConvertible convertible, Response.Status code) {
        return getResponse(convertible.getAsApiDto(), code);
    }

    protected Response getResponse(BaseDto response, Response.Status code) {
        Response.ResponseBuilder webServiceResponseBuilder = Response.status(code);
        webServiceResponseBuilder.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, DELETE, OPTIONS");
        webServiceResponseBuilder.entity(response);
        return webServiceResponseBuilder.build();
    }
}
