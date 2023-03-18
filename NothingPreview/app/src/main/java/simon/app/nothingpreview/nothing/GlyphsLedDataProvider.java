package simon.app.nothingpreview.nothing;

import android.content.Context;
import android.text.TextUtils;
import android.util.LruCache;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class GlyphsLedDataProvider {

    public static ArrayList<GlyphsLedAnimPoint> readLedAnim(Context context, String animName) throws IOException {
        ArrayList<GlyphsLedAnimPoint> arrayList = new ArrayList<>();
        BufferedReader assetsReader = new BufferedReader(new InputStreamReader(context.getApplicationContext().getAssets().open(animName)));
        while (true) {
            String readLine = assetsReader.readLine();
            if (readLine == null) {
                break;
            }
            GlyphsLedAnimPoint glyphsLedAnimPoint = new GlyphsLedAnimPoint();
            StringTokenizer stringTokenizer = new StringTokenizer(readLine, ",");
            int i = 0;
            while (stringTokenizer.hasMoreTokens()) {
                double doubleValue = Double.parseDouble(stringTokenizer.nextToken());
                if (doubleValue == 0.0d) {
                    glyphsLedAnimPoint.setLedValue(i, 0);
                } else {
                    glyphsLedAnimPoint.setLedValue(i, (int) (((((doubleValue * 40.0d) / 4096.0d) + 60.0d) * 255.0d) / 100.0d));
                }
                i++;
            }
            arrayList.add(glyphsLedAnimPoint);
        }
        assetsReader.close();
        return arrayList;
    }
}
