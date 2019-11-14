package com.chatapp.chats

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.chatapp.R
import com.chatapp.chats.models.ForumModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.forum_creation_layout.*

class ForumCreationFragment : Fragment(), AdapterView.OnItemSelectedListener {

    var selectedTopic: String = ""
    val sharedPrefFile = "com..chatapp"
    var mPreferences: SharedPreferences? = null
    var preferencesEditor: SharedPreferences.Editor? = null
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPreferences = activity?.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        preferencesEditor = mPreferences!!.edit()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.forum_creation_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter = ArrayAdapter.createFromResource(
            context!!,
            R.array.topic_suggestion,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerSuggestion.adapter = adapter
        }
        spinnerSuggestion.onItemSelectedListener = this

        btnCreateForum.setOnClickListener {
            createForum()
        }
    }

    private fun createForum() {
        val forumName = edtForum.text.toString()
        val forumDescription = edtDescription.text.toString()

        if (TextUtils.isEmpty(forumName)) {
            Toast.makeText(context, "Please enter forum name", Toast.LENGTH_LONG).show()
            return
        }

        if (TextUtils.isEmpty(selectedTopic)) {
            Toast.makeText(context, "Please select the topic", Toast.LENGTH_LONG).show()
            return
        }

        if (TextUtils.isEmpty(forumDescription)) {
            Toast.makeText(context, "Please enter forum description", Toast.LENGTH_LONG).show()
            return
        }

        val forumModel = ForumModel()
        forumModel.forumName = forumName
        forumModel.description = forumDescription
        forumModel.createrName = mPreferences?.getString("EMAIL_ID", "")!!
        forumModel.forumTopic = selectedTopic
        forumModel.creationTime = System.currentTimeMillis()

        db.collection("forums")
            .add(forumModel)
            .addOnSuccessListener {
                Toast.makeText(context, "Forum created", Toast.LENGTH_LONG).show()
                activity?.supportFragmentManager?.popBackStack()
            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    context,
                    "Something went wrong while creating forum!",
                    Toast.LENGTH_LONG
                ).show()
            }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        selectedTopic = parent?.getItemAtPosition(position) as String
    }
}