package services.post;

import database.stock_data.StockDay;
import database.stock_data.StockHistory;
import org.junit.Before;
import org.junit.Test;
import services.get.JSONAPIGet;

import java.util.LinkedList;
import java.util.List;

import static config.TestConfiguration.IP;
import static config.TestConfiguration.PORT;
import static org.junit.Assert.assertTrue;

public class JSONAPIPostTest {
    JSONAPIPost post;
    JSONAPIGet get;

    @Before
    public void setUp() throws Exception {
        post = new JSONAPIPost(IP, PORT);
        get = new JSONAPIGet(IP, PORT);
    }

    @Test
    public void testPostStockHistory() throws Exception {
        StockHistory testStockHistory = buildStockHistory();
        post.post(testStockHistory);
        List<StockHistory> stockHistories = get.getResourceCollection(StockHistory.class);
        assertTrue(stockHistories.size() >= 3);
    }

    private StockHistory buildStockHistory() {
        StockHistory stockHistory = new StockHistory();
        stockHistory.setTicker("test_stock_history");
        List<StockDay> testStockDays = buildStockDays();
        stockHistory.setStockDays(testStockDays);
        return stockHistory;
    }

    private List<StockDay> buildStockDays() {
        List<StockDay> stockDays = new LinkedList<>();
        stockDays.add(buildStockDay(1));
        stockDays.add(buildStockDay(2));
        return stockDays;
    }

    private StockDay buildStockDay(Integer i) {
        StockDay stockDay = new StockDay();
        stockDay.setDate("test_date_" + i);
        stockDay.setValue(i.doubleValue());
        return stockDay;
    }
}