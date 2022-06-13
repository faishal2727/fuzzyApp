package com.example.fuzzydefuzzy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fuzzydefuzzy.databinding.ActivityPersediaanBinding

class PersediaanActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPersediaanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersediaanBinding.inflate(layoutInflater)
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