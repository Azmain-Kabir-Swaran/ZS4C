package androidExamples;

import android.app.Service;
import android.os.PowerManager;
import android.os.IBinder;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager.WakeLock;

public class Android29 extends Service {

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void onCreate() {
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Tag");
        wl.acquire();
    }

    @Override
    public void onDestroy() {
        WakeLock wl = null;
        wl.release();
    }

}