package io.github.vouched;

import org.junit.Test;

import static org.junit.Assert.*;

public class GetJobsTest {
  @Test
  public void shouldRespondWithJobs() throws VouchedException {
    Client client = new Client(Config.get().getPrivateKey());

    Jobs jobs = client.getJobs(new JobsFilter());
    assertTrue(jobs.total > 0);
  }

  @Test
  public void shouldRetreiveSingleJobById() throws VouchedException {
    Client client = new Client(Config.get().getPrivateKey());
    String jobId = Config.get().getTestExistingJobId();

    JobsFilter f = new JobsFilter();
    f.id = jobId;

    Jobs jobs = client.getJobs(f);
    assertEquals(1, jobs.total);
    assertEquals(f.id, jobs.items[0].id);
  }
}
