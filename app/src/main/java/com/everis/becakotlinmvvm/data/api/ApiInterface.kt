package com.everis.becakotlinmvvm.data.api

import com.everis.becakotlinmvvm.data.model.HolidayModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("PublicHolidays/2021/br")
    fun getHolidays(): Call<List<HolidayModel>>
}