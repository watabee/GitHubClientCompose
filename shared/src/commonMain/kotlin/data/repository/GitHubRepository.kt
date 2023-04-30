package data.repository

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.api.http.HttpRequest
import com.apollographql.apollo3.api.http.HttpResponse
import com.apollographql.apollo3.network.http.HttpInterceptor
import com.apollographql.apollo3.network.http.HttpInterceptorChain
import com.github.watabee.githubclientcompose.BuildKonfig
import com.github.watabee.githubclientcompose.data.graphql.GetUsersQuery

class GitHubRepository {
    private val apolloClient = ApolloClient.Builder()
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

    suspend fun getUsers(after: String?): GetUsersQuery.Search {
        val query = GetUsersQuery(first = 30, after = Optional.presentIfNotNull(after))
        return apolloClient.query(query)
            .execute()
            .dataAssertNoErrors
            .search
    }
}
