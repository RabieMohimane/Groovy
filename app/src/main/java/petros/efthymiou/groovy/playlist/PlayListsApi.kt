package petros.efthymiou.groovy.playlist

import retrofit2.http.GET

interface PlayListsApi {
    @GET("/playlist")
    suspend fun getAllPlayLists():List<PlayList>
}