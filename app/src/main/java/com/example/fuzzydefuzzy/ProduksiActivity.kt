package com.example.fuzzydefuzzy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fuzzydefuzzy.databinding.ActivityProduksiBinding

class ProduksiActivity : AppCompatActivity() {
    private  lateinit var binding : ActivityProduksiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProduksiBinding.inflate(layoutInflater)
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