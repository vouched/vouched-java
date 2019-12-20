package com.vouched.sdk;

import com.vouched.sdk.graphql.GraphQlClient;
import org.mountcloud.graphql.request.mutation.DefaultGraphqlMutation;
import org.mountcloud.graphql.request.mutation.GraphqlMutation;
import org.mountcloud.graphql.request.query.DefaultGraphqlQuery;
import org.mountcloud.graphql.request.query.GraphqlQuery;


public class Client {
    public Client(String key) {
        this.key = key;
    }

    public Jobs getJobs() throws VouchedException {
        GraphqlQuery query = new DefaultGraphqlQuery("jobs");
        query.addResultAttributes("total");

        GraphQlClient client = new GraphQlClient(Config.get().getServer(), this.key);
        return client.doRequest(query, Jobs.class);
    }

    public Job submit(SubmitJob submitJob) throws VouchedException {
        GraphqlMutation mutation = new DefaultGraphqlMutation("submitJob");

        mutation
                .addParameter("type", submitJob.type)
                .addParameter("callbackURL", submitJob.callbackURL)
                .addObjectParameter("params", submitJob.parameters)
                .addObjectParameter("properties", submitJob.properties);

        GraphQlClient client = new GraphQlClient(Config.get().getServer(), this.key);
        return client.doRequest(mutation, Job.class);
    }

    private final String key;
}
