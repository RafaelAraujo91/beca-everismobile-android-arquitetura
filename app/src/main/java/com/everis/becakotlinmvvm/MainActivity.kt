package com.everis.becakotlinmvvm

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.everis.becakotlinmvvm.databinding.ActivityMainBinding
import com.everis.becakotlinmvvm.presenter.HolidayViewModel
import com.everis.becakotlinmvvm.presenter.adapter.HolidayAdapter
import com.everis.becakotlinmvvm.utils.Connected

class MainActivity : AppCompatActivity() {

    val TAG = javaClass.simpleName

    lateinit var holidayAdapter: HolidayAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()

        if (Connected.isConnected(this)) {
            val holidayViewModel = HolidayViewModel()
            initObs(holidayViewModel)

        }
    }

    private fun initUI() {
        binding.rvHolidayList.setHasFixedSize(true);
        val layoutManager = LinearLayoutManager(this)
        binding.rvHolidayList.layoutManager = layoutManager
        binding.rvHolidayList.itemAnimator = DefaultItemAnimator()

        holidayAdapter = HolidayAdapter()
        binding.rvHolidayList.adapter = holidayAdapter
    }

    private fun initObs(holidayViewModel: HolidayViewModel) {
        binding.progressBar.visibility = View.VISIBLE
        holidayViewModel.getHolidays().observe(this,
                { t ->
                    Log.e(TAG, "observe onChanged()=" + t?.size)
                    binding.progressBar.visibility = View.GONE
                    holidayAdapter.addData(t!!)
                    holidayAdapter.notifyDataSetChanged()
                })
    }
}