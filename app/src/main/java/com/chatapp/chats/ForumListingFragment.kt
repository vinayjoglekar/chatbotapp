package com.chatapp.chats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chatapp.ForumRecyclerAdapter
import com.chatapp.R
import com.chatapp.chats.chatbot.ChatBotFragment
import kotlinx.android.synthetic.main.forum_listing_layout.*

class ForumListingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.forum_listing_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, ChatBotFragment())
                ?.addToBackStack(null)
                ?.commit()
        }

        val adapter = ForumRecyclerAdapter(getForumList())
        recyclerForums.adapter = adapter
    }

    fun getForumList(): ArrayList<String> {
        val forums = ArrayList<String>()
        forums.add("")
        forums.add("")
        forums.add("")
        forums.add("")
        return forums
    }
}