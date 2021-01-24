package petros.efthymiou.groovy.playlist

import junit.framework.Assert.assertEquals
import org.junit.Test
import petros.efthymiou.groovy.R
import petros.efthymiou.groovy.utils.BaseUnitTest

class PlaylistMapperShould : BaseUnitTest() {
    private val playlistRaw = PlayListRaw("1", "da name", "da category")
    private val playlistRaw2 = PlayListRaw("2", "da name", "rock")
    private val mapper = PlayListMapper()
    private val playlists = mapper(listOf(playlistRaw,playlistRaw2))
    val playlist=playlists[0]
    val playlist2=playlists[1]

    @Test
    fun keepIdSame() {
        assertEquals(playlistRaw.id, playlist.id)
    }
    @Test
    fun keepSameName(){
        assertEquals(playlistRaw.name,playlist.name)
    }
    @Test
    fun keepSameCategory(){
        assertEquals(playlistRaw.category,playlist.category)
    }
    @Test
    fun mapDefaultWhenNotRock(){
        assertEquals(playlist.image, R.mipmap.playlist)
    }
    @Test
    fun mapRockImageWhenRockCategory(){
        assertEquals(playlist2.image,R.mipmap.rock)
    }
}