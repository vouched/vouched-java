package com.vouched.sdk;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Base64;

public class ImageUtils {
    public static String loadBase64Image(String resourcePath) throws URISyntaxException, IOException {
        java.net.URL url = ImageUtils.class.getResource(resourcePath);
        java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
        byte[] bytes = Files.readAllBytes(resPath);

        byte[] encoded = Base64.getEncoder().encode(bytes);
        return new String(encoded);
    }
}
