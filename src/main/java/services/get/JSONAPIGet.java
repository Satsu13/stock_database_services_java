package services.get;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import services.BaseJSONAPIService;

import java.io.InputStream;
import java.util.List;

public class JSONAPIGet extends BaseJSONAPIService {
    public JSONAPIGet(String ip, int port) {
        super(ip, port);
    }

    public <T> T getResource(Class<T> resourceClass, int id) {
        try {
            return tryGettingResource(resourceClass, id);
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    private  <T> T tryGettingResource(Class<T> resourceClass, int id) throws UnirestException {
        HttpResponse<InputStream> response = getResourceByID(resourceClass, id);
        ResourceConverter converter = buildConverter(resourceClass);
        JSONAPIDocument<T> jsonapiDocument = converter.readDocument(response.getBody(), resourceClass);
        return jsonapiDocument.get();
    }

    private HttpResponse<InputStream> getResourceByID(Class resourceClass, int id) throws UnirestException {
        String requestString = getBaseResourceString(resourceClass);
        requestString += buildIDString(id);
        requestString += buildIncludeString(resourceClass);
        return Unirest.get(requestString).asBinary();
    }

    private String buildIDString(int id) {
        return id + "/";
    }

    public <T> List<T> getResourceCollection(Class<T> resourceClass) {
        try {
            return tryGettingResourceCollection(resourceClass);
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    private  <T> List<T> tryGettingResourceCollection(Class<T> resourceClass) throws UnirestException {
        HttpResponse<InputStream> response = getCollectionResponse(resourceClass);
        ResourceConverter converter = buildConverter(resourceClass);
        JSONAPIDocument<List<T>> jsonapiDocument = converter.readDocumentCollection(response.getBody(), resourceClass);
        return jsonapiDocument.get();
    }

    private HttpResponse<InputStream> getCollectionResponse(Class resourceClass) throws UnirestException {
        String requestString = getBaseResourceString(resourceClass);
        requestString += buildIncludeString(resourceClass);
        return Unirest.get(requestString).asBinary();
    }
}
