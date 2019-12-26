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
        GraphqlMutation q = new DefaultGraphqlMutation("updateSecretClientKey");

        q.addParameter("secretClientKey", secretClientKey);
        q.addResultAttributes(new GraphQlResult().getAttributes(UpdatedKey.class));

        GraphQlClient client = new GraphQlClient(Config.get().getServer(), this.key);
        return client.doRequest(q, UpdatedKey.class, "updateSecretClientKey").secretClientKey;
    }

    public static class UpdatedKey {
        public String secretClientKey;
    }

    public Job submit(JobRequest jobRequest) {
        GraphqlMutation q = new DefaultGraphqlMutation("submitJob");

        q
                .addParameter("type", jobRequest.type)
                .addParameter("callbackURL", jobRequest.callbackURL)
                .addObjectParameter("params", jobRequest.parameters)
                .addObjectParameter("properties", jobRequest.properties);

        GraphQlClient client = new GraphQlClient(Config.get().getServer(), this.key);
        return client.doRequest(q, Job.class, "job");
    }

    public Job removeJob(String id) {
        GraphqlMutation q = new DefaultGraphqlMutation("removeJob");
        q.addParameter("id", id);
        q.addResultAttributes(new GraphQlResult().getAttributes(Job.class));

        GraphQlClient client = new GraphQlClient(Config.get().getServer(), this.key);
        return client.doRequest(q, Job.class, "removeJob");
    }

    public Jobs getJobs(JobsFilter filter) {
        GraphqlQuery q = new DefaultGraphqlQuery("jobs");
        q.addParameter("id", filter.id);
        q.addParameter("type", filter.type);
        q.getRequestParameter().addObjectParameter("ids", filter.ids);
        q.addParameter("token", filter.token);
        q.addParameter("page", filter.page);
        q.addParameter("pageSize", filter.pageSize);
        q.addParameter("sortBy", filter.sortBy);
        q.addParameter("sortOrder", filter.sortOrder);
        q.addParameter("status", filter.status);
        q.addParameter("to", filter.to);
        q.addParameter("from", filter.from);
        q.addParameter("withPhotos", filter.withPhotos);
        q.addResultAttributes(new GraphQlResult().getAttributes(Jobs.class));

        GraphQlClient client = new GraphQlClient(Config.get().getServer(), this.key);
        return client.doRequest(q, Jobs.class, "jobs");
    }

    private final String key;
}



