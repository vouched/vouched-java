package io.github.vouched;

import java.util.List;

public class JobRequest {
    public String type = "id-verification";
    public String callbackURL;
    public List<JobProperty> properties;
    public JobParameters parameters = new JobParameters();
}
