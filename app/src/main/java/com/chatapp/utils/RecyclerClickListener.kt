package com.chatapp.utils

import com.chatapp.chats.models.ForumModel

interface RecyclerClickListener {
    fun onRowClicked(model: ForumModel)
}