package com.vouched.sdk;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    public String getServer() {
        return dotenv.get("SERVER", "https://verify.vouched.id/graphql");
    }

    public String getPrivateKey() {
        return dotenv.get("PRIVATE_KEY");
    }

    public static Config get() {
        if (config == null) {
            config = new Config();
        }

        return config;
    }

    private Dotenv dotenv;

    private Config() {
        dotenv = Dotenv.load();
    }


    private static Config config;
}

