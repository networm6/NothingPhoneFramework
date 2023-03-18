package simon.app.nothingpreview.nothing;

import android.content.ContentResolver;
import android.content.Context;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;

public class LedCenter {
    public static ContentResolver contentResolver;
    private static int sCurrentBrightnessMax = -1;
    private static int sCurrentBrightnessMin = -1;

    public static boolean getEnable() {
        return Settings.Global.getInt(contentResolver, "led_effect_enable", 0) == 1;
    }

    public static boolean setEnable(int current) {
        return Settings.Global.putInt(contentResolver, "led_effect_enable", current);
    }

    public static int getLedBrightnessMax() {
        int i = sCurrentBrightnessMax;
        if (i != -1) {
            return i;
        }
        int i2 = TextUtils.equals(SystemProperties.get("ro.phone.shell.color"), "white") ? 1306 : 3840;
        sCurrentBrightnessMax = i2;
        return i2;
    }

    public static int getLedBrightnessMin() {
        int i = sCurrentBrightnessMin;
        if (i != -1) {
            return i;
        }
        int i2 = TextUtils.equals(SystemProperties.get("ro.phone.shell.color"), "white") ? 241 : 750;
        sCurrentBrightnessMin = i2;
        return i2;
    }

}
