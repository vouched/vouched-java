package com.vouched.sdk.graphql;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vouched.sdk.VouchedException;

import java.io.IOException;

public class ResponseDeserializer<T> extends JsonDeserializer<T> {
    private final Class<T> responseClass;
    private final String responseFieldName;

    public ResponseDeserializer(Class<T> responseClass, String responseFieldName) {
        this.responseClass = responseClass;
        this.responseFieldName = responseFieldName;
    }

    @Override
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        JsonNode errors = node.get("errors");

        if (errors != null && errors.size() > 0) {
            ResponseError responseError = new ObjectMapper().treeToValue(errors.get(0), ResponseError.class);
            throw VouchedException.from(responseError);
        }

        JsonNode data = node.get("data");
        JsonNode response = data.get(responseFieldName);

        return new ObjectMapper().treeToValue(response, responseClass);
    }
}
