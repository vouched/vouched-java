package io.github.vouched;

public class Job {
  public String id;
  public String status;
  public String submitted;
  public JobRequest request;
  public JobResult result;
  public VouchedError[] errors;
}