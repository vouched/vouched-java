package com.vouched.sdk;

import com.vouched.sdk.error.VouchedException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ClientTest {
  @Test
  public void shouldFailWithInvalidToken() {
      Config config = Config.get();
      Client client = new Client(config.getServer(), "invalid");

    try {
      client.getJobs();
      fail("Exception expected");
    } catch (VouchedException e) {
      assertEquals(VouchedException.Unauthorized, e.getType());
    }
  }
}
