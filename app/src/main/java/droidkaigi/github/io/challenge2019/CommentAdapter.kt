package droidkaigi.github.io.challenge2019

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import droidkaigi.github.io.challenge2019.dummy.Ad
import droidkaigi.github.io.challenge2019.data.api.response.Item
import droidkaigi.github.io.challenge2019.databinding.DummyAdBinding
import droidkaigi.github.io.challenge2019.databinding.ItemCommentBinding
import java.lang.IllegalArgumentException


class CommentAdapter(
    var comments: List<Item?>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ViewHolder(val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root)
    class AdViewHolder(val binding: DummyAdBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemViewType.COMMENT.ordinal -> {
                val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ViewHolder(binding)
            }
            ItemViewType.AD.ordinal -> {
                val binding = DummyAdBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                AdViewHolder(binding)
            }
            else -> {
                throw IllegalArgumentException("undeifiend view type $viewType")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position  < comments.size) ItemViewType.COMMENT.ordinal else ItemViewType.AD.ordinal
    }

    override fun getItemCount(): Int = comments.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ViewHolder -> {
                holder.binding.item = comments[position]
            }
            is AdViewHolder -> {
                holder.binding.ad = Ad("it is dummy!")
            }
        }
    }

    enum class ItemViewType(type: Int) {
        COMMENT(0),
        AD(1)
    }
}