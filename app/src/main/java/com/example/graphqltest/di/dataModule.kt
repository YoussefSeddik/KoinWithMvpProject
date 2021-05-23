package com.example.graphqltest.di

import com.apollographql.apollo.ApolloClient
import com.example.graphqltest.Constants
import com.example.graphqltest.ui.MainActivityContract
import com.example.graphqltest.ui.MainActivityPresenter
import com.graphq_test.data.data.ApiHelper
import com.graphq_test.data.data.ApiHelperImp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import java.util.concurrent.TimeUnit


val dataModule = module {

    single {
        HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {}
        }).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .pingInterval(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addNetworkInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        ApolloClient.builder()
            .serverUrl(Constants.BASE_URL)
            .okHttpClient(get())
            .build()
    }

    single<ApiHelper> {
        ApiHelperImp(get())
    }

    single {
        MainActivityPresenter(get())
    }

}






