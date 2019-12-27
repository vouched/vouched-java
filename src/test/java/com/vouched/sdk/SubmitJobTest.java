package com.vouched.sdk;

import org.junit.Test;

import static org.junit.Assert.*;

public class SubmitJobTest {
  @Test
  public void shouldSubmitJob() throws Exception {
    Client client = new Client(Config.get().getPrivateKey());

    String img = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUA" +
            "AAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO" +
            "9TXL0Y4OHwAAAABJRU5ErkJggg==";

    JobRequest jobRequest = new JobRequest();
//    jobRequest.parameters.userPhoto = ImageUtils.loadBase64Image("/large.jpg");
    jobRequest.parameters.userPhoto = img;
//    jobRequest.parameters.idPhoto = ImageUtils.loadBase64Image("/small.jpg");
    jobRequest.parameters.idPhoto = img;
    jobRequest.parameters.firstName = "Thor Thunder";
    jobRequest.parameters.lastName = "odinson";
    jobRequest.parameters.dob = "06/22/1970";

    Job job = client.submitJob(jobRequest);
    assertNotNull(job.id);
  }
}
