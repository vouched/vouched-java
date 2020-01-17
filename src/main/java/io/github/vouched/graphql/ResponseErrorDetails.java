package io.github.vouched.graphql;

import java.util.List;

public class ResponseErrorDetails {
    public String code;
    public String message;
    public List<ResponseError> errors;
}
