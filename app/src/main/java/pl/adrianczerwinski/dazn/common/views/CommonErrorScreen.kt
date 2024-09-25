package pl.adrianczerwinski.dazn.common.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import pl.adrianczerwinski.dazn.R

@Composable
fun CommonErrorScreen(
    errorMessage: String = stringResource(R.string.common_error_message),
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) = Column(
    modifier = modifier.fillMaxSize().padding(12.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Icon(
        modifier = Modifier.size(60.dp),
        imageVector = Icons.Filled.Close,
        contentDescription = "Error Icon",
        tint = MaterialTheme.colorScheme.onBackground
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = errorMessage,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onBackground
    )
    Spacer(modifier = Modifier.height(16.dp))
    Button(
        onClick = { onClick() },
        content = {
            Text(
                text = stringResource(R.string.try_again),
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
            )
        }
    )
}

@LightDarkPreview
@Composable
private fun CommonErrorPreview() = ColumnPreview {
    CommonErrorScreen()
}
