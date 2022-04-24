package com.srikanth.locus.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.srikanth.locus.adapter.WeatherAdapter
import com.srikanth.locus.databinding.ActivityMainBinding
import com.srikanth.locus.viewmodel.WeatherModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val API_KEY ="65d00499677e59496ca2f318eb68c049"
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerAdapter: WeatherAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initViewModel()
    }


    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = WeatherAdapter()
        binding.recyclerView.adapter = recyclerAdapter
    }

    private fun initViewModel() {
        val viewModel: WeatherModel by viewModels()
        viewModel.getLiveData().observe(this,
            {
                d("cod",it.cod.toString())
                if (it.cod == 200) {
                    recyclerAdapter.setList(it.weather)
                    d("res", it.weather.toString())
                } else {
                  //  Snackbar.make(binding.root, it.cod, Snackbar.LENGTH_LONG).show()
                    Toast.makeText(this,"not found",Toast.LENGTH_LONG).show()
                }

            })
        //viewModel.getApi("Bangalore,", "65d00499677e59496ca2f318eb68c049")
        d("int",intent.getStringExtra("city").toString())
        viewModel.getApi(intent.getStringExtra("city").toString(),API_KEY)
    }
}