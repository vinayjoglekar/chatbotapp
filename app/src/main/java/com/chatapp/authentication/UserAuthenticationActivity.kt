package com.chatapp.authentication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.chatapp.MainActivity
import com.chatapp.R
import com.google.firebase.auth.FirebaseAuth
import com.mongodb.stitch.android.core.Stitch

class UserAuthenticationActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var viewModel: AuthenticationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_auth_layout)
        Stitch.initializeDefaultAppClient(
                resources.getString(R.string.mongo_cluster_id)
        )
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        viewModel = ViewModelProviders.of(this).get(AuthenticationViewModel::class.java)
        viewModel.mPreferences = getSharedPreferences(viewModel.sharedPrefFile, Context.MODE_PRIVATE)
        viewModel.preferencesEditor = viewModel.mPreferences!!.edit()
        viewModel.preferencesEditor?.clear()
        viewModel.setFirebaseAuth(auth)

        viewModel.client = Stitch.getDefaultAppClient()
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser == null) {
            supportFragmentManager.beginTransaction().add(R.id.container, SignInFragment()).commit()
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}