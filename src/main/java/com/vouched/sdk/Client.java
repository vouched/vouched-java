package com.vouched.sdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.vouched.sdk.graphql.GraphQlClient;
import com.vouched.sdk.graphql.ResponseDeserializer;
import org.mountcloud.graphql.request.mutation.DefaultGraphqlMutation;
import org.mountcloud.graphql.request.mutation.GraphqlMutation;
import org.mountcloud.graphql.request.query.DefaultGraphqlQuery;
import org.mountcloud.graphql.request.query.GraphqlQuery;

public class Client {
    public Client(String key) {
        this.key = key;
    }

    public Job submit(JobRequest jobRequest) throws VouchedException {
        GraphqlMutation mutation = new DefaultGraphqlMutation("submitJob");

        mutation
                .addParameter("type", jobRequest.type)
                .addParameter("callbackURL", jobRequest.callbackURL)
                .addObjectParameter("params", jobRequest.parameters)
                .addObjectParameter("properties", jobRequest.properties);

        GraphQlClient client = new GraphQlClient(Config.get().getServer(), this.key);
        return client.doRequest(mutation, Job.class, "job");
    }

    public Jobs getJobs() throws VouchedException {
        GraphqlQuery query = new DefaultGraphqlQuery("jobs");
        query.addResultAttributes("total");

        GraphQlClient client = new GraphQlClient(Config.get().getServer(), this.key);
        return client.doRequest(query, Jobs.class, "jobs");
    }

    private final String key;
}

