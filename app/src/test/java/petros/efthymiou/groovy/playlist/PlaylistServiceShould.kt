package petros.efthymiou.groovy.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import petros.efthymiou.groovy.utils.BaseUnitTest
import java.lang.RuntimeException

class PlaylistServiceShould : BaseUnitTest() {
    private val api: PlayListsApi = mock()
    private lateinit var service: PlayListService
    private val playlists: List<PlayListRaw> =mock()

    @Test
    fun fetchPlaylistsFromApi() = runBlockingTest {
        service = PlayListService(api)
        service.fetchPlayliste().first()
        verify(api, times(1)).getAllPlayLists()
    }

    @Test
    fun convertresultsToFlowAndEmitThem() = runBlockingTest {
        mockSuccessfulCase()
        assertEquals(Result.success(playlists), service.fetchPlayliste().first())
    }



    @Test
    fun emitErrorResultWhenNetworkFails()= runBlockingTest {
        mockFailureCase()
        assertEquals("Something went wrong",service.fetchPlayliste()
            .first().exceptionOrNull()?.message)
    }

    private suspend fun mockFailureCase() {
        whenever(api.getAllPlayLists()).thenThrow(RuntimeException("Damn developer backend"))
        service = PlayListService(api)
    }

    private suspend fun mockSuccessfulCase() {
        service = PlayListService(api)
        whenever(api.getAllPlayLists()).thenReturn(playlists)
    }
}