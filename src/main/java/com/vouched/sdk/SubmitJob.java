package com.vouched.sdk;

import java.util.Map;

public class SubmitJob {
    public String type = "id-verification";
    public String callbackURL;
    public Map<String, String> properties;
    public JobParameters parameters = new JobParameters();
}
