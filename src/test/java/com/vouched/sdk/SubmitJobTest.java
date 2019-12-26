package com.vouched.sdk;

import org.junit.Test;

import static org.junit.Assert.*;

public class SubmitJobTest {
  @Test
  public void shouldSubmitJob() throws VouchedException {
    Client client = new Client(Config.get().getPrivateKey());

    JobRequest jobRequest = new JobRequest();
    jobRequest.parameters.idPhoto = "1";

    Job job = client.submit(jobRequest);
    assertNotNull(job);
  }
}
