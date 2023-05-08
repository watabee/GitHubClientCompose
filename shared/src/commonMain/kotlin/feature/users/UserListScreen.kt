package feature.users

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import com.moriatsushi.koject.Provides
import data.repository.GitHubRepository

@Provides
class UserListScreen(private val gitHubRepository: GitHubRepository) : Screen {

    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        val screenModel: UserListScreenModel = rememberScreenModel { UserListScreenModel(gitHubRepository) }
        UserList(uiState = screenModel.uiState, onLoadMore = screenModel::loadMore)
    }
}
