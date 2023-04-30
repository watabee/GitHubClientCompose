package feature.users

import androidx.compose.runtime.Immutable
import data.model.GitHubUser

@Immutable
sealed interface GitHubUserListUiState {
    object Loading : GitHubUserListUiState

    object Error : GitHubUserListUiState

    data class Success(
        val users: List<GitHubUser>,
        val isLoadingMore: Boolean,
        val isLoadMoreError: Boolean
    ) : GitHubUserListUiState
}
