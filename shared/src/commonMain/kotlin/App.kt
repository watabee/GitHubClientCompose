import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.moriatsushi.koject.inject
import feature.users.UserListScreen

@Composable
fun App() {
    MaterialTheme {
        Navigator(screen = inject<UserListScreen>())
    }
}
