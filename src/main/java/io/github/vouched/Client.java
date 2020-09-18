package io.github.vouched;

import io.github.vouched.graphql.GraphQlClient;
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
        UpdatedKey updatedKey = client.doRequest(q, UpdatedKey.class, "updateSecretClientKey");
        return updatedKey.secretClientKey;
    }

    public static class UpdatedKey {
        public String secretClientKey;
    }

    public CrossCheck crosscheck(CrossCheckRequest request) {
        InputAddress address = null;
        if( request.address != null ){
            address = new InputAddress();
            address.unit = request.address.unit;
            address.streetAddress = request.address.streetAddress;
            address.city = request.address.city;
            address.state = request.address.state;
            address.postalCode = request.address.postalCode;
            address.country = request.address.country;
        }
        GraphqlMutation q = new DefaultGraphqlMutation("crosscheckIdentity");
        q
                .addParameter("email", request.email)
                .addParameter("phone", request.phone)
                .addParameter("firstName", request.firstName)
                .addParameter("lastName", request.lastName)
                .addParameter("ipAddress", request.ipAddress)
                .addObjectParameter("address", address);

        q.addResultAttributes(new GraphQlResult().getAttributes(CrossCheck.class));

        GraphQlClient client = new GraphQlClient(Config.get().getServer(), this.key);
        return client.doRequest(q, CrossCheck.class, "crosscheckIdentity");
    }
    public Job submitJob(JobRequestInput jobRequest) {
        GraphqlMutation q = new DefaultGraphqlMutation("submitJob");
        q
                .addParameter("type", jobRequest.type)
                .addParameter("callbackURL", jobRequest.callbackURL)
                .addObjectParameter("params", jobRequest.parameters)
                .addObjectParameter("properties", jobRequest.properties);

        q.addResultAttributes(new GraphQlResult().getAttributes(Job.class));

        GraphQlClient client = new GraphQlClient(Config.get().getServer(), this.key);
        return client.doRequest(q, Job.class, "submitJob");
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
        q
                .addParameter("id", filter.id)
                .addParameter("type", filter.type)
                .addObjectParameter("ids", filter.ids)
                .addParameter("token", filter.token)
                .addParameter("page", filter.page)
                .addParameter("pageSize", filter.pageSize)
                .addParameter("sortBy", filter.sortBy)
                .addParameter("sortOrder", filter.sortOrder)
                .addParameter("status", filter.status)
                .addParameter("to", filter.to)
                .addParameter("from", filter.from)
                .addParameter("withPhotos", filter.withPhotos)
                .addParameter("withPhotoUrls", filter.withPhotoUrls);

        q.addResultAttributes(new GraphQlResult().getAttributes(Jobs.class));

        GraphQlClient client = new GraphQlClient(Config.get().getServer(), this.key);
        return client.doRequest(q, Jobs.class, "jobs");
    }

    private final String key;
}



