package com.chatapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.chatapp.authentication.UserAuthenticationActivity
import com.chatapp.chats.ForumCreationFragment
import com.chatapp.chats.ForumListingFragment
import com.chatapp.chats.MessageModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportFragmentManager.beginTransaction().replace(R.id.container, ForumListingFragment()).commit()
        navigationBar.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.list -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, ForumListingFragment()).commit()
                }

                R.id.add -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, ForumCreationFragment()).commit()
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when (item.itemId) {
            R.id.log_out -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, UserAuthenticationActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        return true
    }
}