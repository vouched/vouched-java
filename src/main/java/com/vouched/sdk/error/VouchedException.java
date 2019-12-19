package com.vouched.sdk.error;

public class VouchedException extends Exception {

  private static final long serialVersionUID = 8918828512143293558L;

  private String type;

  public static String UnknownSystemError = "UnknownSystemError";
  public static String CommunicationError = "CommunicationError";
  public static String Unauthorized = "Unauthorized";

  public VouchedException(String message, String type) {
    super(message);
    this.type = type;
  }

  public String getType() {
    return this.type;
  }

}