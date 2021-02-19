package com.example.cats.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.cats.R

class CatAdapter :
    PagingDataAdapter<String, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

        companion object {
            private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<String>() {
                override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
                    oldItem == newItem

                override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
                    oldItem == newItem
            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder as? CatImageViewHolder)?.bind(item = getItem(position))
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return CatImageViewHolder.getInstance(parent)
        }

        class CatImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            companion object {
                fun getInstance(parent: ViewGroup): CatImageViewHolder {
                    val inflater = LayoutInflater.from(parent.context)
                    val view = inflater.inflate(R.layout.item_cat_image_view, parent, false)
                    return CatImageViewHolder(view)
                }
            }

            var ivCatMain: ImageView = view.findViewById(R.id.ivCatMain)

            fun bind(item: String?) {
                ivCatMain.load(item) {
                    placeholder(R.drawable.cat_icon)
                }
            }

        }
}