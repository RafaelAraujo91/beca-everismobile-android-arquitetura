package com.everis.becakotlinmvvm.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class IsConnected() {
    lateinit var connectivityManager: ConnectivityManager

    fun isNetworkAvailable(context: Context): Boolean {
        connectivityManager = (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?)!!;
        var activeNetworkInfo: NetworkInfo? = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.activeNetworkInfo;
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected;
    }
}