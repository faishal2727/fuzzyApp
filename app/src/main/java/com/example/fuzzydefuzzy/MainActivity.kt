package com.example.fuzzydefuzzy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.fuzzydefuzzy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btnCek()
        permintaan()
        persediaan()
        produksi()
        fuzzy()
    }
    fun btnCek(){
        binding.btnCek.setOnClickListener {
            startActivity(Intent(this, CekActivity::class.java))
            finish()
        }
    }
    fun permintaan(){
        binding.cardPermintaan.setOnClickListener {
            startActivity(Intent(this,PermintaanActivity::class.java))
            finish()
        }
    }
    fun persediaan(){
        binding.cardPersediaan.setOnClickListener {
            startActivity(Intent(this,PersediaanActivity::class.java))
            finish()
        }
    }
    fun produksi(){
        binding.cardProduksi.setOnClickListener {
            startActivity(Intent(this,ProduksiActivity::class.java))
            finish()
        }
    }
    fun fuzzy(){
        binding.cardFuzzy.setOnClickListener {
            startActivity(Intent(this,FuzziActivity::class.java))
            finish()
        }
    }
}