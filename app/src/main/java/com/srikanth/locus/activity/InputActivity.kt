package com.srikanth.locus.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.srikanth.locus.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            if (binding.et.text.isNotEmpty()) {
                val btnIntent = Intent(this, MainActivity::class.java)
                btnIntent.putExtra("city",binding.et.text.toString())
                startActivity(btnIntent)
            } else {
                Snackbar.make(binding.root,"Enter city name",Snackbar.LENGTH_LONG).show()
            }
        }
    }
}