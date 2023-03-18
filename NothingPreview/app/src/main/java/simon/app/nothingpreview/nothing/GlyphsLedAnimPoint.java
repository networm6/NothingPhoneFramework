package simon.app.nothingpreview.nothing;


public class GlyphsLedAnimPoint {
    public int[] mLeds = new int[5];

    public void setLedValue(int i, int i2) {
        if (i > this.mLeds.length - 1) {
            new Throwable("position can't be bigge than mLeds.length");
        }
        this.mLeds[i] = i2;
    }

    public int getValue(int i) {
        return this.mLeds[i];
    }
}
