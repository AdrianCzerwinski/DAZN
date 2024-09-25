package pl.adrianczerwinski.dazn.events.ui

import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import pl.adrianczerwinski.dazn.common.models.Date
import pl.adrianczerwinski.dazn.common.models.DateType
import pl.adrianczerwinski.dazn.common.models.ScreenState
import pl.adrianczerwinski.dazn.events.domain.model.Event
import pl.adrianczerwinski.dazn.events.domain.usecase.GetEventsUseCase
import pl.adrianczerwinski.dazn.events.ui.model.EventsUiState
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class EventsViewModelTest {

    @get:Rule
    val dispatcherRule = CoroutineTestRule()

    private val getEventsUseCase = mockk<GetEventsUseCase>()
    private val event = Event(
        id = "id",
        date = Date(
            date = "date",
            time = "time",
            type = DateType.STANDARD
        ),
        title = "title",
        imageUrl = "imageUrl",
        videoUrl = "videoUrl",
        subtitle = "subtitle"
    )

    @Test
    fun `getEvents should return success when request succeeds`() = runTest{
        // given
        val events = listOf(event)
        val expected = EventsUiState(screenState = ScreenState.CONTENT, events = events)
        coEvery { getEventsUseCase() } returns events

        //given
        val viewModel = getViewModel()

        // then
        viewModel.state.test {
            assertEquals(expected, awaitItem())
        }
    }

    @Test
    fun `getEvents should return error when request fails`() = runTest {
        // given
        coEvery { getEventsUseCase() } returns null
        val expected = EventsUiState(screenState = ScreenState.ERROR)

        // given
        val viewModel = getViewModel()

        // then
        viewModel.state.test {
            assertEquals(expected, awaitItem())
        }
    }

    @Test
    fun `getEvents should return empty when request returns empty list`() = runTest {
        // given
        coEvery { getEventsUseCase() } returns emptyList()
        val expected = EventsUiState(screenState = ScreenState.EMPTY)

        // given
        val viewModel = getViewModel()

        // then
        viewModel.state.test {
            assertEquals(expected, awaitItem())
        }
    }

    private fun getViewModel() = EventsViewModel(getEventsUseCase)
}

@OptIn(ExperimentalCoroutinesApi::class)
class CoroutineTestRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
): TestWatcher() {
    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}