package com.example.atividadeavaliativa1.repository.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private
    const val BASE_URL = "http://10.0.2.2:3001/"
    const val TO_TEST_BASE_URL = "http://localhost:3001/"

    // lazy valuation -> só vai executar se algum momento, em tempo de excução,
    // essa função for chamada. Evitando disperdício de recursos.
    val api : LoginApiInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(LoginApiInterface::class.java)
    }

    val testapi : LoginApiInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(TO_TEST_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(LoginApiInterface::class.java)
    }

    val productApi: ProductApiInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ProductApiInterface::class.java)
    }
}