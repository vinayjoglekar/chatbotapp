package com.chatapp.authentication

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.mongodb.stitch.android.core.StitchAppClient


class AuthenticationViewModel: ViewModel() {

    val sharedPrefFile = "com..chatapp"
    var mPreferences: SharedPreferences? = null
    var preferencesEditor: SharedPreferences.Editor? = null

    lateinit var auth: FirebaseAuth
    lateinit var client: StitchAppClient

    fun setFirebaseAuth(auth: FirebaseAuth){
        this.auth = auth
    }

    fun signUp(emailID: String, password: String): Task<AuthResult> =  auth.createUserWithEmailAndPassword(emailID, password)

    fun signIn(emailID: String, password: String): Task<AuthResult> = auth.signInWithEmailAndPassword(emailID, password)
}