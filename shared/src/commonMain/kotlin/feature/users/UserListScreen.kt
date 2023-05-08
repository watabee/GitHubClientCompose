package feature.users

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import data.repository.GitHubRepository

class UserListScreen : Screen {

    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        val screenModel: UserListScreenModel = rememberScreenModel { UserListScreenModel(GitHubRepository()) }
        UserList(uiState = screenModel.uiState, onLoadMore = screenModel::loadMore)
    }
}
