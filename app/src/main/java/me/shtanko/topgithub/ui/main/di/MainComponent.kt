package me.shtanko.topgithub.ui.main.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Component
import dagger.Module
import dagger.Provides
import me.shtanko.topgithub.ui.main.GITHUB_API_URL
import me.shtanko.topgithub.ui.main.GSON_DATE_FORMAT
import me.shtanko.topgithub.ui.main.MainFragment
import me.shtanko.topgithub.ui.main.api.GithubApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object MainModule {

    @Provides
    @JvmStatic
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setPrettyPrinting()
            .setDateFormat(GSON_DATE_FORMAT)
            .create()
    }

    @Provides
    @JvmStatic
    @Singleton
    fun provideConvertersFactory(gson: Gson): Converter.Factory =
        GsonConverterFactory.create(gson)

    @Provides
    @JvmStatic
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @JvmStatic
    @Singleton
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient().newBuilder()
            .addNetworkInterceptor(interceptor)
            .build()

    @Provides
    @JvmStatic
    @Singleton
    fun provideRetrofitBuilder(
        client: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit.Builder = Retrofit.Builder()
        .addConverterFactory(converterFactory)
        .client(client)

    @Provides
    @JvmStatic
    @Singleton
    fun provideGithubApiService(builder: Retrofit.Builder): GithubApiService = builder
        .baseUrl(GITHUB_API_URL)
        .build()
        .create(GithubApiService::class.java)

}

@Component(modules = [MainModule::class])
@Singleton
interface MainComponent {
    fun inject(fragment: MainFragment)
}