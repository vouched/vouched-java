package io.github.vouched;

import java.util.Map;

public class JobRequest {
    public String type = "id-verification";
    public String callbackURL;
    public Map<String, String> properties;
    public JobParameters parameters = new JobParameters();
}
