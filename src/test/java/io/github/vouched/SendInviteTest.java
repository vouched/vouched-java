package io.github.vouched;

import org.junit.Test;

import static org.junit.Assert.*;
import java.util.Vector;

public class SendInviteTest {
  @Test
  public void shouldSendInvite() throws Exception {
    Client client = new Client(Config.get().getPrivateKey());

    SendInviteInput request = new SendInviteInput();
    request.send = false;
    Invite invite = client.sendInvite(request);
    assertNotNull(invite.qrCode);
    assertNotNull(invite.jobId);
  }
}
