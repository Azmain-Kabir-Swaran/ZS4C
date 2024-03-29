package androidExamples;

import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpHost;
import java.io.IOException;

public class Android30 {

    public static String main(String[] args) {
        HttpHost target = new HttpHost("http://" + ServiceWrapper.SERVER_HOST,ServiceWrapper.SERVER_PORT);
        HttpGet get = new HttpGet("/list");
        String result=null;
        HttpEntity entity = null;
        HttpClient client = new DefaultHttpClient();
        try {
            HttpResponse response=client.execute(target, get);
            entity = response.getEntity();
            result = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (entity!=null)
                try {
                    entity.consumeContent();
                } catch (IOException e) {}
        }
        return result;
    }

}
class ServiceWrapper
{
    static String SERVER_HOST = "host";
    static int SERVER_PORT = 8080;
}