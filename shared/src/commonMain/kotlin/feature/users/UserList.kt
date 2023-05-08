package feature.users

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seiko.imageloader.model.ImageRequest
import com.seiko.imageloader.rememberAsyncImagePainter
import data.model.GitHubUser
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@Composable
fun UserList(uiState: UserListUiState, onLoadMore: () -> Unit) {
    when (uiState) {
        UserListUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        UserListUiState.Error -> {
            // TODO: retry
            Text("Error")
        }

        is UserListUiState.Success -> {
            UserList(
                users = uiState.users,
                isLoadingMore = uiState.isLoadingMore,
                onLoadMore = onLoadMore
            )
        }
    }
}

@Composable
private fun UserList(users: List<GitHubUser>, isLoadingMore: Boolean, onLoadMore: () -> Unit) {
    val listState = rememberLazyListState()
    LaunchedEffect(listState) {
        snapshotFlow { listState.canScrollForward }
            .distinctUntilChanged()
            .filter { !it }
            .collect {
                onLoadMore()
            }
    }

    LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
        items(items = users, key = { it.id }) { user ->
            GitHubUserItem(user)
        }
        if (isLoadingMore) {
            item(key = "loading") {
                Box(
                    modifier = Modifier.fillMaxWidth().height(80.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
private fun GitHubUserItem(user: GitHubUser) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val request = remember(user.avatarUrl) {
                ImageRequest(user.avatarUrl)
            }
            val painter = rememberAsyncImagePainter(request)

            Image(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(color = Color.LightGray),
                painter = painter,
                contentDescription = null
            )
            Text(text = user.name, fontSize = 16.sp, fontWeight = FontWeight.Bold, overflow = TextOverflow.Ellipsis, maxLines = 1)
        }
        Divider()
    }
}
