package com.vouched.sdk.example;

import com.vouched.sdk.Client;
import com.vouched.sdk.Config;
import com.vouched.sdk.Jobs;
import com.vouched.sdk.JobsFilter;

public class Example {
    public static void main(String[] args) {
        Client client = new Client(Config.get().getPrivateKey());
        Jobs jobs = client.getJobs(new JobsFilter());
        System.out.println("Job count is " + jobs.total);
    }
}
