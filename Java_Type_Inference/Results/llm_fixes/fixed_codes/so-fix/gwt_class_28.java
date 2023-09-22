import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;

public class gwt_class_28 {
    /**
     * Entry point classes define <code>onModuleLoad()</code>.
     */
    public static class Test implements EntryPoint {
        /**
         * The message displayed to the user when the server cannot be reached or
         * returns an error.
         */

        private static final String SERVER_URL = "http://localhost:8094";
        private static final String SERVER_ERROR = "An error occurred while "
                + "attempting to contact the server. Please check your network "
                + "connection and try again.";

        /**
         * This is the entry point method.
         */
        public void onModuleLoad() {

            RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, SERVER_URL);
            try {
                requestBuilder.sendRequest(null, new Jazz10RequestCallback());
            } catch (RequestException e) {
                Window.alert("Failed to send the message: " 
                        + e.getMessage());
            }
        }

        static class Jazz10RequestCallback implements RequestCallback {

            public void onError(Request request, Throwable exception) {
                Window.alert("Failed to send the message: "
                        + exception.getMessage());

            }

            public void onResponseReceived(Request request, Response response) {
                Window.alert(response.getText());
            }
        }
    }
}