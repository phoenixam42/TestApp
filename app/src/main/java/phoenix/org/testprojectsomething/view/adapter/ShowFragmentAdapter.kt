package phoenix.org.testprojectsomething.view.adapter

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import phoenix.org.testprojectsomething.R
import phoenix.org.testprojectsomething.databinding.ItemShowRvBinding
import phoenix.org.testprojectsomething.network.MovieApi
import phoenix.org.testprojectsomething.view.MainActivity

class ShowFragmentAdapter(
    private val arraylist: ArrayList<MovieApi.MovieRespond>,
    private val context: Context
) :
    RecyclerView.Adapter<ShowFragmentAdapter.ViewHolder>() {
    private var onClickListener: OnClickListener? = null

    class ViewHolder(binding: ItemShowRvBinding) : RecyclerView.ViewHolder(binding.root) {
        val poster = binding.imgItem
        val title = binding.itemTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemShowRvBinding.inflate(LayoutInflater.from(context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = arraylist[position]
        Glide
            .with(context)
            .load(model.Poster)
            .centerCrop()
            .into(holder.poster)
        holder.title.text = model.Title
        holder.itemView.setOnClickListener {
            /* *//* val intent = Intent(context.applicationContext, DetailsFragment::class.java)
            intent.putParcelableArrayListExtra("filmInfo",arraylist)*//*
            context.startActivity(intent)*/
            val bundle = Bundle()
            bundle.putString("test",model.imdbID)
            Log.i(TAG, "onBindViewHolder: ${bundle}")
            Log.i(TAG, "onBindViewHolder: ${model.imdbID[position]}")
            (context as MainActivity).findNavController(R.id.fragment_container)
                .navigate(R.id.detailsFragment, bundle)
            onClickListener?.onclick(position)
        }
    }

    override fun getItemCount(): Int {
        return arraylist.size
    }

    interface OnClickListener {
        fun onclick(position: Int)
    }
}


