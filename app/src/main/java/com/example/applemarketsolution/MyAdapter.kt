package com.example.applemarketsolution

import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView.OnItemLongClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarketsolution.databinding.ItemRecyclerviewBinding

class MyAdapter(
    private val mItems: MutableList<MyItem>,
    private val itemClickListener: (item: MyItem, position: Int) -> Unit,
    private val itemLongClickListener: (position: Int) -> Boolean
) : RecyclerView.Adapter<MyAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.Holder {
        val binding = ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: MyAdapter.Holder, position: Int) {
        holder.bind(mItems[position])
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    inner class Holder(private var binding : ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyItem) {
            binding.apply {
                ivImage.setImageResource(item.image)
                tvTitle.text = item.title
                tvAddress.text = item.address

                tvPrice.text = DecimalFormat("#,###").format(item.price) + "원"

                tvRepliesNum.text = item.replies.toString()
                tvLikesNum.text = item.likes.toString()

                if (item.likePressed)
                    ivLikes.setImageResource(R.drawable.like_pressed)
                else
                    ivLikes.setImageResource(R.drawable.likes)

                itemViewLayout.setOnClickListener {
                    itemClickListener(item, adapterPosition)
                }

                itemViewLayout.setOnLongClickListener {
                    itemLongClickListener(adapterPosition)
                }
            }
        }
    }
}