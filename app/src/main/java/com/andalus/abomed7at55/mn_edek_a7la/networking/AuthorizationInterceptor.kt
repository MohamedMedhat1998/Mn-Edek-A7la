package com.andalus.abomed7at55.mn_edek_a7la.networking

import com.andalus.abomed7at55.mn_edek_a7la.utils.Secrets
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
                .newBuilder()
                .addHeader(Secrets.HEADER_NAME, Secrets.HEADER_VALUE)
                .build()
        return chain.proceed(request)
    }
}