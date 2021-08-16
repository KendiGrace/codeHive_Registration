package com.example.codehiveregistration.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.codehiveregistration.Api.ApiClient
import com.example.codehiveregistration.Api.ApiInterface
import com.example.codehiveregistration.Models.LoginRequest
import com.example.codehiveregistration.Models.LoginResponse
import com.example.codehiveregistration.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var etEmail2:EditText
    lateinit var etPass2:EditText
    lateinit var btnLogin:Button
    lateinit var pbLoading2:ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        castViews()
        clickButton()

    }
    fun castViews(){
        etEmail2=findViewById(R.id.etEmail2)
        etPass2=findViewById(R.id.etPass2)
        btnLogin=findViewById(R.id.btnLogin)
        pbLoading2=findViewById(R.id.pbLoading)
    }
    fun clickButton(){
        btnLogin.setOnClickListener {
            val email=etEmail2.text.toString()
            val password=etPass2.text.toString()
            if(email.isEmpty() && password.isEmpty()){
                etEmail2.setError("Email address required")
                etPass2.setError("Password required")
            }
            else{
                val logRequest = LoginRequest(email = email, password = password)
                val retrofit=ApiClient.buildApiClient(ApiInterface::class.java)
                val request = retrofit.studentLogin(logRequest)
                request.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        pbLoading2.visibility=View.GONE
                        if(response.isSuccessful){
                            Toast.makeText(baseContext,"Login request successful",Toast.LENGTH_SHORT).show()
                            var intent = Intent(baseContext, CoursesActivity::class.java)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(baseContext,response.errorBody()!!.toString(),Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                   Toast.makeText(baseContext,"Your login request cannot be completed at this time,Pleas try again later",Toast.LENGTH_LONG).show()
                    }

                })
            }

        }
    }

}