import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import feature.users.GitHubUserList

@Composable
fun App() {
    MaterialTheme {
        GitHubUserList()
    }
}
