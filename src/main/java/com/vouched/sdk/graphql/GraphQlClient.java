package com.vouched.sdk.graphql;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vouched.sdk.VouchedException;
import org.mountcloud.graphql.request.GraphqlRequestType;
import org.mountcloud.graphql.request.query.GraphqlQuery;
import org.mountcloud.graphql.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GraphQlClient {
    public GraphQlClient(String server, String key) {
        this.server = server;
        this.key = key;
    }

    public <T> T query(GraphqlQuery query, Class<T> responseClass) throws VouchedException {
        try {
            String result = doHttpRequest(query.toString(), GraphqlRequestType.POST);

            if (result == null) throw new IOException("Failed to fetch");

            Response<T> response = objectMapper.readValue(result, Response.class);

            if (response.errors != null && !response.errors.isEmpty()) {
                throw VouchedException.from(response.errors.get(0));
            }

            return response.data;
        } catch (IOException e) {
            throw new VouchedException(e.getMessage(), VouchedException.CommunicationError);
        }
    }

    private String doHttpRequest(String json, GraphqlRequestType type) throws IOException {
        if (!type.equals(GraphqlRequestType.POST)) throw new IllegalArgumentException();

        HttpClientUtil httpClientUtil = new HttpClientUtil();

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Cache-Control", "no-cache");
        headers.put("X-Api-Key", key);

        return httpClientUtil.doPostJson(server, json, headers);
    }

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String server;
    private final String key;

    private static Logger logger = LoggerFactory.getLogger(GraphQlClient.class);
}
