package petros.efthymiou.groovy.playlist

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import petros.efthymiou.groovy.playlist.PlayList
import petros.efthymiou.groovy.playlist.PlayListRepository
import javax.inject.Inject

class PlaylistViewModel(private val repository: PlayListRepository) : ViewModel() {
val loader=MutableLiveData<Boolean>()
    val playlists= liveData<Result<List<PlayList>>> {
        loader.postValue(true)
        emitSource(repository.getPlaylists()
            .onEach {
                loader.postValue(false)
            }
            .asLiveData())

    }
}
