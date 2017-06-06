package services;

import com.github.jasminb.jsonapi.ResourceConverter;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import database.stock_data.StockDay;

import java.lang.reflect.Field;

public class BaseJSONAPIService {
    private String ip;
    private int port;

    public BaseJSONAPIService(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    protected String getResourceString(Class resourceClass) {
        Type typeAnnotation = (Type) resourceClass.getDeclaredAnnotation(Type.class);
        return typeAnnotation.value() + "/";
    }

    protected String buildIncludeString(Class resourceClass) {
        if (hasIncludes(resourceClass)) {
            return "?include=" + buildIncludes(resourceClass);
        }
        return "";
    }

    private boolean hasIncludes(Class resourceClass) {
        for (Field field : resourceClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Relationship.class)) {
                return true;
            }
        }
        return false;
    }

    private String buildIncludes(Class resourceClass) {
        String includes = "";
        for (Field field : resourceClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Relationship.class)) {
                includes += field.getDeclaredAnnotation(Relationship.class).value() + "&";
            }
        }
        return includes.substring(0, includes.length() - 1);
    }

    protected GetRequest makeGetRequest(String route) {
        return Unirest.get("HTTP://" + ip + ":" + port + "/" + route);
    }

    protected  <T> ResourceConverter buildConverter(Class<T> resourceClass) {
        return new ResourceConverter(resourceClass, StockDay.class);
    }
}
