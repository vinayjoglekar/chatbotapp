package com.chatapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ForumRecyclerAdapter(val forumList: ArrayList<String>) : RecyclerView.Adapter<ForumRecyclerAdapter.ForumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForumViewHolder {
        val holder =
            ForumViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.chat_forums_layout, parent, false))
        return holder
    }

    override fun getItemCount(): Int = forumList.size

    override fun onBindViewHolder(holder: ForumViewHolder, position: Int) {
    }

    class ForumViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}