package services.stock_data;

import database.stock_data.StockHistory;
import services.JSONAPIService;

import java.util.List;

public class StockHistoryService extends JSONAPIService {
    public StockHistoryService(String ip, int port) {
        super(ip, port);
    }

    public StockHistory getStockHistory(int id) {
        return getResource(StockHistory.class, id);
    }

    public List<StockHistory> getStockHistories() {
        return getResourceCollection(StockHistory.class);
    }
}
