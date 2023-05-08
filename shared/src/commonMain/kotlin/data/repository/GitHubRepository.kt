package data.repository

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.github.watabee.githubclientcompose.data.graphql.GetUsersQuery
import com.moriatsushi.koject.Provides

@Provides
class GitHubRepository(private val apolloClient: ApolloClient) {

    suspend fun getUsers(after: String?): GetUsersQuery.Search {
        val query = GetUsersQuery(first = 30, after = Optional.presentIfNotNull(after))
        return apolloClient.query(query)
            .execute()
            .dataAssertNoErrors
            .search
    }
}
