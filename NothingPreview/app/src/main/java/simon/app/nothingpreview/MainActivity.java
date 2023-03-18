package simon.app.nothingpreview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.CompoundButton;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

import simon.app.nothingpreview.databinding.ActivityMainBinding;
import simon.app.nothingpreview.nothing.LedCenter;

public class MainActivity extends AppCompatActivity {
    protected ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.play.setOnClickListener(v -> {
            String name = binding.csv.getText().toString();
            try {
                binding.glyphsView.loadAnim(name);
                binding.glyphsView.start();
            } catch (IOException e) {
                Snackbar.make(v, "输入无效 " + e, Snackbar.LENGTH_LONG).show();
            }
        });
        binding.cancel.setOnClickListener(v -> binding.glyphsView.cancel());

//        binding.glyphOpen.setOnCheckedChangeListener((buttonView, isChecked) -> LedCenter.setEnable(isChecked ? 1 : 0));
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.glyphOpen.setChecked(LedCenter.getEnable());
    }
}