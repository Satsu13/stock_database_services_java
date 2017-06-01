package database.stock_data;

import com.github.jasminb.jsonapi.annotations.Type;
import database.BaseTable;

@Type("stock-days")
public class StockDay extends BaseTable {
    private Double value;
    private String date;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
