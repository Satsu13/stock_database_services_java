package database;

import com.github.jasminb.jsonapi.annotations.Id;

public class BaseTable {
    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
