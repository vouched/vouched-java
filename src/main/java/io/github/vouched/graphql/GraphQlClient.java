package io.github.vouched.graphql;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.github.vouched.VouchedError;
import io.github.vouched.VouchedException;
import org.mountcloud.graphql.request.GraphqlRequest;
import org.mountcloud.graphql.request.GraphqlRequestType;
import org.mountcloud.graphql.util.HttpClientUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.*;

public class GraphQlClient {
    public GraphQlClient(String server, String key) {
        this.server = server;
        this.key = key;
    }

    public <T> T doRequest(GraphqlRequest request, Class<T> responseClass, String responseKey) throws VouchedException {
        try {
            String result = doHttpRequest(request.toString(), GraphqlRequestType.POST);
            // System.out.println(request.toString());
            // System.out.println(result);

            if (result == null) throw new IOException("Failed to fetch");
            ObjectMapper responseMapper = createResponseMapper(responseClass, responseKey);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(result);

            T r = responseMapper.readValue(result, responseClass);
            return r;
        } catch (IOException e) {

            throw new VouchedException(e.getMessage(), VouchedError.ConnectionError, null);
        }
    }

    private String doHttpRequest(String json, GraphqlRequestType type) throws IOException {
        if (!type.equals(GraphqlRequestType.POST)) throw new IllegalArgumentException();

        HttpClientUtil httpClientUtil = new HttpClientUtil();

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Cache-Control", "no-cache");
        headers.put("X-Api-Key", key);

        String result = httpClientUtil.doPostJson(server, json, headers);
        return result;
    }

    @SuppressWarnings("unchecked")
    private ObjectMapper createResponseMapper(Class responseClass, String responseKey) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        SimpleModule module = new SimpleModule();
        module.addDeserializer(responseClass, new ResponseDeserializer(responseClass, responseKey));
        mapper.registerModule(module);

        return mapper;
    }


    private final String server;
    private final String key;
}
