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

    public String updateSecretClientKey(String secretClientKey) {
        GraphqlMutation mutation = new DefaultGraphqlMutation("updateSecretClientKey");

        mutation.addParameter("secretClientKey", secretClientKey);
        mutation.addResultAttributes(new GraphQlResult().getAttributes(UpdatedKey.class));

        GraphQlClient client = new GraphQlClient(Config.get().getServer(), this.key);
        return client.doRequest(mutation, UpdatedKey.class, "updateSecretClientKey").secretClientKey;
    }

    public static class UpdatedKey {
        public String secretClientKey;
    }

    public Job submit(JobRequest jobRequest) {
        GraphqlMutation mutation = new DefaultGraphqlMutation("submitJob");

        mutation
                .addParameter("type", jobRequest.type)
                .addParameter("callbackURL", jobRequest.callbackURL)
                .addObjectParameter("params", jobRequest.parameters)
                .addObjectParameter("properties", jobRequest.properties);

        GraphQlClient client = new GraphQlClient(Config.get().getServer(), this.key);
        return client.doRequest(mutation, Job.class, "job");
    }

    public Job removeJob(String id) {
        GraphqlMutation mutation = new DefaultGraphqlMutation("removeJob");
        mutation.addParameter("id", id);

        GraphQlClient client = new GraphQlClient(Config.get().getServer(), this.key);
        return client.doRequest(mutation, Job.class, "job");
    }

    public Jobs getJobs(JobsFilter jobsFilter) {
        GraphqlQuery query = new DefaultGraphqlQuery("jobs");
        query.addResultAttributes(new GraphQlResult().getAttributes(Jobs.class));

        GraphQlClient client = new GraphQlClient(Config.get().getServer(), this.key);
        return client.doRequest(query, Jobs.class, "jobs");
    }

    private final String key;
}



