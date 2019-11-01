package com.chatapp.chats.models

class ForumModel {
    var id: Int = 0
    var forumName: String? = null
    var createrName: String? = null
    var creationTime: Long = 0
    var description: String? = null
    var comments: List<MessageModel>? = null
}