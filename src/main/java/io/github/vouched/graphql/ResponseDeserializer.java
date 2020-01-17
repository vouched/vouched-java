package io.github.vouched.graphql;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.*;
import io.github.vouched.VouchedException;

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

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        if (errors != null && errors.size() > 0) {
            ResponseError responseError = mapper.treeToValue(errors.get(0), ResponseError.class);
            throw VouchedException.from(responseError);
        }

        JsonNode data = node.get("data");
        JsonNode response = data.get(responseFieldName);

        return mapper.treeToValue(response, responseClass);
    }
}
