package com.chatapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.chatapp.authentication.UserAuthenticationActivity
import com.chatapp.chats.ForumListingFragment
import com.chatapp.chats.MessageModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportFragmentManager.beginTransaction().add(R.id.container, ForumListingFragment()).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.log_out -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, UserAuthenticationActivity::class.java)
                startActivity(intent)
                finish()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun getChatList(): ArrayList<MessageModel> {
        val messages = ArrayList<MessageModel>()
        val message1 = MessageModel()
        message1.memberName = "Vinay"
        message1.messageBody = "Hi, How are you all?"
        message1.isMessageByCurrentUser = false
        message1.timeCreated = "11:51 am"

        val message2 = MessageModel()
        message2.memberName = "Manjot"
        message2.messageBody = "Manjot this side"
        message2.isMessageByCurrentUser = false
        message2.timeCreated = "11:52 am"

        val message3 = MessageModel()
        message3.memberName = "Kunal Jadhav"
        message3.messageBody = "I prefer AC train"
        message3.isMessageByCurrentUser = false
        message3.timeCreated = "11:54 am"

        val message4 = MessageModel()
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