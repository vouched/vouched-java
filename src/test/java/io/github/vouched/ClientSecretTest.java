package io.github.vouched;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClientSecretTest {
  @Test
  public void shouldFailOnInvalidSecret() {
    Client client = new Client("invalid");

    try {
      client.getJobs(new JobsFilter());
      fail("Exception expected");
    } catch (VouchedException e) {
      assertEquals(VouchedError.UNAUTHENTICATED, e.getType());
    }
  }

  @Test
  public void shouldFailToUpdateToSmallSecret() throws VouchedException {
    Client client = new Client(Config.get().getPrivateKey());

    try {
      client.updateSecretClientKey("x");
      fail("Exception expected");
    } catch (VouchedException e) {
      assertEquals(VouchedError.InvalidRequestError, e.getType());
    }
  }

  @Test
  public void shouldUpdateSecretSuccessfully() throws VouchedException {
    Client client = new Client(Config.get().getPrivateKey());

    Config config = Config.get();
    String privateKey = config.getPrivateKey();
    String key = client.updateSecretClientKey(privateKey);
    assertEquals(Config.get().getPrivateKey(), key);
  }
}
