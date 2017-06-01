package services;

import database.stock_data.StockHistory;

import java.util.List;

public class StockHistoryService extends JSONAPIService {
    public StockHistoryService(String ip, int port) {
        super(ip, port);
    }

    public StockHistory getStockHistory(int id) {
        return getResource("stock-histories", id, StockHistory.class);
    }

    public List<StockHistory> getStockHistories() {
        return getResourceCollection("stock-histories", StockHistory.class);
    }
}
