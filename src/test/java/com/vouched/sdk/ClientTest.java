package com.vouched.sdk;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
}
