package com.andalus.abomed7at55.mn_edek_a7la.networking

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
                .newBuilder()
                .addHeader("key", "LrhDt43t2YnwZasT5FzBzMumfJWQwWyD")
                .build()
        return chain.proceed(request)
    }
}