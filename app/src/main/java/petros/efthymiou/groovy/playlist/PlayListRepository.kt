package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow

class PlayListRepository(
    private val service: PlayListService
) {
    suspend fun getPlaylists(): Flow<Result<List<PlayList>>> =
        service.fetchPlayliste()


}
