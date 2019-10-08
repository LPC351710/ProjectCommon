package com.ppm.jetpack.livedata;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import com.ppm.jetpack.R;

public class Main2Activity extends AppCompatActivity {

    MutableLiveData<String> mutableLiveData1;

    MutableLiveData<String> mutableLiveData2;

    MutableLiveData<Boolean> liveDataSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mutableLiveData1 = new MutableLiveData<>();
        mutableLiveData2 = new MutableLiveData<>();
        liveDataSwitch = new MutableLiveData<Boolean>();

        LiveData transformedLiveData = Transformations.switchMap(liveDataSwitch, new Function<Boolean, LiveData<String>>() {
            @Override
            public LiveData<String> apply(Boolean input) {
                return input ? mutableLiveData1 : mutableLiveData2;
            }
        });

        transformedLiveData.observe(this, new Observer<String>() {

            @Override
            public void onChanged(@NonNull final String s) {
            }
        });

        liveDataSwitch.postValue(false);
        mutableLiveData1.postValue("mutableLiveData1");
        mutableLiveData2.postValue("mutableLiveData2");
    }

    private void merge() {
        MutableLiveData<String> mutableLiveData1 = new MutableLiveData<>();
        MutableLiveData<String> mutableLiveData2 = new MutableLiveData<>();
        MediatorLiveData liveDataMerger = new MediatorLiveData<String>();
        liveDataMerger.addSource(mutableLiveData1, new Observer() {
            @Override
            public void onChanged(Object o) {

            }
        });

        liveDataMerger.addSource(mutableLiveData2, new Observer() {
            @Override
            public void onChanged(Object o) {

            }
        });

        liveDataMerger.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
            }
        });

        mutableLiveData2.postValue("");
    }
}
