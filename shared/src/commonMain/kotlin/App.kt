import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import feature.users.UserList
import feature.users.UserListUiState

@Composable
fun App() {
    MaterialTheme {
        UserList(uiState = UserListUiState.Loading, onLoadMore = {})
    }
}
