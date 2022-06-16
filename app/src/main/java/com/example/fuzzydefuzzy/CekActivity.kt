package com.example.fuzzydefuzzy

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.fuzzydefuzzy.databinding.ActivityCekBinding
import kotlinx.android.synthetic.main.alert.view.*

class CekActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCekBinding
    private var permintaan = 0.0
    private var persediaan = 0.0

    //Membuat function untuk rumus pertama
    fun rumusPertama(x: Double, c: Double, d: Double): Double {
        val hitung = (-(x - d)) / (d - c)
        return hitung
    }
    //Membuat function untuk rumus kedua
    fun rumusKedua(x: Double, a: Double, b: Double): Double {
        val hitung = (x - a) / (b - a)
        return hitung
    }

    //variabel untuk proses fuzzification
    //variabel permintaan
    var permintaanString: Array<String> = arrayOf()
    var permintaanAngka: Array<Double> = arrayOf()
    //variabel persediaan
    var persediaanString: Array<String> = arrayOf()
    var persediaanAngka: Array<Double> = arrayOf()

    //variabel untuk proses infrence
    var angkaInference: Array<Double> = arrayOf()
    var stringInference: Array<String> = arrayOf()
    var key: Int = 0
    var success: Boolean = false

    //variabel untuk proses defuzzi
    val rangeRendah = Array<Int>(5) { it * 25 +2000}
    val rangeSedang = Array<Int>(5) { it * 25+ 2200 }
    val rangeTinggi = Array<Int>(5) { it * 25 + 2400 }
    var final_result = 0.0
    var string_result = ""
    var angkaDisjuction: Array<Double> = arrayOf()
    var stringDisjuction: Array<String> = arrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCekBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dialog = Dialog(this)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        back()
    }
    override fun onResume() {
        super.onResume()
        hitung()
    }
    private fun back(){
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
    //function untuk proses fuzzification
    private fun fuzzification(){
        when {
            permintaan < 1000.0 -> permintaanAngka += 0.0
            permintaan in 1000.0..1100.0 -> {
                permintaanString += "Kecil"
                permintaanAngka += 1.0
            }
            permintaan in 1101.0..1149.0 -> {
                val a = 1100.0
                val b = 1150.0
                permintaanString += "Kecil"
                permintaanString += "Sedang"
                permintaanAngka += rumusPertama(permintaan, a, b)
                permintaanAngka += rumusKedua(permintaan, a, b)
            }
            permintaan in 1150.0..1250.0 -> {
                permintaanString += "Sedang"
                permintaanAngka += 1.0
            }
            permintaan in 1251.0..1299.0 -> {
                val a = 1250.0
                val b = 1300.0
                permintaanString += "Sedang"
                permintaanString += "Besar"
                permintaanAngka += rumusPertama(permintaan, a, b)
                permintaanAngka += rumusKedua(permintaan, a, b)
            }
            permintaan in 1300.0..1600.0 -> {
                permintaanString += "Besar"
                permintaanAngka += 1.0
            }
            permintaan > 1600.0 -> permintaanAngka += 0.0
        }
        when {
            persediaan < 600.0 -> persediaanAngka += 0.0
            persediaan in 600.0..680.0 -> {
                persediaanString += "Sedikit"
                persediaanAngka += 1.0
            }
            persediaan in 681.0..699.0 -> {
                val a = 680.0
                val b = 700.0
                persediaanString += "Sedikit"
                persediaanString += "Sedang"
                persediaanAngka += rumusPertama(persediaan, a, b)
                persediaanAngka += rumusKedua(persediaan, a, b)
            }
            persediaan in 700.0..780.0 -> {
                persediaanString += "Sedang"
                persediaanAngka += 1.0
            }
            persediaan in 781.0..799.0 -> {
                val a = 780.0
                val b = 800.0
                persediaanString += "Sedang"
                persediaanString += "Banyak"
                persediaanAngka += rumusPertama(persediaan, a, b)
                persediaanAngka += rumusKedua(persediaan, a, b)
            }
            persediaan in 800.0..900.0 -> {
                persediaanString += "Banyak"
                persediaanAngka += 1.0
            }
            persediaan > 1500.0 -> persediaanAngka += 0.0
        }
    }
    //function untuk proses inference
    private fun inference(){
        if (permintaan < 1000.0 || persediaan < 600.0) {
        println("Permintaan min 1000 dan Persediaan min 600")
    } else {
        if (permintaan > 1600.0 || persediaan > 900.0) {
            println("Permintaan max 1600 dan Persediaan max 900")
        } else {
            //proses conjuction
            if (permintaanString.isNotEmpty() && permintaanString.isNotEmpty()) {
                for (i in permintaanAngka) {
                    for (j in persediaanAngka) {
                        if (i <= j) {
                            angkaInference += i
                        } else {
                            angkaInference += j
                        }
                    }
                }
                success = true
            } else {
                println("Data Tidak Ada  ygy 3")
            }
        }
    }
        //proses conjuction
        for (i in permintaanString) {
            for (j in persediaanString) {
                if (i == "Kecil" && j == "Sedikit") {
                    stringInference += "Sedikit"
                } else if (i == "Kecil" && j == "Sedang") {
                    stringInference += "Sedikit"
                } else if (i == "Kecil" && j == "Banyak") {
                    stringInference += "Sedikit"
                } else if (i == "Sedang" && j == "Sedikit") {
                    stringInference += "Sedikit"
                } else if (i == "Sedang" && j == "Sedang") {
                    stringInference += "Sedang"
                } else if (i == "Sedang" && j == "Banyak") {
                    stringInference += "Sedang"
                } else if (i == "Besar" && j == "Sedikit") {
                    stringInference += "Banyak"
                } else if (i == "Besar" && j == "Sedang") {
                    stringInference += "Banyak"
                } else if (i == "Besar" && j == "Banyak") {
                    stringInference += "Banyak"
                } else {
                    stringInference += "Tidak Ada Dalam Rules"
                }
            }
        }

        //lebih >2
        var cek = 0
        if (stringInference.size > 2) {
            for (i in stringInference.indices) {
                for (j in 1..stringInference.size - 1) {
                    // Pengecekan apakah ada nama yang sama pada suatu array
                    if ( stringInference[0] == stringInference[j] || stringInference[i] == stringInference[j]) {
                        key = 1
                    }
                }
            }
        } else if (stringInference.size == 2) {
            if (stringInference[0] == stringInference[1]) {
                cek = 1
            }
        } else {
            println("Lempar Defuzzi karena key = 0")
        }

        // Jika ada nama yang sama menjalankan disjunction
        if (key == 1) {
            disjunction(stringInference)
        } else if (cek == 1) {
            stringDisjuction += stringInference[0]
            if (angkaInference[0] > angkaInference[1]) {
                angkaDisjuction += angkaInference[0]
            } else {
                angkaDisjuction += angkaInference[1]
            }
        } else {
            for (i in angkaInference) {
                angkaDisjuction += i
            }
            for (i in stringInference) {
                stringDisjuction += i
            }
        }
        println(stringDisjuction.toList())
        println(angkaDisjuction.toList())
        println()
        }

        //proses disjuction
        fun disjunction(arr: Array<String>) {
        var tempo: Array<Int> = arrayOf()
        var tempo2: Array<Int> = arrayOf()
        var tempo3: Array<Double> = arrayOf()
        var tempo4: Array<Int> = arrayOf()
        var tempo5: Array<Int> = arrayOf()

        // s s s se
        // Mengecek nama yang sama pada array, jika ada dikelompokkan dan masukkan index tsb ke dalam array baru
        for (i in stringInference.indices) {
            if (stringInference[0] == stringInference[i]) {
                tempo += i
            } else {
                tempo2 += i
            }
        }
        // s s se b
        for (j in tempo2) {
            val a = tempo2[0]
            if (stringInference[a] == stringInference[j]) {
                tempo4 += j
            } else {
                tempo5 += j
            }
        }
        println(tempo.toList())
        println(tempo2.toList())

        var result: Array<Double> = arrayOf()
        var result2: Array<Double> = arrayOf()

        // Pengecekan Index, ambil index dgn nilai terbesar masukkan ke dalam variabel tempo3
        // jika tempo 5 no kosong
        if (tempo5.isNotEmpty()) {
            println(tempo.toList())
            println(tempo4.toList())
            println(tempo5.toList())
            // Clear
            if (tempo.size == 2) {
                // Ambil Nilai string dari index terbesar
                if (tempo[0] > tempo[1]) {
                    val a = tempo[0]
                    stringInference += stringInference[a]
                } else {
                    val a = tempo[1]
                    stringDisjuction += stringDisjuction[a]
                }
                // Ambil nilai integer dari index terbesar
                for (i in tempo4) {
                    result += angkaInference[i]
                }
                tempo3 += result.toList().sortedDescending().first()

                for (i in tempo4) {
                    tempo3 += angkaInference[i]
                    stringDisjuction += stringInference[i]
                }
                for (i in tempo5) {
                    tempo3 += angkaInference[i]
                    stringDisjuction += stringInference[i]
                }


            } else if (tempo4.size == 2) {
                if (tempo4[0] > tempo4[1]) {
                    val a = tempo4[0]
                    stringDisjuction += stringInference[a]
                } else {
                    val a = tempo4[1]
                    stringDisjuction += stringInference[a]
                }
                for (i in tempo4) {
                    result += angkaInference[i]
                }
                tempo3 += result.toList().sortedDescending().first()
                for (i in tempo) {
                    tempo3 += angkaInference[i]
                    stringDisjuction += stringInference[i]
                }
                for (i in tempo5) {
                    tempo3 += angkaInference[i]
                    stringDisjuction += stringInference[i]
                }

            } else if (tempo5.size == 2) {
                if (tempo5[0] > tempo5[1]) {
                    val a = tempo5[0]
                    stringDisjuction += stringInference[a]
                } else {
                    val a = tempo5[1]
                    stringDisjuction += stringInference[a]
                }
                for (i in tempo5) {
                    result += angkaInference[i]
                }
                tempo3 += result.toList().sortedDescending().first()
                for (i in tempo) {
                    tempo3 += angkaInference[i]
                    stringDisjuction += stringInference[i]
                }
                for (i in tempo4) {
                    tempo3 += angkaInference[i]
                    stringDisjuction += stringInference[i]
                }

            } else {
                println("error")
            }
        } else if (tempo.size == 2 && tempo2.size == 2) {
            for (i in tempo) {
                result += angkaInference[i]
            }
            tempo3 += result.toList().sortedDescending().first()
            result = arrayOf()

            if (tempo[0] > tempo[1]) {
                val a = tempo[0]
                stringDisjuction += stringInference[a]
            } else {
                val a = tempo[0]
                stringDisjuction += stringInference[a]
            }

            if (tempo2[0] > tempo2[1]) {
                val a = tempo2[0]
                stringDisjuction += stringInference[a]
            } else {
                val a = tempo2[0]
                stringDisjuction += stringInference[a]
            }

            for (i in tempo2) {
                result += angkaInference[i]
            }

            result2 += result.toList().sortedDescending().first()

            for (i in result2) {
                tempo3 += i
            }
        // s s s se
        } else if (tempo.size == 3 && tempo2.size == 1) {
            for (i in tempo) {
                result += angkaInference[i]
            }
            tempo3 += result.toList().sortedDescending().first()
            // Ambil nilai string dari tempo3
            for (i in tempo3) {
                stringDisjuction += stringInference[i.toInt()]
            }

            for (i in tempo2) {
                stringDisjuction += stringInference[i]
                tempo3 += angkaInference[i]

            }

        } else if (tempo.size == 1 && tempo2.size == 3) {
            for (i in tempo2) {
                result += angkaInference[i]
            }
            tempo3 += result.toList().sortedDescending().first()
            // Ambil nilai string dari tempo3
            for (i in tempo3) {
                stringDisjuction += stringInference[i.toInt()]
            }
            for (i in tempo) {
                stringDisjuction += stringInference[i]
                tempo3 += angkaInference[i]
            }
        } else {
            println("Ups Ada data yang tidak terduga")
        }
        angkaDisjuction += tempo3

        println(angkaDisjuction.toList())
        println(stringDisjuction.toList())
    }

    //funtion untuk proses defuzzification
    fun defuzzyfication(){
        if(stringDisjuction.isNotEmpty()){
            if(stringDisjuction.size == 3){
                if(stringDisjuction[0] == "Sedikit" && stringDisjuction[1] == "Sedang" && stringDisjuction[2] == "Banyak"){
                    final_result = ( (rangeRendah.sum() * angkaDisjuction[0] ) + (rangeSedang.sum() * angkaDisjuction[1])+ (rangeTinggi.sum() *angkaDisjuction[2]) ) / (( angkaDisjuction[0] * 5 ) + (angkaDisjuction[1] *5) + ( angkaDisjuction[2] * 5 ) + ( angkaDisjuction[3] * 5 ))
                }else if(stringDisjuction[0] == "Sedikit" && stringDisjuction[1] == "Banyak" && stringDisjuction[2] == "Sedang"){
                    final_result = ( (rangeRendah.sum() * angkaDisjuction[0] ) + (rangeTinggi.sum() * angkaDisjuction[1])+ (rangeSedang.sum() *angkaDisjuction[2]) ) / (( angkaDisjuction[0] * 5 ) + (angkaDisjuction[1] *5) + ( angkaDisjuction[2] * 5 ) + ( angkaDisjuction[3] * 5 ))
                }else if(stringDisjuction[0] == "Sedang" && stringDisjuction[1] == "Sedikit" && stringDisjuction[2] == "Banyak"){
                    final_result = ( (rangeTinggi.sum() * angkaDisjuction[0] ) + (rangeSedang.sum() * angkaDisjuction[1])+ (rangeRendah.sum() * angkaDisjuction[2]) ) / ((angkaDisjuction[0] * 5 ) + (angkaDisjuction[1] *5) + ( angkaDisjuction[2] * 5 ) + (angkaDisjuction[3] * 5 ))
                }else if(stringDisjuction[0] == "Sedang" && stringDisjuction[1] == "Banyak" && stringDisjuction[2] == "Sedikit"){
                    final_result = ( (rangeSedang.sum() * angkaDisjuction[0] ) + (rangeRendah.sum() * angkaDisjuction[1])+ (rangeTinggi.sum() * angkaDisjuction[2]) ) / (( angkaDisjuction[0] * 5 ) + (angkaDisjuction[1] *5) + ( angkaDisjuction[2] * 5 ) + ( angkaDisjuction[3] * 5 ))
                }else if(stringDisjuction[0] == "Banyak" && stringDisjuction[1] == "Sedikit" && stringDisjuction[2] == "Sedang"){
                    final_result = ( (rangeSedang.sum() * angkaDisjuction[0] ) + (rangeRendah.sum() * angkaDisjuction[1])+ (rangeTinggi.sum() * angkaDisjuction[2]) ) / ((angkaDisjuction[0] * 5 ) + (angkaDisjuction[1] *5) + ( angkaDisjuction[2] * 5 ) + ( angkaDisjuction[3] * 5 ))
                }else if(stringDisjuction[0] == "Banyak" && stringDisjuction[1] == "Sedang" && stringDisjuction[2] == "Sedikit"){
                    final_result = ( (rangeTinggi.sum() * angkaDisjuction[0] ) + (rangeRendah.sum() * angkaDisjuction[1])+ (rangeSedang.sum() * angkaDisjuction[2]) ) / (( angkaDisjuction[0] * 5 ) + (angkaDisjuction[1] *5) + ( angkaDisjuction[2] * 5 ) + ( angkaDisjuction[3] * 5 ))
                }else{
                    println("Terjadi Kesalahan")
                }
            //--
            }else if(stringDisjuction.size == 2){
                if(stringDisjuction[0] == "Sedikit" && stringDisjuction[1] == "Sedang"){
                    final_result =  ( (rangeRendah.sum() * angkaDisjuction[0] ) + (rangeSedang.sum() * angkaDisjuction[1])) / (( angkaDisjuction[0] * 5 ) + (angkaDisjuction[1] *5))
                }else if(stringDisjuction[0] == "Sedikit" && stringDisjuction[1] == "Banyak"){
                    final_result =  ( (rangeRendah.sum() * angkaDisjuction[0] ) + (rangeTinggi.sum() * angkaDisjuction[1])) / (( angkaDisjuction[0] * 5 ) + (angkaDisjuction[1] *5))
                }else if(stringDisjuction[0] == "Sedang" && stringDisjuction[1] == "Sedikit"){
                    final_result =  ( (rangeSedang.sum() * angkaDisjuction[0] ) + (rangeRendah.sum() * angkaDisjuction[1])) / (( angkaDisjuction[0] * 5 ) + (angkaDisjuction[1] *5))
                }else if(stringDisjuction[0] == "Sedang" && stringDisjuction[1] == "Banyak"){
                    final_result =  ( (rangeSedang.sum() * angkaDisjuction[0] ) + (rangeTinggi.sum() * angkaDisjuction[1])) / (( angkaDisjuction[0] * 5 ) + (angkaDisjuction[1] *5))
                }else if(stringDisjuction[0] == "Banyak" && stringDisjuction[1] == "Sedikit"){
                    final_result =  ( (rangeTinggi.sum() * angkaDisjuction[0] ) + (rangeRendah.sum() * angkaDisjuction[1])) / (( angkaDisjuction[0] * 5 ) + (angkaDisjuction[1] *5))
                }else if(stringDisjuction[0] == "Banyak" && stringDisjuction[1] == "Sedang"){
                    final_result =  ( (rangeTinggi.sum() * angkaDisjuction[0] ) + (rangeSedang.sum() * angkaDisjuction[1])) / (( angkaDisjuction[0] * 5 ) + (angkaDisjuction[1] *5))
                }else{
                    println("Terjadi Kesalahan")
                }

            }else{
                if(stringDisjuction[0] == "Sedikit"){
                    final_result =   (rangeRendah.sum() * angkaDisjuction[0] ) / ( angkaDisjuction[0] * 5 )
                }else if(stringDisjuction[0] == "Sedang"){
                    final_result =   (rangeSedang.sum() * angkaDisjuction[0] ) / (angkaDisjuction[0] * 5 )
                }else if(stringDisjuction[0] == "Banyak"){
                    final_result =   (rangeTinggi.sum() * angkaDisjuction[0] ) / ( angkaDisjuction[0] * 5 )
                }else{
                    println("Terjadi Kesalahan")
                }
            }
        }

        if(success){
            if(final_result.toInt() in 2000 ..2200){
                string_result = "Sedikit"
            }else if(final_result.toInt() in 2200..2400){
                string_result = "Sedang"
            }else if(final_result.toInt() in 2400..2600){
                string_result = "Banyak"
            }else{
                string_result = "Data diluar jangkauan"
            }
        }else{
            if(permintaan > 1600.0 && persediaan > 900.0){
                string_result = "Pastikan Nilai Permintaan 1000 - 1600 dan Persediaan 600 - 900"
            }else {
                string_result = "Pastikan Nilai Permintaan 1000 - 1600 dan Persediaan 600 - 900"
            }

        }
        println(final_result.toInt())
        println(string_result)
    }

    private fun hitung() {
        binding.btnCek.setOnClickListener {
            if (binding.permintaan.text.isNullOrEmpty() || binding.persediaan.text.isNullOrEmpty()) {
                Toast.makeText(this, "Jangan Kosong", Toast.LENGTH_SHORT).show()

            }
            else {
                val a = binding.permintaan.text.toString().toDouble()
                val b = binding.persediaan.text.toString().toDouble()
                if(a <1000.0 || a > 1600.0 || b < 600.0 || b >900.0) {
                    val mDialogView = LayoutInflater.from(this).inflate(R.layout.alert, null)
                    val builder = AlertDialog.Builder(this)
                            .setView(mDialogView)
                    val mAlertDialog = builder.show()
//                    mAlertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

                    mDialogView.btnOk.setOnClickListener {
                        mAlertDialog.dismiss()
                            Toast.makeText(applicationContext, android.R.string.yes, Toast.LENGTH_SHORT).show()
                    }

                    mDialogView.detail.setOnClickListener {
                            permintaan = binding.permintaan.text.toString().toDouble()
                            persediaan = binding.persediaan.text.toString().toDouble()
                            fuzzification()
                            inference()
                            defuzzyfication()
                            if (string_result != "") {
                                startActivity(Intent(this, HasilActivity::class.java).also {
                                    it.putExtra("result",string_result )
                                    it.putExtra("angka",final_result)
                                    it.putExtra("a",permintaanString)
                                    it.putExtra("b",permintaanAngka)
                                    it.putExtra("fuzzystring",persediaanString)
                                    it.putExtra("fuzzyangka",persediaanAngka)
                                    it.putExtra("inferenceangka",angkaInference)
                                    it.putExtra("inferencestring",stringInference)
                                    it.putExtra("defuzziangka",angkaDisjuction)
                                    it.putExtra("defuzziString",stringDisjuction)
                                })
                            } else {
                                Toast.makeText(this, "Blook", Toast.LENGTH_SHORT).show()
                            }
                    }

//                    builder.setNegativeButton(android.R.string.no) { dialog, which ->
//                        Toast.makeText(applicationContext,
//                                android.R.string.no, Toast.LENGTH_SHORT).show()
//                    }



                    Toast.makeText(this, "Pastikan Nilai Permintaan 1000 - 1600 dan Persediaan 600 - 900", Toast.LENGTH_SHORT).show()
                }else{
                    permintaan = binding.permintaan.text.toString().toDouble()
                    persediaan = binding.persediaan.text.toString().toDouble()
                    fuzzification()
                    inference()
                    defuzzyfication()
                    if (string_result != "") {
                        startActivity(Intent(this, HasilActivity::class.java).also {
                            it.putExtra("result", string_result )
                            it.putExtra("angka",final_result)
                            it.putExtra("a",permintaanString)
                            it.putExtra("b",permintaanAngka)
                            it.putExtra("fuzzystring",persediaanString)
                            it.putExtra("fuzzyangka",persediaanAngka)
                            it.putExtra("inferenceangka",angkaInference)
                            it.putExtra("inferencestring",stringInference)
                            it.putExtra("defuzziangka",angkaDisjuction)
                            it.putExtra("defuzziString",stringDisjuction)
                        })
                    } else {
                        Toast.makeText(this, "Blook", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}