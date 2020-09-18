package io.github.vouched;

import org.junit.Test;
import com.google.gson.*;
import static org.junit.Assert.*;

public class CrossCheckTest {
  @Test
  public void shouldRespondWithCrossCheck() throws VouchedException {
    Client client = new Client(Config.get().getPrivateKey());
    CrossCheckRequest request = new CrossCheckRequest();
    String email = "baoman@gmail.com";
    String phone = "917-800-0000";
    String firstName = "Tony";
    String lastName = "Bao";
    CrossCheckAddress address = new CrossCheckAddress();
    address.streetAddress = "1 Main St";
    address.city = "Seattle";
    address.state = "WA";
    address.postalCode = "98041";
    String ipAddress = "52.47.73.72";

    request.firstName = firstName;
    request.lastName = lastName;
    request.email = email;
    request.phone = phone;
    request.address = address;
    request.ipAddress = ipAddress;
    

    CrossCheck crosscheck = client.crosscheck(request);
    assertEquals(crosscheck.id!= null, true);

    // Gson gson = new GsonBuilder().setPrettyPrinting().create();
    // String json = gson.toJson(crosscheck);
    // System.out.println(json);

  }

}
