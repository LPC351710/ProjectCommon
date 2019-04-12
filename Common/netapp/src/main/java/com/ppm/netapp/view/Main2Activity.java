package com.ppm.netapp.view;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.ppm.netapp.R;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageView imageView = findViewById(R.id.img);

        RoundedCorners roundedCorners = new RoundedCorners(100);
//        RequestOptions options = RequestOptions.centerCrop().transform();

        RequestOptions myOptions = new RequestOptions().centerCrop().transform(new RoundTransform(this, 30));
        Glide.with(this).load("https://ms.wrcdn.com/2019/4/12/PXps7oVCcr1Pg22d17YBN68hPSsB.jpeg").apply(myOptions).into(imageView);

    }
}
