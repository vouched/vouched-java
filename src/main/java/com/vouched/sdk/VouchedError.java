package com.vouched.sdk;

import java.util.List;

public class VouchedError {
    public static String UnknownSystemError = "UnknownSystemError";
    public static String CommunicationError = "CommunicationError";
    public static String UNAUTHENTICATED = "UNAUTHENTICATED";

    public String type;
    public String message;
    public String suggestion;
    public List<VouchedError> errors;
}
