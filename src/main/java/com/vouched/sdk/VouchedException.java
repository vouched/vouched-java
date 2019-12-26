package com.vouched.sdk;

import com.vouched.sdk.graphql.ResponseError;

import static com.vouched.sdk.VouchedError.UnknownSystemError;

public class VouchedException extends RuntimeException {
  private static final long serialVersionUID = 8918828512143293558L;

  private String type;

  public VouchedException(String message, String type) {
    super(message);
    this.type = type;
  }

  public static VouchedException from(ResponseError responseError) {
    if (responseError.extensions != null && responseError.extensions.code != null)
      return new VouchedException(responseError.message, responseError.extensions.code);

    return new VouchedException(responseError.message, UnknownSystemError);
  }

  public String getType() {
    return this.type;
  }
}