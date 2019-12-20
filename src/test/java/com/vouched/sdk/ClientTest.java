package com.vouched.sdk;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClientTest {
  @Test
  public void shouldFailWithInvalidToken() {
    Client client = new Client("invalid");

    try {
      client.getJobs();
      fail("Exception expected");
    } catch (VouchedException e) {
      assertEquals(VouchedException.UNAUTHENTICATED, e.getType());
    }
  }

  @Test
  public void shouldRespondWithJobs() throws VouchedException {
    Client client = new Client(Config.get().getPrivateKey());

    Jobs jobs = client.getJobs();
    assertTrue(jobs.total > 0);
  }
}
