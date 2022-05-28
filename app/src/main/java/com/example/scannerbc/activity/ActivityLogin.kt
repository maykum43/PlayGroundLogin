package com.example.scannerbc.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.scannerbc.MainActivity
import com.example.scannerbc.R
import com.example.scannerbc.api.ApiConfig
import com.example.scannerbc.helper.SharePreference
import com.example.scannerbc.model.ResponseUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityLogin: AppCompatActivity() {
    lateinit var sph : SharePreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sph = SharePreference(this)

        val btn_login = findViewById<Button>(R.id.btn_login)
        btn_login.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val edt_login_email = findViewById<EditText>(R.id.edt_login_email)
        val edt_login_pass = findViewById<EditText>(R.id.edt_login_pass)
        val email = edt_login_email.text.toString()
        val password = edt_login_pass.text.toString()

        if(email.isEmpty()){
            edt_login_email.error = "Email harus diisi"
            return
        }

        if(password.isEmpty()){
            edt_login_pass.error = "Password harus diisi"
            return
        }

        ApiConfig.instanceRetrofit.login(email,password)
            .enqueue(object : Callback<ResponseUser> {
                override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                    val response = response.body()

                    if (response != null){
                        if (response.status == 0){
                            Toast.makeText(this@ActivityLogin, response.message, Toast.LENGTH_SHORT).show()
                        }else{
                            sph.setStatusLogin(true)
                            startActivity(Intent(this@ActivityLogin, MainActivity::class.java))
                            finish()
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                    Toast.makeText(this@ActivityLogin, t.localizedMessage, Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }
}