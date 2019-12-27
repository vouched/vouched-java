package com.vouched.sdk;

import java.util.List;

public class Job {
  public String id;
  public String status;
  public String submitted;
//  public JobRequest request;
  public JobResult result;
  public VouchedError[] errors;
}