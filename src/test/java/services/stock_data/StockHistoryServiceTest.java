package services.stock_data;

import database.stock_data.StockHistory;
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
}