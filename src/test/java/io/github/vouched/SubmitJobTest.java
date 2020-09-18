package io.github.vouched;

import org.junit.Test;

import static org.junit.Assert.*;
import java.util.Vector;

public class SubmitJobTest {
  @Test
  public void shouldSubmitJob() throws Exception {
    Client client = new Client(Config.get().getPrivateKey());

    String img = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUA" +
            "AAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO" +
            "9TXL0Y4OHwAAAABJRU5ErkJggg==";

    JobRequestInput jobRequest = new JobRequestInput();
    jobRequest.parameters.userPhoto = img;
    jobRequest.parameters.idPhoto = img;
    jobRequest.parameters.firstName = "Thor Thunder";
    jobRequest.parameters.lastName = "odinson";
    jobRequest.parameters.dob = "06/22/1970";
    jobRequest.callbackURL = "https://www.google.com";
    JobProperty prop = new JobProperty();
    prop.name = "internal_id";
    prop.value = "123";
    Vector properties = new Vector();
    properties.add(prop);
    jobRequest.properties = properties;

    Job job = client.submitJob(jobRequest);
    assertNotNull(job.id);
  }
}
