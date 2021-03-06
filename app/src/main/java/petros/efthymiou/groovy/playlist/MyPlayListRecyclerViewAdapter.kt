package petros.efthymiou.groovy.playlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import petros.efthymiou.groovy.R


class MyPlayListRecyclerViewAdapter(
    private val values: List<PlayList>,
    private val listener: (String) -> Unit
) : RecyclerView.Adapter<MyPlayListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.playlist_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.playlistName.text = item.name
        holder.playlistCategory.text = item.category
        holder.playlistImage.setImageResource(item.image)
        holder.root.setOnClickListener{listener(item.id)}
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val playlistName: TextView = view.findViewById(R.id.playlist_name)
        val playlistCategory: TextView = view.findViewById(R.id.playlist_category)
        val playlistImage: ImageView = view.findViewById(R.id.image)
        val root: LinearLayout = view.findViewById(R.id.playlist_item_root)


    }
}