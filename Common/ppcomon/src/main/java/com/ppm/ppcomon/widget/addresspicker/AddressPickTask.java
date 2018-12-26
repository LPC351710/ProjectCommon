package com.ppm.ppcomon.widget.addresspicker;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import com.alibaba.fastjson.JSON;
import com.ppm.ppcomon.R;
import com.ppm.ppcomon.widget.DefaultProgressDialog;
import com.ppm.ppcomon.widget.addresspicker.common.ConvertUtils;
import com.ppm.ppcomon.widget.addresspicker.entity.Province;
import com.ppm.ppcomon.widget.addresspicker.picker.AddressPicker;

import java.util.ArrayList;


/**
 * @author lpc on 2017/5/11
 */
public class AddressPickTask extends AsyncTask<String, Void, ArrayList<Province>> {
    private Activity activity;
    protected DefaultProgressDialog mProgressHUD;
    private Callback callback;
    private String selectedProvince = "", selectedCity = "", selectedCounty = "";
    private boolean hideProvince = false;
    private boolean hideCounty = false;

    private AddressPicker picker;

    public AddressPickTask(Activity activity) {
        this.activity = activity;
    }

    public void setHideProvince(boolean hideProvince) {
        this.hideProvince = hideProvince;
    }

    public void setHideCounty(boolean hideCounty) {
        this.hideCounty = hideCounty;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
//            mProgressHUD = ProgressHUD.show(activity, "加载数据中...", true, false, null);
    }

    @Override
    protected ArrayList<Province> doInBackground(String... params) {
        if (params != null) {
            switch (params.length) {
                case 1:
                    selectedProvince = params[0];
                    break;
                case 2:
                    selectedProvince = params[0];
                    selectedCity = params[1];
                    break;
                case 3:
                    selectedProvince = params[0];
                    selectedCity = params[1];
                    selectedCounty = params[2];
                    break;
                default:
                    break;
            }
        }
        ArrayList<Province> data = new ArrayList<>();
        try {
            String dataStr = ConvertUtils.toString(activity.getResources().openRawResource(R.raw.city));
            data.addAll(JSON.parseArray(dataStr, Province.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onPostExecute(ArrayList<Province> result) {
        if (mProgressHUD != null) {
            mProgressHUD.dismiss();
        }
        if (result.size() > 0) {
            picker = new AddressPicker(activity, result);
            picker.setHideProvince(hideProvince);
            picker.setHideCounty(hideCounty);
            picker.setTopBackgroundColor(Color.parseColor("#ffffff"));
            picker.setPressedTextColor(Color.parseColor("#999999"));
            picker.setTopLineColor(Color.parseColor("#eaeaea"));
            picker.setTopHeight(55);
            picker.setTopLineVisible(true);
            picker.setHalfWidthScreen(true);
            picker.setCancelVisible(false);
            picker.setTitleText("请选择区域");
            picker.setCancelTextColor(Color.parseColor("#999999"));
            picker.setSubmitTextColor(Color.parseColor("#FF0000"));
            picker.setTitleTextSize(15);
            picker.setSubmitTextSize(15);
            if (hideCounty) {
                picker.setColumnWeight(0.8f, 1.0f);
            } else if (hideProvince) {
                picker.setColumnWeight(1.0f, 0.8f);
            } else {
                picker.setColumnWeight(0.8f, 1.0f, 1.0f);
            }
            picker.setSelectedItem(selectedProvince, selectedCity, selectedCounty);
            picker.setOnAddressPickListener(callback);
//            picker.show();
        } else {
            callback.onAddressInitFailed();
        }
    }

    public void showPicker() {
        if (picker != null) {
            picker.show();
        } else {
            mProgressHUD = new DefaultProgressDialog(activity);
            mProgressHUD.showLoadingProgressHUD();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (picker != null) {
                        dismissProgress();
                        picker.show();
                    } else {
                        handler.postDelayed(this, 500);
                    }
                }
            }, 500);
        }
    }

    private void dismissProgress() {
        if (mProgressHUD != null) {
            mProgressHUD.dismiss();
        }
    }

    public interface Callback extends AddressPicker.OnAddressPickListener {

        void onAddressInitFailed();

    }

}
