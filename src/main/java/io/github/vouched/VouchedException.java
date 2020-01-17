package io.github.vouched;

import io.github.vouched.graphql.ResponseError;

import java.util.List;

public class VouchedException extends RuntimeException {
  private static final long serialVersionUID = 8918828512143293558L;

  private String type;
  private List<ResponseError> errors;

  public VouchedException(String message, String type, List<ResponseError> errors) {
    super(message);
    this.type = type;
    this.errors = errors;
  }

  public static VouchedException from(ResponseError responseError) {
    if (responseError.details != null) {
      return new VouchedException(responseError.details.message, responseError.details.code, responseError.details.errors);
    }

    if (responseError.extensions != null && responseError.extensions.code != null)
      return new VouchedException(responseError.message, responseError.extensions.code, null);

    return new VouchedException(responseError.message, VouchedError.UnknownSystemError, null);
  }

  public String getType() {
    return this.type;
  }

  public List<ResponseError> getErrors() {
    return errors;
  }
}