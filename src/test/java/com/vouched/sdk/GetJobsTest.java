package com.vouched.sdk;

import org.junit.Test;

import static org.junit.Assert.*;

public class GetJobsTest {
  @Test
  public void shouldRespondWithJobs() throws VouchedException {
    Client client = new Client(Config.get().getPrivateKey());

    Jobs jobs = client.getJobs(new JobsFilter());
    assertTrue(jobs.total > 0);
  }
}
