package com.example.fuzzydefuzzy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fuzzydefuzzy.databinding.ActivityHasilBinding

class HasilActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHasilBinding
    private var hasil :String? = ""
    private var angka :Double? = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHasilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hasil()
        back()
    }
    private fun back(){
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
    private fun hasil() {
        hasil = intent.getStringExtra("result")
        angka = intent.getDoubleExtra("angka",0.0)
        var fuza = intent.getStringArrayExtra("a")
        var fuzb = intent.extras?.getSerializable("b") as? Array<*>?
        var fuzzistring = intent.getStringArrayExtra("fuzzystring")
        var fuzziangka = intent.extras?.getSerializable("fuzzyangka") as? Array<*>?
        var inferenceString = intent.getStringArrayExtra("inferencestring")
        var inferenceAngka = intent.extras?.getSerializable("inferenceangka") as? Array<*>?
        var defuzzistring = intent.getStringArrayExtra("defuzziString")
        var defuzziangka = intent.extras?.getSerializable("defuzziangka") as? Array<*>?
        binding.hasil.text = hasil
        binding.angka.text = angka.toString()
        var resultakhir = """
            
            Fuzzyfication :
            ${fuza?.toList()}
            ${fuzb?.toList()}
            ${fuzzistring?.toList()}
            ${fuzziangka?.toList()}
            
            
            Inference :
            ${inferenceString?.toList()}
            ${inferenceAngka?.toList()}
            
            Defuzzi : 
            ${defuzzistring?.toList()}
            ${defuzziangka?.toList()}
            
        """.trimIndent()
        binding.detail.text  = resultakhir
        if (hasil == "Sedikit"){
            binding.penjelasan.text = "Kita Harus Membuat Roti dengan Jumlah yang Sedikit"
            binding.picture.setImageResource(R.drawable.sedikit)
            Toast.makeText(this, "hasil Sedikit", Toast.LENGTH_SHORT).show()
        }else if (hasil == "Sedang"){
            binding.penjelasan.text = "Kita Harus Membuat Roti dengan Jumlah yang Sedang"
            binding.picture.setImageResource(R.drawable.sedang)
            Toast.makeText(this, "hasil Sedang", Toast.LENGTH_SHORT).show()
        } else if (hasil == "Banyak"){
            binding.penjelasan.text = "Kita Harus Membuat Roti dengan Jumlah yang Banyak"
            binding.picture.setImageResource(R.drawable.banyak)
            Toast.makeText(this, "hasil Banyak", Toast.LENGTH_SHORT).show()
        } else{
            binding.penjelasan.text = "Ada Yang Tidak Beres Nich.."
            binding.picture.setImageResource(R.drawable.gagal)
            Toast.makeText(this, "Mohon Masukan Inputan yang Benar", Toast.LENGTH_SHORT).show()
        }
    }
}


