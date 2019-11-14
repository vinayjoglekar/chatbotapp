package com.chatapp.chats.models

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.chatapp.BR
import java.io.Serializable

class ForumModel : BaseObservable(), Serializable {

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
    var forumTopic: String = "Others"
        set(value) {
            field = value
            notifyPropertyChanged(BR.forumTopic)
        }

    @get:Bindable
    var formattedDate: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.formattedDate)
        }

    @get:Bindable
    var description: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.description)
        }

    var comments: List<CommentModel>? = null
}