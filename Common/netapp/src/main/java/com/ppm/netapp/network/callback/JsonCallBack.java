package com.ppm.netapp.network.callback;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ppm.netapp.model.Data;
import com.ppm.ppcomon.utils.StringUtil;

import java.lang.reflect.ParameterizedType;

public abstract class JsonCallBack<T extends Data> implements ReqCallback {

    public abstract void success(T data);

    public abstract void fail(int code, String err);

    @Override
    public void success(String response) {
        if (StringUtil.isEmpty(response)) {
            fail(ErrCode.CODE_DATA_ILLEGAL, "");
            return;
        }
        try {
            JSONObject object = JSON.parseObject(response);
            if (object == null) {
                fail(ErrCode.CODE_DATA_ILLEGAL, "");
                return;
            }

            Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            if (tClass == null) {
                throw new RuntimeException("T must be instantiation");
            }

            T jsData = JSON.toJavaObject(object, tClass);
            success(jsData);
        } catch (Exception e) {
            fail(ErrCode.CODE_DATA_ILLEGAL, "");
            return;
        }
    }

    @Override
    public void fail() {

    }
}

