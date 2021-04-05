package com.everis.becakotlinmvvm

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HolidayRepository {

    val TAG: String = javaClass.simpleName

    fun fetchHolidays(): MutableLiveData<List<HolidayModel>> {
        var mutableList: MutableLiveData<List<HolidayModel>> = MutableLiveData()

        val apiInterface = RetrofitClient.getRetrofitInstance(Constants().BASE_URL)
            .create(ApiInterface::class.java)

        apiInterface.getHolidays().enqueue(object : Callback<List<HolidayModel>> {
            override fun onResponse(
                call: Call<List<HolidayModel>>,
                response: Response<List<HolidayModel>>
            ) {
                Log.e(TAG, "onResponse response=" + response.toString())

                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse response.size=" + response.body()?.size)

                    if (response.body() != null && response.body()?.size!! > 0) {
                        mutableList.value = response.body()!!
                    }
                }
            }

            override fun onFailure(call: Call<List<HolidayModel>>, t: Throwable) {
                Log.e(TAG, "onFailure call=" + call.toString())
            }

        })

        return mutableList
    }

    fun isConnectedToNetwork(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false
    }

}
