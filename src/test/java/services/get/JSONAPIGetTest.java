package services.get;

import database.stock_data.StockDay;
import database.stock_data.StockHistory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static config.TestConfiguration.IP;
import static config.TestConfiguration.PORT;
import static org.junit.Assert.assertEquals;

public class JSONAPIGetTest {
    JSONAPIGet jsonapiGet;

    @Before
    public void setUp() throws Exception {
        jsonapiGet = new JSONAPIGet(IP, PORT);
    }

    @Test
    public void testGetStockHistory() throws Exception {
        StockHistory stockHistory = jsonapiGet.getResource(StockHistory.class, 1);
        testFirstStockHistory(stockHistory);
    }

    private void testFirstStockHistory(StockHistory stockHistory) {
        assertEquals(stockHistory.getTicker(), "test_history_1");
        assertEquals(stockHistory.getStockDays().size(), 2);
        testFirstStockHistoriesDays(stockHistory);
    }

    private void testFirstStockHistoriesDays(StockHistory stockHistory) {
        StockDay day = stockHistory.getStockDays().get(0);
        assertEquals(day.getValue(), 5, 0.0001);
        assertEquals(day.getDate(), "2015-06-22");
    }

    @Test
    public void testGetStockHistories() throws Exception {
        List<StockHistory> stockHistories = jsonapiGet.getResourceCollection(StockHistory.class);
        assertEquals(stockHistories.size(), 2);
        testFirstStockHistory(stockHistories.get(0));
    }
}