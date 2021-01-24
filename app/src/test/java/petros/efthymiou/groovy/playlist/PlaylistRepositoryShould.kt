package petros.efthymiou.groovy.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import petros.efthymiou.groovy.utils.BaseUnitTest
import java.lang.RuntimeException

class PlaylistRepositoryShould : BaseUnitTest() {

    private val service: PlayListService = mock()
    private val mapper: PlayListMapper = mock()
    private val playlists = mock<List<PlayList>>()
    private val playlistsRaw = mock<List<PlayListRaw>>()
private  val exception=RuntimeException("Something Went Wrong!!")
    @Test
    fun getPlaylistsfromService() = runBlockingTest {
        val repository = PlayListRepository(service,mapper)
        repository.getPlaylists()
        verify(service, times(1)).fetchPlayliste()
    }

    @Test
    fun emitMappedPlaylistsFromService() = runBlockingTest {
        val repository = mockSuccessfulCase()
        assertEquals(playlists,repository.getPlaylists().first().getOrNull())
    }
    @Test
    fun propagateErrors()= runBlockingTest{
        val repository = mockFailureCase()
        assertEquals(exception,repository.getPlaylists().first().exceptionOrNull())

    }
    @Test
    fun delegateBusinessLogicToMapper()= runBlockingTest{
        val repository=mockSuccessfulCase()
        repository.getPlaylists().first()
        verify(mapper, times(1)).invoke(playlistsRaw)
    }

    private suspend fun mockFailureCase(): PlayListRepository {
        whenever(service.fetchPlayliste()).thenReturn(
            flow {
                emit(Result.failure<List<PlayListRaw>>(exception))
            }
        )
        val repository = PlayListRepository(service,mapper)
        return repository
    }

    private suspend fun mockSuccessfulCase(): PlayListRepository {
        whenever(service.fetchPlayliste()).thenReturn(
            flow {
                emit(Result.success(playlistsRaw))
            }
        )
        whenever(mapper.invoke(playlistsRaw)).thenReturn(playlists)
        return PlayListRepository(service,mapper)

    }
}