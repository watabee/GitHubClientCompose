package data.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.http.HttpRequest
import com.apollographql.apollo3.api.http.HttpResponse
import com.apollographql.apollo3.network.http.HttpInterceptor
import com.apollographql.apollo3.network.http.HttpInterceptorChain
import com.github.watabee.githubclientcompose.BuildKonfig
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton

@Singleton
@Provides
fun provideApolloClient(): ApolloClient {
    return ApolloClient.Builder()
        .serverUrl("https://api.github.com/graphql")
        .addHttpInterceptor(object : HttpInterceptor {
            override suspend fun intercept(request: HttpRequest, chain: HttpInterceptorChain): HttpResponse {
                val newRequest = request.newBuilder()
                    .addHeader("Authorization", "Bearer ${BuildKonfig.githubToken}")
                    .build()
                return chain.proceed(newRequest)
            }
        })
        .build()
}
