package petros.efthymiou.groovy.playlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import petros.efthymiou.groovy.R
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class PlayListFragment : Fragment() {

    lateinit var viewModel: PlaylistViewModel

    @Inject
    lateinit var viewModelFactory: PlaylistViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupViewModel()
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        Log.e("List", "onCreateView")
        viewModel.playlists.observe(this as LifecycleOwner) { playlists ->
            Log.e("List", "playlists.observe")
            if (playlists.getOrNull() != null)
                setupList(view, playlists.getOrNull()!!)
            else {
                Log.e("List", "null")
            }
        }

        return view
    }

    private fun setupList(
        view: View?,
        playlists: List<PlayList>
    ) {
        Log.e("List", "setupList")
        with(view as RecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter =
                MyPlayListRecyclerViewAdapter(
                    playlists
                )
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlaylistViewModel::class.java)
    }

    companion object {


        @JvmStatic
        fun newInstance() =
            PlayListFragment()
    }
}