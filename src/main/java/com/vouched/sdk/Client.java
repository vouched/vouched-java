package com.vouched.sdk;

import java.io.IOException;
import java.net.MalformedURLException;

import com.vouched.sdk.error.VouchedException;

import java.util.HashMap;
import java.util.Map;

import org.mountcloud.graphql.GraphqlClient;
import org.mountcloud.graphql.request.query.DefaultGraphqlQuery;
import org.mountcloud.graphql.request.query.GraphqlQuery;
import org.mountcloud.graphql.response.GraphqlResponse;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class Client {
    public Client(String server, String key) {
        this.server = server;
        this.key = key;
    }

    public Jobs getJobs() throws VouchedException {
        GraphqlClient client = createClient();

        GraphqlQuery query = new DefaultGraphqlQuery("jobs");
        query.addResultAttributes("total");

        try {
            GraphqlResponse response = client.doQuery(query);

            //this map is graphql result
            Map data = response.getData();

            logger.info("Got results", data);

            return null;
        } catch (IOException e) {
            throw new VouchedException(e.getMessage(), VouchedException.CommunicationError);
        }
    }

    private GraphqlClient createClient() {
        GraphqlClient client = GraphqlClient.buildGraphqlClient(server);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Cache-Control", "no-cache");
        headers.put("X-Api-Key", key);
        client.setHttpHeaders(headers);

        return client;
    }

    private static Logger logger = LoggerFactory.getLogger(Client.class);
    private String key;
    private String server;
}
