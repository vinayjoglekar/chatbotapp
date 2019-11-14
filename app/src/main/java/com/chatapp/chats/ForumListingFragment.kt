package com.chatapp.chats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chatapp.ForumRecyclerAdapter
import com.chatapp.R
import com.chatapp.chats.models.ForumModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.forum_listing_layout.*

class ForumListingFragment : Fragment() {

    val db = FirebaseFirestore.getInstance()
    lateinit var adapter: ForumRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ForumRecyclerAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.forum_listing_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerForums.adapter = adapter

        db.collection("forums").get().addOnSuccessListener { result ->
            if(result.documents.size > 0) {
                val forums = ArrayList<ForumModel>()
                for (document in result.documents) {
                    forums.add(document.toObject(ForumModel::class.java)!!)
                }
                adapter.addForums(forums)
            }
        }.addOnCanceledListener {
        }
        fab.setOnClickListener {
//            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, ChatBotFragment())
//                ?.addToBackStack(null)
//                ?.commit()
            startActivity(context?.packageManager?.getLaunchIntentForPackage("com.tcp.dialogflowchat"))

        }
    }
}