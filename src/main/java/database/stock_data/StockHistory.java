package database.stock_data;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import database.BaseTable;

import java.util.List;

@Type("stock-histories")
public class StockHistory extends BaseTable {
    private String ticker;

    @Relationship("stock-days")
    private List<StockDay> stockDays;


    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public List<StockDay> getStockDays() {
        return stockDays;
    }

    public void setStockDays(List<StockDay> stockDays) {
        this.stockDays = stockDays;
    }
}
