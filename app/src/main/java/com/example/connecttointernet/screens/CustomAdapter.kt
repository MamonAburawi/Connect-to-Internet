package com.example.connecttointernet.screens

import android.content.Context
import android.nfc.Tag
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.example.connecttointernet.R
import com.example.connecttointernet.databinding.ItemViewBinding
import com.example.connecttointernet.model.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.StringBuilder


class CustomAdapter(private val context: Context) : ListAdapter<Photo, CustomAdapter.ViewHolder>(MarsDiffCallback()) {

    companion object{
        const val TAG = "CustomAdapter"
    }
    val diff = AsyncListDiffer(this, MarsDiffCallback())

    class ViewHolder private constructor(private val binding: ItemViewBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: Photo, context: Context) {

            Log.d(TAG,"imageUri: ${data.imgSrc}")
            binding.apply {
                name.text = data.camera!!.name
                setImage(context,data.imgSrc,image)
            }


        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                return ViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.item_view, parent, false))
            }
        }


        private fun setImage(context: Context, uri: String, image: ImageView){
            GlobalScope.launch(Dispatchers.Main) {
                try {

                    /** we fix the scheme by adding s char in 4 position in uri **/
                    val stringBuilder = StringBuilder(uri)
                    val fixUri = stringBuilder.insert(4,"s").toString()

                    val options = RequestOptions()
                        .placeholder(R.drawable.progress_animator)
                        .priority(Priority.HIGH)
                        .dontAnimate()
                        .dontTransform()

                    val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
                    if (fixUri.contains("https://")) {
                        Glide.with(context)
                            .asBitmap()
                            .transition(withCrossFade(factory))
                            .apply(options)
                            .load(fixUri.toUri().buildUpon().scheme("https").build())
                            .into(image)
                    }

                }catch (ex: Exception){
                    Log.d(TAG,"error: "+ex.message.toString())
                }

            }
        }



    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = diff.currentList[position]
        holder.onBind(data, context)
    }

    override fun getItemCount(): Int {
        return diff.currentList.size
    }

}


class MarsDiffCallback : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }
}


//
//class CustomAdapter(private val data: MarsData) :
//    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
//
//    class ViewHolder(val binding: ItemViewBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {
//
//
//    }
//
//    // Create new views (invoked by the layout manager)
//    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
//        // Create a new view, which defines the UI of the list item
//
//        val layoutInflater = LayoutInflater.from(viewGroup.context)
//        val binding = DataBindingUtil.inflate<ItemViewBinding>(layoutInflater,R.layout.item_view,viewGroup,false)
//
////        val view = LayoutInflater.from(viewGroup.context)
////            .inflate(R.layout.item_view, viewGroup, false)
//
//        return ViewHolder(binding,viewGroup.context)
//    }
//
//    // Replace the contents of a view (invoked by the layout manager)
//    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
//
//        val d = data.photos[position]
//        setImage(viewHolder.context,d.imgSrc,viewHolder.binding.image)
//
//        // Get element from your dataset at this position and replace the
//        // contents of the view with that element
//
//    }
//
//
//    override fun getItemCount() = data.photos.size
//
//
//    fun setImage(context: Context, uri: String,image: ImageView){
//        val options = RequestOptions()
//            .placeholder(R.drawable.progress_animator)
//            .priority(Priority.HIGH)
//            .dontAnimate()
//            .dontTransform()
//
//        val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
//        if (uri.contains("https://")) {
//            Glide.with(context)
//                .asBitmap()
//                .transition(withCrossFade(factory))
//                .apply(options)
//                .load(uri.toUri().buildUpon().scheme("https").build())
//                .into(image)
//
//        }
//    }
//
//}
//

