package database.stock_data;

import org.junit.Before;
import org.junit.Test;
import services.StockHistoryService;

import java.util.List;

import static config.TestConfiguration.IP;
import static config.TestConfiguration.PORT;
import static org.junit.Assert.assertEquals;

public class StockHistoryServiceTest {
    StockHistoryService stockHistoryService;

    @Before
    public void setUp() throws Exception {
        stockHistoryService = new StockHistoryService(IP, PORT);
    }

    @Test
    public void testGetStockHistories() throws Exception {
        List<StockHistory> stockHistories = stockHistoryService.getStockHistories();
        assertEquals(stockHistories.size(), 2);
    }

    @Test
    public void testGetStockHistory() throws Exception {
        StockHistory stockHistory = stockHistoryService.getStockHistory(1);
        assertEquals(stockHistory.getTicker(), "test_history_1");
        assertEquals(stockHistory.getStockDays().size(), 2);
    }

    @Test
    public void testStockHistoriesDaysAreComplete() throws Exception {
        StockHistory stockHistory = stockHistoryService.getStockHistory(1);
        StockDay day = stockHistory.getStockDays().get(0);
        assertEquals(day.getValue(), 5, 0.0001);
        assertEquals(day.getDate(), "2015-06-22");
    }
}