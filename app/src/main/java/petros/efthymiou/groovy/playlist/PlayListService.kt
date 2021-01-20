package petros.efthymiou.groovy.playlist

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class PlayListService(val playListsApi: PlayListsApi) {


    suspend fun fetchPlayliste(): Flow<Result<List<PlayList>>> {


        return flow {
            emit(Result.success(playListsApi.getAllPlayLists()))
        }.catch {

            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }

}
