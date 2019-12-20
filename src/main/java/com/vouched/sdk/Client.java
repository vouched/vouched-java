package com.vouched.sdk;

import com.vouched.sdk.graphql.GraphQlClient;
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
        return client.query(query, Jobs.class);
    }

    private final String key;
}
