package com.example.fuzzydefuzzy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fuzzydefuzzy.databinding.ActivityFuzziBinding

class FuzziActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFuzziBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFuzziBinding.inflate(layoutInflater)
        setContentView(binding.root)
        back()
    }
    private fun back(){
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}