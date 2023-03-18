package simon.app.nothingpreview.nothing;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class LedAnimUtils {
    public interface OnAnimCallback {

        void onAnimFinish();

        void onAnimRefresh(int i);

        void onAnimStart();

    }

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final OnAnimCallback callback;
    private volatile boolean isStop;
    private int currentFrame;
    private int frameSize;


    public LedAnimUtils(OnAnimCallback callback) {
        this.callback = callback;
    }

    public LedAnimUtils setFrameSize(int size) {
        this.frameSize = size;
        return this;
    }

    public void startAnim() {
        currentFrame = 0;
        isStop = false;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (currentFrame == 0)
                    callback.onAnimStart();
                if (!isStop) {
                    callback.onAnimRefresh(currentFrame++);
                    if (frameSize > currentFrame)
                        handler.postDelayed(this, 16);
                    else {
                        isStop = true;
                        callback.onAnimFinish();
                    }
                }
            }
        }, 16);
    }

    public void cancel() {
        isStop = true;
    }

}
