package com.vouched.sdk;

import java.util.List;

public class VouchedError {
    public static String InvalidRequestError = "InvalidRequestError";
    public static String FaceMatchError = "FaceMatchError";
    public static String NameMatchError = "NameMatchError";
    public static String BirthDateMatchError = "BirthDateMatchError";
    public static String InvalidIdPhotoError = "InvalidIdPhotoError";
    public static String InvalidUserPhotoError = "InvalidUserPhotoError";
    public static String AuthenticationError = "AuthenticationError";
    public static String ConnectionError = "ConnectionError";
    public static String InvalidIdBackPhotoError = "InvalidIdBackPhotoError";
    public static String UnknownSystemError = "UnknownSystemError";
    public static String UNAUTHENTICATED = "UNAUTHENTICATED";

    public String type;
    public String message;
    public String suggestion;
    public VouchedError[] errors;
}
