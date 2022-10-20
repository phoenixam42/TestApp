package phoenix.org.testprojectsomething.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import phoenix.org.testprojectsomething.databinding.ItemShowRvBinding
import phoenix.org.testprojectsomething.network.MovieApi

class ShowFragmentAdapter(val list: ArrayList<MovieApi.MovieRespond>, val context: Context) :
    RecyclerView.Adapter<ShowFragmentAdapter.ViewHolder>() {
    class ViewHolder(binding: ItemShowRvBinding) : RecyclerView.ViewHolder(binding.root) {
        val poster = binding.imgItem
        val title = binding.itemTitle
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemShowRvBinding.inflate(LayoutInflater.from(context)))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]
        Glide
            .with(context)
            .load(model.Poster)
            .centerCrop()
            .into(holder.poster);
        holder.title.text = model.Title
    }
    override fun getItemCount(): Int {
        return list.size
    }
}