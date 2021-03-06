package com.ppm.kotlindemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.ppm.kotlindemo.R
import com.ppm.ppcomon.net.HttpClient
import com.ppm.ppcomon.net.callback.Callback
import com.ppm.ppcomon.utils.LogUtils
import java.net.ResponseCache
import kotlin.collections.set

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = this.findViewById(R.id.view_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = HisAdapter()
        recyclerView.adapter = adapter

        val dataList: ArrayList<String> = ArrayList()
        dataList.add("1111")
        dataList.add("2222")
        dataList.add("333")
        adapter.mData = dataList
        requestHisList()
    }


    /**
     * 请求历史列表
     */
    private fun requestHisList() {
        val httpClient = HttpClient()
        val params: MutableMap<String, String> = getCommonParams();
        params["month"] = "1"
        params["day"] = "1"
        val mCallback: Callback = object : Callback {
            override fun onStart() {
            }

            override fun onSuccess(response: JSONObject?) {
                if (response != null) {
                    LogUtils.d(response.toJSONString())
                }
            }

            override fun onFail(errorCode: Int, errorMessage: String?) {
                LogUtils.d(errorMessage)
            }


            override fun onStop() {
            }
        }

        httpClient.sendPostRequest(
            "http://api.juheapi.com/japi/toh",
            params,
            mCallback
        )
    }

    private fun getCommonParams(): MutableMap<String, String> {
        val params: MutableMap<String, String> = mutableMapOf()
        params["key"] = "0dbb338d2f0bf8392d1ff773df23a555"
        params["v"] = "1.0"
        return params
    }

}
