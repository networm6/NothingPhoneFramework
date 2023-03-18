package simon.app.nothingpreview.nothing.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import simon.app.nothingpreview.R;
import simon.app.nothingpreview.nothing.GlyphsLedAnimPoint;
import simon.app.nothingpreview.nothing.GlyphsLedDataProvider;
import simon.app.nothingpreview.nothing.LedAnimUtils;

public class GlyphsView extends FrameLayout implements LedAnimUtils.OnAnimCallback {
    private ImageView imgLed1;
    private ImageView imgLed2;
    private ImageView imgLed3;
    private ImageView imgLed4;
    private ImageView imgLed5;

    private LedAnimUtils animUtils;
    private ArrayList<GlyphsLedAnimPoint> animPoints;

    public GlyphsView(Context context) {
        this(context, null, 0, 0);
    }

    public GlyphsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0);
    }

    public GlyphsView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public GlyphsView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet, i, i2);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        LayoutInflater.from(context).inflate(R.layout.view_led_pager, this);
        this.imgLed1 = (ImageView) findViewById(R.id.img_led1);
        this.imgLed2 = (ImageView) findViewById(R.id.img_led2);
        this.imgLed3 = (ImageView) findViewById(R.id.img_led3);
        this.imgLed4 = (ImageView) findViewById(R.id.img_led4);
        this.imgLed5 = (ImageView) findViewById(R.id.img_led5);
        animUtils = new LedAnimUtils(this);
    }

    public GlyphsView loadAnim(String name) throws IOException {
        animPoints = GlyphsLedDataProvider.readLedAnim(getContext(), name);
        animUtils.cancel();
        animUtils.setFrameSize(animPoints.size());
        return this;
    }

    public void start() {
        animUtils.startAnim();
    }

    public void cancel() {
        animUtils.cancel();
    }

    @Override
    public void onAnimStart() {
        this.imgLed1.setImageResource(R.drawable.ic_default_led1);
        this.imgLed2.setImageResource(R.drawable.ic_default_led2);
        this.imgLed3.setImageResource(R.drawable.ic_default_led3);
        this.imgLed4.setImageResource(R.drawable.ic_default_led4);
        this.imgLed5.setImageResource(R.drawable.ic_default_led5);
    }

    @Override
    public void onAnimRefresh(int i) {
        setLedDrawable(this.imgLed1, R.drawable.ic_glyphs_led1, R.drawable.ic_default_led1, this.animPoints.get(i).getValue(0));
        setLedDrawable(this.imgLed2, R.drawable.ic_glyphs_led2, R.drawable.ic_default_led2, this.animPoints.get(i).getValue(1));
        setLedDrawable(this.imgLed3, R.drawable.ic_glyphs_led3, R.drawable.ic_default_led3, this.animPoints.get(i).getValue(2));
        setLedDrawable(this.imgLed4, R.drawable.ic_glyphs_led4, R.drawable.ic_default_led4, this.animPoints.get(i).getValue(3));
        setLedDrawable(this.imgLed5, R.drawable.ic_glyphs_led5, R.drawable.ic_default_led5, this.animPoints.get(i).getValue(4));
    }

    @Override
    public void onAnimFinish() {
        this.imgLed1.setImageResource(R.drawable.ic_default_led1);
        this.imgLed2.setImageResource(R.drawable.ic_default_led2);
        this.imgLed3.setImageResource(R.drawable.ic_default_led3);
        this.imgLed4.setImageResource(R.drawable.ic_default_led4);
        this.imgLed5.setImageResource(R.drawable.ic_default_led5);
    }

    private void setLedDrawable(ImageView imageView, int i, int i2, int i3) {
        if (i3 <= 0) {
            imageView.setImageResource(i2);
            return;
        }
        imageView.setImageResource(i);
        imageView.getDrawable().setAlpha(i3);
    }


}