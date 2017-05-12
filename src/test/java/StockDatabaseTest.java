import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;

public class StockDatabaseTest {
    //   http://jsonapi.org/examples/     <---- example of including resources in url

    public static final String IP = "192.168.99.100";
    public static final int PORT = 3000;

    @Test
    public void testRailsDefault() throws Exception {
        testResponse("stock-histories/1", "stock-days");
    }

    private void testResponse(String resource, String... includes) throws UnirestException {
        JsonNode response = getResponse(resource, includes);
        printResponse(response);
    }

    private JsonNode getResponse(String resource, String... includes) throws UnirestException {
        String url = buildResponseURL(resource, includes);
        HttpResponse<JsonNode> response = Unirest.get(url).asJson();
        return response.getBody();
    }

    private String buildResponseURL(String resource, String[] includes) {
        String responseURL = buildBaseURL(resource);
        if (includes.length > 0) {
            responseURL += buildIncludeString(includes);
        }
        return responseURL;
    }

    private String buildBaseURL(String resource) {
        return "http://" + IP + ":" + PORT + "/" + resource;
    }

    private String buildIncludeString(String[] includes) {
        String includeString = "?include=" + includes[0];
        for (int i = 1; i < includes.length; i++) {
            includeString += "&" + includes[i];
        }
        return includeString;
    }

    private void printResponse(JsonNode response) {
        System.out.println(response.toString());
    }
}
