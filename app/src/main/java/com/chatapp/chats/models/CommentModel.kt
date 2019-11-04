package com.chatapp.chats.models

class CommentModel {
    var messageId = 0
    lateinit var name: String
    lateinit var body: String
    var messageTime: Long = 0
}