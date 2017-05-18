package services;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import database.stock_data.StockHistory;

import java.io.InputStream;
import java.util.List;

public class StockHistoryService {
    private String ip;
    private int port;

    public StockHistoryService(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public List<StockHistory> getStockHistories() {
        try {
            return tryGettingStockHistories();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    private List<StockHistory> tryGettingStockHistories() throws UnirestException {
        ResourceConverter converter = new ResourceConverter(StockHistory.class);
        HttpResponse<InputStream> response = getRequest("stock-histories").asBinary();
        JSONAPIDocument<List<StockHistory>> jsonapiDocument =
                converter.readDocumentCollection(response.getBody(), StockHistory.class);
        return jsonapiDocument.get();
    }

    private GetRequest getRequest(String route) {
        return Unirest.get("HTTP://" + ip + ":" + port + "/" + route);
    }
}
