package com.ppm.jetpack.livedata;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import com.ppm.jetpack.R;

public class LiveDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);

        MutableLiveData<String> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
            }
        });

        LiveData transformedLiveData = Transformations.map(mutableLiveData, new Function<String, Object>() {
            @Override
            public Object apply(String input) {
                return input + "liveData";
            }
        });

        transformedLiveData.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {

            }
        });
        mutableLiveData.postValue("this is LiveDta Test");

    }

}
