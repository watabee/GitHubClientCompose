import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import data.model.GitHubUser
import feature.users.GitHubUserList

private val users = listOf(
    GitHubUser(
        id = "MDQ6VXNlcjEyOTk0ODg3",
        name = "peng-zhihui",
        avatarUrl = "https://avatars.githubusercontent.com/u/12994887?u=6bfec84cb512892557cfed7fd7c52b0b0f41f95b&v=4"
    ),
    GitHubUser(
        id = "MDQ6VXNlcjI0MTEzOA==",
        name = "karpathy",
        avatarUrl = "https://avatars.githubusercontent.com/u/241138?u=05376db54475c3d23b3a409f4c47d14c4855dc28&v=4"
    ),
    GitHubUser(
        id = "MDQ6VXNlcjU0MzIyODU0",
        name = "rafaballerini",
        avatarUrl = "https://avatars.githubusercontent.com/u/54322854?u=ec4b38b15a6071ac1467e4343b66525f6c737820&v=4"
    ),
    GitHubUser(
        id = "MDQ6VXNlcjcyODk1",
        name = "geohot",
        avatarUrl = "https://avatars.githubusercontent.com/u/72895?u=64c16e3f87c708f1f3920331f1f6285f6529960e&v=4"
    ),
    GitHubUser(
        id = "MDQ6VXNlcjM5MTkx",
        name = "paulirish",
        avatarUrl = "https://avatars.githubusercontent.com/u/39191?u=6371e7d88f56f9fc4cf519a7ff4b1eb91dca2f17&v=4"
    ),
    GitHubUser(
        id = "MDQ6VXNlcjE3NjAxMw==",
        name = "wesbos",
        avatarUrl = "https://avatars.githubusercontent.com/u/176013?u=1d436e62dc32dbbf1bfefb4d658cd67553154c42&v=4"
    ),
    GitHubUser(
        id = "MDQ6VXNlcjE1MDA2ODQ=",
        name = "kentcdodds",
        avatarUrl = "https://avatars.githubusercontent.com/u/1500684?u=f35a28c4eead6ba9636a8ff5858f6977d8593c43&v=4"
    ),
    GitHubUser(
        id = "MDQ6VXNlcjgw",
        name = "ry",
        avatarUrl = "https://avatars.githubusercontent.com/u/80?v=4"
    ),
    GitHubUser(
        id = "MDQ6VXNlcjQ5MjExODM=",
        name = "kamranahmedse",
        avatarUrl = "https://avatars.githubusercontent.com/u/4921183?u=9a67bfd198f1a73f262d08b53eeb6ad2befc572f&v=4"
    ),
    GitHubUser(
        id = "MDQ6VXNlcjExNjAxMDQw",
        name = "3b1b",
        avatarUrl = "https://avatars.githubusercontent.com/u/11601040?u=ccd223915efaad0958c195ce57bf46ab40d67873&v=4"
    )
)

@Composable
fun App() {
    MaterialTheme {
        GitHubUserList(users = users)
    }
}

expect fun getPlatformName(): String