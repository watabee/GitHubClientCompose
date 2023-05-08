package feature.users

import androidx.compose.runtime.Immutable
import data.model.GitHubUser

@Immutable
sealed interface UserListUiState {
    object Loading : UserListUiState

    object Error : UserListUiState

    data class Success(
        val users: List<GitHubUser>,
        val isLoadingMore: Boolean,
        val isLoadMoreError: Boolean
    ) : UserListUiState
}
