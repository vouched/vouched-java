package com.vouched.sdk;

import org.junit.Test;

import static org.junit.Assert.*;

public class RemoveJobTest {
  @Test
  public void shouldFailToRemoveNonExistingJob() throws VouchedException {
    Client client = new Client(Config.get().getPrivateKey());

    try {
      client.removeJob("OWIOSI5O1");
      fail("Exception expected");
    } catch (VouchedException e) {
      assertEquals(VouchedError.InvalidRequestError, e.getType());
    }
  }

  @Test
  public void shouldRemoveJob() throws VouchedException {
    Client client = new Client(Config.get().getPrivateKey());

    Job job = client.removeJob("OWIOSI5O");
    assertEquals("removed", job.status);
  }
}
