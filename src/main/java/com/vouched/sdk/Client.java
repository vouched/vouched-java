package com.vouched.sdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.vouched.sdk.graphql.GraphQlClient;
import com.vouched.sdk.graphql.ResponseDeserializer;
import org.mountcloud.graphql.request.query.DefaultGraphqlQuery;
import org.mountcloud.graphql.request.query.GraphqlQuery;

public class Client {
    public Client(String key) {
        this.key = key;
    }

    public Jobs getJobs() throws VouchedException {
        GraphqlQuery query = new DefaultGraphqlQuery("jobs");
        query.addResultAttributes("total");

        GraphQlClient client = new GraphQlClient(Config.get().getServer(), this.key, createResponseMapper());
        return client.doRequest(query, Jobs.class);
    }

    /*
    public Job submit(SubmitJob submitJob) throws VouchedException {
        GraphqlMutation mutation = new DefaultGraphqlMutation("submitJob");

        mutation
                .addParameter("type", submitJob.type)
                .addParameter("callbackURL", submitJob.callbackURL)
                .addObjectParameter("params", submitJob.parameters)
                .addObjectParameter("properties", submitJob.properties);

        GraphQlClient client = new GraphQlClient(Config.get().getServer(), this.key);
        return client.doRequest(mutation, new TypeReference<Response<Job>>() {});
    }

     */

    private ObjectMapper createResponseMapper() {
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addDeserializer(Jobs.class, new ResponseDeserializer(Jobs.class, "jobs"));
        mapper.registerModule(module);

        return mapper;
    }

    private final String key;
}

