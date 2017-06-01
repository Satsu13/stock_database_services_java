package services;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import database.stock_data.StockDay;

import java.io.InputStream;
import java.util.List;

public class JSONAPIService {
    private String ip;
    private int port;

    public JSONAPIService(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    protected <T> T getResource(String resource, int id, Class<T> resourceClass) {
        try {
            return tryGettingResource(resource, id, resourceClass);
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T tryGettingResource(String resource, int id, Class<T> resourceClass) throws UnirestException {
        HttpResponse<InputStream> response = getRequest(resource + "/" + id + "?include=stock-days").asBinary();
        ResourceConverter converter = new ResourceConverter(resourceClass, StockDay.class);
        JSONAPIDocument<T> jsonapiDocument = converter.readDocument(response.getBody(), resourceClass);
        return jsonapiDocument.get();
    }

    protected <T> List<T> getResourceCollection(String resource, Class<T> resourceClass) {
        try {
            return tryGettingResourceCollection(resource, resourceClass);
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> List<T> tryGettingResourceCollection(String resource, Class<T> resourceClass) throws UnirestException {
        HttpResponse<InputStream> response = getRequest(resource).asBinary();
        ResourceConverter converter = new ResourceConverter(resourceClass);
        JSONAPIDocument<List<T>> jsonapiDocument = converter.readDocumentCollection(response.getBody(), resourceClass);
        return jsonapiDocument.get();
    }

    private GetRequest getRequest(String route) {
        return Unirest.get("HTTP://" + ip + ":" + port + "/" + route);
    }
}
