package com.vouched.sdk.graphql;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.vouched.sdk.Jobs;
import com.vouched.sdk.VouchedError;
import com.vouched.sdk.VouchedException;
import org.mountcloud.graphql.request.GraphqlRequest;
import org.mountcloud.graphql.request.GraphqlRequestType;
import org.mountcloud.graphql.request.query.GraphqlQuery;
import org.mountcloud.graphql.response.DefaultGraphqlResponse;
import org.mountcloud.graphql.response.GraphqlResponse;
import org.mountcloud.graphql.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GraphQlClient {
    public GraphQlClient(String server, String key, ObjectMapper responseMapper) {
        this.server = server;
        this.key = key;
        this.responseMapper = responseMapper;
    }

    public <T> T doRequest(GraphqlRequest request, Class<T> responseClass) throws VouchedException {
        try {
            String result = doHttpRequest(request.toString(), GraphqlRequestType.POST);

            System.out.println(result);

            if (result == null) throw new IOException("Failed to fetch");

            return responseMapper.readValue(result, responseClass);
        } catch (IOException e) {
            throw new VouchedException(e.getMessage(), VouchedError.ConnectionError);
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

    private final String server;
    private final String key;
    private ObjectMapper responseMapper;
}
