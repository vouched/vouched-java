package io.github.vouched;

import java.util.List;

public class JobRequestInput {
  public String type = "id-verification";
  public String callbackURL;
  public List<JobProperty> properties;
  public JobParametersInput parameters = new JobParametersInput();
}
