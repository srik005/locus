package com.srikanth.locus.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.srikanth.locus.activity.DetailActivity
import com.srikanth.locus.databinding.ActivityDetailBinding
import com.srikanth.locus.model.WeatherList


class WeatherAdapter() : RecyclerView.Adapter<WeatherAdapter.WeatherHolder>() {
    private var dataList: List<WeatherList>? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherHolder {
        val mView =
            ActivityDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return WeatherHolder(mView)
    }

    fun setList(listData: List<WeatherList>) {
        this.dataList = listData
    }

    override fun onBindViewHolder(holder: WeatherHolder, @SuppressLint("RecyclerView") position:Int) {
        holder.bind(dataList!!.get(position))
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
              val intent = Intent(v?.context, DetailActivity::class.java)
               intent.putExtra("description",dataList!!.get(position).description)
                     intent.putExtra("main",dataList!!.get(position).main)
                v?.context?.startActivity(intent)
            }

        })
    }

    override fun getItemCount(): Int {

        //d("listdata", dataList!!.size.toString())
        if (dataList == null) {
            return 0
        } else {
            return dataList?.size!!
        }
    }

    class WeatherHolder(private val binding: ActivityDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(weatherData: WeatherList) {
            binding.tvWeather.text = weatherData.description
            binding.tvTemperature.text = weatherData.main
        }
    }
}