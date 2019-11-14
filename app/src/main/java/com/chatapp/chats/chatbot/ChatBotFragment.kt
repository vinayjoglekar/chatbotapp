//package com.chatapp.chats.chatbot
//
//
//import android.os.AsyncTask
//import android.os.Bundle
//import android.util.Log
//import android.view.KeyEvent
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ScrollView
//import android.widget.Toast
//import androidx.appcompat.widget.AppCompatTextView
//import androidx.constraintlayout.widget.ConstraintLayout
//import androidx.fragment.app.Fragment
//import com.google.api.gax.core.FixedCredentialsProvider
//import com.google.auth.oauth2.GoogleCredentials
//import com.google.auth.oauth2.ServiceAccountCredentials
//import com.google.cloud.dialogflow.v2beta1.*
//import kotlinx.android.synthetic.main.fragment_chatbot.*
//import java.util.*
//
//
//
//
//class ChatBotFragment : Fragment() {
//
//    private lateinit var session: SessionName
//    private lateinit var sessionsClient: SessionsClient
//
//    private val USER = 10001
//    private val BOT = 10002
//
//    interface FragmentCallback {
//        fun onTaskDone(response: DetectIntentResponse?)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        super.onCreateView(inflater, container, savedInstanceState)
//        return inflater.inflate(com.chatapp.R.layout.fragment_chatbot, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        chatScrollView.post { chatScrollView.fullScroll(ScrollView.FOCUS_DOWN) }
//        sendBtn.setOnClickListener { sendMessage() }
//
//        queryEditText.setOnKeyListener { _, keyCode, event ->
//            if (event.action == KeyEvent.ACTION_DOWN) {
//                when (keyCode) {
//                    KeyEvent.KEYCODE_DPAD_CENTER, KeyEvent.KEYCODE_ENTER -> {
//                        sendMessage()
//                        true
//                    }
//                    else -> {
//                    }
//                }
//            }
//            false
//        }
//
//        initChatBot()
//    }
//
//    private fun initChatBot() {
//        try {
//            val stream = resources.openRawResource(com.chatapp.R.raw.test_agent_credentials)
//            val credentials = GoogleCredentials.fromStream(stream)
//            val projectId = (credentials as ServiceAccountCredentials).projectId
//
//            val settingsBuilder = SessionsSettings.newBuilder()
//            val sessionsSettings =
//                settingsBuilder.setCredentialsProvider(FixedCredentialsProvider.create(credentials))
//                    .build()
//            sessionsClient = SessionsClient.create(sessionsSettings)
//            session = SessionName.of(projectId, UUID.randomUUID().toString()) // uuid = UUID.randomUUID().toString()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//
//
//    }
//
//    private fun sendMessage() {
//        val msg = queryEditText.text.toString()
//        if (msg.trim { it <= ' ' }.isEmpty()) {
//            Toast.makeText(context, "Please enter your query!", Toast.LENGTH_LONG).show()
//        } else {
//            showTextView(msg, USER)
//            queryEditText.setText("")
//
//            val queryInput = QueryInput.newBuilder()
//                .setText(TextInput.newBuilder().setText(msg).setLanguageCode("en-US")).build()
//
//            RequestTask(object : FragmentCallback {
//
//                override fun onTaskDone(response: DetectIntentResponse?) {
//                    callback(response)
//                }
//            }, session, sessionsClient, queryInput).execute()
//        }
//    }
//
//
//    private fun getUserLayout(): ConstraintLayout {
//        val inflater = LayoutInflater.from(context)
//        return inflater.inflate(com.chatapp.R.layout.sender_row_layout, null) as ConstraintLayout
//    }
//
//    private fun getBotLayout(): ConstraintLayout {
//        val inflater = LayoutInflater.from(context)
//        return inflater.inflate(com.chatapp.R.layout.receiver_row_layout, null) as ConstraintLayout
//    }
//
//    fun callback(response: DetectIntentResponse?) {
//        response?.let {
//            val botReply = response.queryResult.fulfillmentText
//            Log.d("callback", "V2 Bot Reply: $botReply")
//            showTextView(botReply, BOT)
//        }
//    }
//
//    private fun showTextView(message: String, type: Int) {
//        val layout: ConstraintLayout = when (type) {
//            USER -> getUserLayout()
//            BOT -> getBotLayout()
//            else -> getBotLayout()
//        }
//        layout.isFocusableInTouchMode = true
//        chatLayout.addView(layout) // move focus to text view to automatically make it scroll up if softfocus
//        val tv = layout.findViewById<AppCompatTextView>(com.chatapp.R.id.tvMessageBody)
//        tv.text = message
//        layout.requestFocus()
//        queryEditText.requestFocus() // change focus back to edit text to continue typing
//    }
//
//
//    class RequestTask internal constructor(
//        private val mFragmentCallback: FragmentCallback,
//        private val session: SessionName,
//        private val sessionsClient: SessionsClient,
//        private val queryInput: QueryInput
//    ) :
//        AsyncTask<Void, Void, DetectIntentResponse>() {
//
//        override fun doInBackground(vararg voids: Void): DetectIntentResponse? {
//            try {
//                val detectIntentRequest = DetectIntentRequest.newBuilder()
//                    .setSession(session.toString())
//                    .setQueryInput(queryInput)
//                    .build()
//                return sessionsClient.detectIntent(detectIntentRequest)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//
//            return null
//        }
//
//        override fun onPostExecute(response: DetectIntentResponse) {
//            mFragmentCallback.onTaskDone(response)
//        }
//    }
//
//}
