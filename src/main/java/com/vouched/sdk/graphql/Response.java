package com.vouched.sdk.graphql;

import java.util.List;

public class Response<T> {
    public T data;
    public List<ResponseError> errors;
}

