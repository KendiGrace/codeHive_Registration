package com.example.codehiveregistration.Api

import com.example.codehiveregistration.Models.LoginRequest
import com.example.codehiveregistration.Models.LoginResponse
import com.example.codehiveregistration.Models.RegistrationRequest
import com.example.codehiveregistration.Models.RegistrationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/students/register/")
    fun registerStudent(@Body registrationRequest:RegistrationRequest):Call<RegistrationResponse>

    @POST("/students/login/")
    fun studentLogin(@Body loginRequest: LoginRequest):Call<LoginResponse>
}
