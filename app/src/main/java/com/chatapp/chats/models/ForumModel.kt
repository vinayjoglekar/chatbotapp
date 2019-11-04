package com.chatapp.chats.models

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.chatapp.BR

class ForumModel: BaseObservable() {

    var id: Int = 0

    @get:Bindable
    var forumName: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.forumName)
        }

    @get:Bindable
    var createrName: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.createrName)
        }

    @get:Bindable
    var creationTime: Long = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.creationTime)
        }

    @get:Bindable
    var description: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.description)
        }

    var comments: List<CommentModel>? = null
}