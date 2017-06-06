package services;

import services.get.JSONAPIGet;

import java.util.List;

public class JSONAPIService {

    private JSONAPIGet jsonapiGet;

    public JSONAPIService(String ip, int port) {
        jsonapiGet = new JSONAPIGet(ip, port);
    }

    public <T> T getResource(Class<T> resourceClass, int id) {
        return jsonapiGet.getResource(resourceClass, id);
    }

    public <T> List<T> getResourceCollection(Class<T> resourceClass) {
        return jsonapiGet.getResourceCollection(resourceClass);
    }
}
