package pl.adrianczerwinski.dazn.common.helpers

import java.time.OffsetDateTime
import java.time.ZoneId
import javax.inject.Inject

class CurrentDate @Inject constructor() {

    fun getCurrentDate(): OffsetDateTime {
        return OffsetDateTime.now(ZoneId.systemDefault())
    }
}
