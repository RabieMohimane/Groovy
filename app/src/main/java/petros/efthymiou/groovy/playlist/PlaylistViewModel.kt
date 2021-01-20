package petros.efthymiou.groovy.playlist

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import petros.efthymiou.groovy.playlist.PlayList
import petros.efthymiou.groovy.playlist.PlayListRepository
import javax.inject.Inject

class PlaylistViewModel(private val repository: PlayListRepository) : ViewModel() {

    val playlists= liveData<Result<List<PlayList>>> {
        emitSource(repository.getPlaylists().asLiveData())

    }
}
