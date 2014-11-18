package com.github.kindrat.programmerwars.tanks.server.services.exceptions;

public class ServerLogicException extends Exception
{
   private final ErrorCode errorCode;

   public ServerLogicException(ErrorCode errorCode)
   {
      this.errorCode = errorCode;
   }

   public ErrorCode getErrorCode()
   {
      return errorCode;
   }
}
