package com.chatapp.chats

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.chatapp.R
import com.chatapp.chats.models.CommentModel
import com.chatapp.chats.models.ForumModel
import kotlinx.android.synthetic.main.chat_screen_layout.*

class ChatActivity : AppCompatActivity() {

    lateinit var forumModel: ForumModel
    lateinit var adapter: ChatRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_screen_layout)
        forumModel = intent.getSerializableExtra("ForumModel") as ForumModel
//        collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent))
//        collapsingToolbar.setTitle(forumModel.forumName)
//        collapsingToolbar.setCollapsedTitleTextColor(Color.rgb(255, 255, 255));

        adapter = ChatRecyclerAdapter(getChatList())
//        recyclerChat.layoutManager = LinearLayoutManager(this)
//        recyclerChat.adapter = adapter

        tvForumTitle.text = forumModel.forumName
        tvForumDescription.text = forumModel.description
        tvCreator.text = "${forumModel.createrName} , ${forumModel.formattedDate}"
        tvForumType.text = forumModel.forumTopic

        imgBack.setOnClickListener {
            finish()
        }

        toolbarTitle.text = forumModel.forumName
    }

    fun getChatList(): ArrayList<CommentModel> {
        val messages = ArrayList<CommentModel>()
        return messages
    }
}