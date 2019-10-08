package com.ppm.jetpack.livedata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private MutableLiveData<String> name;

    public LiveData<String> getName() {
        if (name == null) {
            name = new MutableLiveData<>();
            addName();
        }

        return name;
    }

    private void addName() {
        name.setValue("Android View Model");
    }
}
