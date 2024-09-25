package pl.adrianczerwinski.dazn.common.views.helpers

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import pl.adrianczerwinski.dazn.R
import pl.adrianczerwinski.dazn.common.models.Date
import pl.adrianczerwinski.dazn.common.models.DateType

@Composable
fun getDateText(date: Date): String {

    val suffix = when (date.type) {
        DateType.YESTERDAY -> stringResource(id = R.string.yesterday)
        DateType.TODAY -> stringResource(id = R.string.today)
        DateType.TOMORROW -> stringResource(id = R.string.tomorrow)
        DateType.STANDARD -> ""
    }

    val dateTime = if (date.type == DateType.STANDARD) {
        "${date.date}, ${date.time}"
    } else {
        ", ${date.time}"
    }

    return suffix + dateTime
}