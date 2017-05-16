import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class UnirestTest {
    @Test
    public void test() throws Exception {
        HttpResponse<String> response = Unirest.get("http://google.com").asString();
        System.out.println(response.getBody());
    }
}
