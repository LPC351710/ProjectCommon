package com.ppm.jetpack;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ppm.jetpack.livedata.MainViewModel;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        MainViewModel model = ViewModelProviders.of(this).get(MainViewModel.class);
        model.getName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("onChanged", "s = " + s);
            }
        });
    }
}
