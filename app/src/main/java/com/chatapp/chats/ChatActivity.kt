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
        //forumModel = intent.getSerializableExtra("ForumModel") as ForumModel
        collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent))
        //collapsingToolbar.setTitle(forumModel.createrName);
        collapsingToolbar.setCollapsedTitleTextColor(Color.rgb(255, 255, 255));

        adapter = ChatRecyclerAdapter(getChatList())
        recyclerChat.layoutManager = LinearLayoutManager(this)
        recyclerChat.adapter = adapter
    }

    fun getChatList(): ArrayList<CommentModel> {
        val messages = ArrayList<CommentModel>()
        val message1 = CommentModel()
        message1.memberName = "Vinay"
        message1.messageBody = "Hi, How are you all?"
        message1.isMessageByCurrentUser = false
        message1.timeCreated = "11:51 am"

        val message2 = CommentModel()
        message2.memberName = "Manjot"
        message2.messageBody = "Manjot this side"
        message2.isMessageByCurrentUser = false
        message2.timeCreated = "11:52 am"

        val message3 = CommentModel()
        message3.memberName = "Kunal Jadhav"
        message3.messageBody = "I prefer AC train"
        message3.isMessageByCurrentUser = false
        message3.timeCreated = "11:54 am"

        val message4 = CommentModel()
        message4.memberName = "Kunal Sale"
        message4.messageBody = "Hello everyone"
        message4.isMessageByCurrentUser = true
        message4.timeCreated = "11:55 am"

        messages.add(message1)
        messages.add(message2)
        messages.add(message4)
        messages.add(message3)
        return messages
    }
}