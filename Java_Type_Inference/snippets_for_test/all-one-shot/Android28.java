package androidExamples;
import android.app.Application;
import android.content.Context;


public class Android28 extends android.app.Application {

    private static Android28 instance;

    public Android28() {
     instance = this;
    }

    public static Context getContext() {
     return instance;
    }

}