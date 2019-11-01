package com.chatapp.chats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chatapp.R

class ChatRecyclerAdapter(val messageList: ArrayList<MessageModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    val SENDER_TYPE = 0
    val RECEIVER_TYPE = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder =
                if(viewType == SENDER_TYPE){
                    SenderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.sender_row_layout, parent,false))
                }
        else{
                    ReceiverViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.receiver_row_layout, parent,false))
                }
        return viewHolder
    }

    override fun getItemCount(): Int = messageList.size

    override fun getItemViewType(position: Int): Int {
         super.getItemViewType(position)
        return if(messageList[position].isMessageByCurrentUser){
            SENDER_TYPE
        }
        else{
            RECEIVER_TYPE
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = messageList[holder.adapterPosition]
        if(holder is ReceiverViewHolder){
            (holder as ReceiverViewHolder).userName.text = model.memberName
            (holder as ReceiverViewHolder).tvMessage.text = model.messageBody
        }
        else{
            (holder as SenderViewHolder).tvMessage.text = model.messageBody
        }
    }

    class SenderViewHolder(view: View): RecyclerView.ViewHolder(view){
        var tvMessage: TextView

        init {
            tvMessage = view.findViewById(R.id.tvMessageBody)
        }
    }

    class ReceiverViewHolder(view: View): RecyclerView.ViewHolder(view){
        var userName: TextView
        var tvMessage: TextView

        init {
            userName = view.findViewById(R.id.tvMemberName)
            tvMessage = view.findViewById(R.id.tvMessageBody)
        }
    }
}