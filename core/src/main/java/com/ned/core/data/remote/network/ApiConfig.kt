package com.ned.core.data.remote.network

import com.ned.core.BuildConfig
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {
    private const val HOSTNAME = "api.disneyapi.dev"

    fun getApiService(): ApiService {
        val BASE_URL = BuildConfig.BASE_URL
        val certificatePinner = CertificatePinner.Builder()
            .add(HOSTNAME, "sha256/d16Cu9pW0YQBf4flFNxlH0HI3hKE9OaAwOBpg3EoZAM=")
            .add(HOSTNAME, "sha256/0Bbh/jEZSKymTy3kTOhsmlHKBB32EDu1KojrP3YfV9c=")
            .build()

        val loggingLevel = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                            else HttpLoggingInterceptor.Level.NONE

        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(loggingLevel))
            .certificatePinner(certificatePinner)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiService::class.java)
    }
}
