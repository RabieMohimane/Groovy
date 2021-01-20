package petros.efthymiou.groovy.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import petros.efthymiou.groovy.utils.BaseUnitTest
import petros.efthymiou.groovy.utils.getValueForTest
import java.lang.RuntimeException


class PlayListViewModelShould : BaseUnitTest() {
    lateinit var viewModel: PlaylistViewModel
    val repository: PlayListRepository = mock()
    private val playlists = mock<List<PlayList>>()
    private val expected = Result.success(playlists)
    private val exception=RuntimeException("Something went wrong")

    @Test
    fun getPlaylistsFromRepository() = runBlockingTest {
        mockSuccessfulCase()
        viewModel.playlists.getValueForTest()
        verify(repository, times(1)).getPlaylists()
    }

    @Test
    fun emitsPlaylistsFromRepository() = runBlockingTest {
        mockSuccessfulCase()
        assertEquals(expected, viewModel.playlists.getValueForTest())
    }

    @Test
    fun emitErrorWhenReciveError() {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(Result.failure<List<PlayList>>(exception))
                }
            )
        }
        val viewModel=PlaylistViewModel(repository)
        val valueForTest = viewModel.playlists.getValueForTest()
        assertEquals(
            exception,
            if (valueForTest != null) valueForTest.exceptionOrNull() else throw KotlinNullPointerException()
        )
    }

    private fun mockSuccessfulCase() {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }
        viewModel =
            PlaylistViewModel(repository)
    }
}