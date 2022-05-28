package com.example.scannerbc.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.scannerbc.databinding.AcitivityWelcomeBinding
import com.example.scannerbc.helper.SharePreference

class WelcomeActivity : AppCompatActivity() {
    lateinit var binding : AcitivityWelcomeBinding
    private lateinit var sph : SharePreference

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = AcitivityWelcomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        sph = SharePreference(this)
        binding.btnLogin.setOnClickListener {
//            sph.setStatusLogin(true)
            startActivity(Intent(this, ActivityLogin::class.java))

//            Toast.makeText(this, "Selamat Datang", Toast.LENGTH_SHORT).show()
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
        }
    }
}