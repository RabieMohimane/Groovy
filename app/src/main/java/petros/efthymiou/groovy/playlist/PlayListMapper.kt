package petros.efthymiou.groovy.playlist

import petros.efthymiou.groovy.R
import javax.inject.Inject

class PlayListMapper @Inject constructor() : Function1<List<PlayListRaw>, List<PlayList>> {
    override fun invoke(playlistRaw: List<PlayListRaw>): List<PlayList> {
        return playlistRaw.map {
            PlayList(
                it.id, it.name, it.category,
                if (it.category.equals("rock"))
                    R.mipmap.rock
                else
                    R.mipmap.playlist
            )
        }
    }

}
