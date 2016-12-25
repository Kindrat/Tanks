package com.github.kindrat.programmerwars.server.exceptions;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class ServerException extends RuntimeException {
    private final ErrorCode errorCode;
}
