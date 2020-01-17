package io.github.vouched.example;

import io.github.vouched.Client;
import io.github.vouched.Config;
import io.github.vouched.Jobs;
import io.github.vouched.JobsFilter;

public class Example {
    public static void main(String[] args) {
        Client client = new Client(Config.get().getPrivateKey());
        Jobs jobs = client.getJobs(new JobsFilter());
        System.out.println("Job count is " + jobs.total);
    }
}
