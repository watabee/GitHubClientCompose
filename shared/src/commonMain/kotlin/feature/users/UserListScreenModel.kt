package feature.users

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import data.model.GitHubUser
import data.repository.GitHubRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class UserListScreenModel(private val gitHubRepository: GitHubRepository) : ScreenModel {
    private val viewModelScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    var uiState: UserListUiState by mutableStateOf(UserListUiState.Loading)
    private var after: String? = null
    private var loadJob: Job? = null

    init {
        load()
    }

    override fun onDispose() {
        viewModelScope.cancel()
    }

    fun load() {
        if (loadJob?.isActive == true) {
            return
        }
        loadJob = viewModelScope.launch {
            val successState = uiState as? UserListUiState.Success

            try {
                uiState = successState?.copy(isLoadingMore = true, isLoadMoreError = false) ?: UserListUiState.Loading
                val result = gitHubRepository.getUsers(after = after)
                after = if (result.pageInfo.hasNextPage) result.pageInfo.endCursor else null

                val users = successState?.users.orEmpty() + result.edges?.mapNotNull {
                    it?.node?.onUser?.let { user ->
                        GitHubUser(
                            id = user.id,
                            name = user.login,
                            avatarUrl = user.avatarUrl,
                            bio = user.bio
                        )
                    }
                }.orEmpty()

                uiState = UserListUiState.Success(
                    isLoadingMore = false,
                    isLoadMoreError = false,
                    users = users.distinctBy { it.id }
                )
            } catch (e: Throwable) {
                uiState = successState?.copy(isLoadingMore = false, isLoadMoreError = true) ?: UserListUiState.Error
            }
        }
    }

    fun loadMore() {
        val successState = uiState as? UserListUiState.Success ?: return
        if (successState.isLoadingMore || successState.isLoadMoreError) {
            return
        }

        load()
    }
}
