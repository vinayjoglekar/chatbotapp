package com.chatapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.chatapp.chats.models.ForumModel
import com.chatapp.databinding.ChatForumsLayoutBinding
import com.chatapp.utils.RecyclerClickListener

class ForumRecyclerAdapter : RecyclerView.Adapter<ForumRecyclerAdapter.ForumViewHolder>() {

    var forumList: ArrayList<ForumModel> = ArrayList()
    lateinit var onClickListener: RecyclerClickListener

    fun addOnClickListener(clickListener: RecyclerClickListener){
        onClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForumViewHolder {
        val binding: ChatForumsLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.chat_forums_layout, parent, false)
        return ForumViewHolder(binding)
    }

    fun addForums(forumList: ArrayList<ForumModel>){
        this.forumList.clear()
        this.forumList = forumList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = forumList.size

    override fun onBindViewHolder(holder: ForumViewHolder, position: Int) {
        val model = forumList[holder.adapterPosition]
        holder.binding.model = model
        holder.binding.root.setOnClickListener {
            onClickListener.onRowClicked(model)
        }
    }

    class ForumViewHolder(val binding: ChatForumsLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}