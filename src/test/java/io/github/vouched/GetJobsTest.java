package io.github.vouched;

import org.junit.Test;
import com.google.gson.*;
import static org.junit.Assert.*;

public class GetJobsTest {
  @Test
  public void shouldRespondWithJobs() throws VouchedException {
    Client client = new Client(Config.get().getPrivateKey());
    JobsFilter f = new JobsFilter();
    Jobs jobs = client.getJobs(f);
    assertTrue(jobs.total > 0);
  }

  @Test
  public void shouldRetreiveSingleJobById() throws VouchedException {
    Client client = new Client(Config.get().getPrivateKey());
    JobsFilter f = new JobsFilter();
    f.id = "OWIOSI5O";

    Jobs jobs = client.getJobs(f);
    assertEquals(0, jobs.total);
  }
}
