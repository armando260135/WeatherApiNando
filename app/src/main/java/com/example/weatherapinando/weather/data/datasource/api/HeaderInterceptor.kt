package com.example.weatherapinando.weather.data.datasource.api

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeaderInterceptor @Inject constructor() : Interceptor {

    companion object {
        private const val HEADER_CONTENT_TYPE = "Content-Type"
        private const val HEADER_VALUE_JSON = "application/json"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val requestBuilder = originalRequest.newBuilder()
            .addHeader(HEADER_CONTENT_TYPE, HEADER_VALUE_JSON)
            .method(originalRequest.method, originalRequest.body)

        return chain.proceed(requestBuilder.build())
    }
}
