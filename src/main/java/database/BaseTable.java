package database;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Links;
import com.github.jasminb.jsonapi.annotations.Meta;

public class BaseTable {
    @Id
    private String id;

    @Meta
    private Meta meta;

    @Links
    private Links links;
}
