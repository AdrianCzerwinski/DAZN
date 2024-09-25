package pl.adrianczerwinski.dazn.player.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun VideoControls(
    modifier: Modifier = Modifier,
    @DrawableRes imageId: Int,
    isPlayButtonVisible: Boolean,
    onClick: () -> Unit,
) {
    if (isPlayButtonVisible) {
        IconButton(
            modifier = modifier,
            onClick = onClick,
        ) {
            Icon(
                modifier = Modifier.size(120.dp),
                painter = painterResource(imageId),
                contentDescription = null,
                tint = Color.White,
            )
        }
    }
}