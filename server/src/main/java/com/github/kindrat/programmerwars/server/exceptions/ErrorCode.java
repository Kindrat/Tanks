package com.github.kindrat.programmerwars.server.exceptions;

public enum ErrorCode {
    PLAYER_NOT_LOADED("Not correct player data was loaded from db"),
    DUPLICATE_PLAYER_LOGIN("Another player already uses this username"),
    PLAYER_NOT_CREATED("Incorrect data was loaded after player creation");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
