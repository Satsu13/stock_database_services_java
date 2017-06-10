package services.post;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.github.jasminb.jsonapi.exceptions.DocumentSerializationException;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import services.BaseJSONAPIService;

public class JSONAPIPost extends BaseJSONAPIService {
    public JSONAPIPost(String ip, int port) {
        super(ip, port);
    }

    public void post(Object jsonapiObject) {
        try {
            tryPosting(jsonapiObject);
        } catch (UnirestException | DocumentSerializationException e) {
            throw new RuntimeException(e);
        }
    }

    private void tryPosting(Object jsonapiObject) throws UnirestException, DocumentSerializationException {
        String uri = getBaseResourceString(jsonapiObject.getClass());
        byte[] body = serialize(jsonapiObject);
        Unirest.post(uri)
                .header("accept", "application/vnd.api+json")
                .header("Content-Type", "application/vnd.api+json")
                .body(body).asBinary();
    }

    private byte[] serialize(Object jsonapiObject) throws DocumentSerializationException {
        ResourceConverter converter = buildConverter(jsonapiObject.getClass());
        JSONAPIDocument jsonapiDocument = new JSONAPIDocument<>(jsonapiObject);
        return converter.writeDocument(jsonapiDocument);
    }
}
