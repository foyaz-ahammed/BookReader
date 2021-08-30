package com.orangetheory.book.modules

import com.orangetheory.book.helpers.BASE_URL
import com.orangetheory.book.repository.Repository
import com.orangetheory.book.repository.api.BookService
import com.orangetheory.book.viewmodels.BookViewModel
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/** Define module variables */
val networkModule = module {
    single { provideRetrofit(get(), get())}
    single { provideOkHttpClient() }
    single { provideMoshi() }
}

val repositoryModule = module {
    single { Repository(get()) }
    single { provideApi(get()) }
}

val viewModelModule = module {
    viewModel { BookViewModel(get()) }
}

/**
 * @return [Retrofit] instance
 */
private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    moshi: Moshi
): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .client(okHttpClient)
    .build()

/**
 * @return [OkHttpClient] instance
 */
private fun provideOkHttpClient() = OkHttpClient.Builder()
    .readTimeout(10L, TimeUnit.SECONDS)
    .readTimeout(15L, TimeUnit.SECONDS)
    .addInterceptor(
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    )
    .build()

/**
 * @return [Moshi] instance
 */
private fun provideMoshi(): Moshi = Moshi.Builder().build()

/**
 * @return [BookService] instance
 */
fun provideApi(retrofit: Retrofit): BookService = retrofit.create(BookService::class.java)