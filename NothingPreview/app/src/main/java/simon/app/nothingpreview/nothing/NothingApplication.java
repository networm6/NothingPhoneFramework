package simon.app.nothingpreview.nothing;

import android.app.Application;

public class NothingApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LedCenter.contentResolver = getContentResolver();
    }
}
