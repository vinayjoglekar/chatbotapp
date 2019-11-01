package com.chatapp.authentication

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class AuthenticationViewModel: ViewModel() {

    lateinit var auth: FirebaseAuth

    fun setFirebaseAuth(auth: FirebaseAuth){
        this.auth = auth
    }

    fun signUp(emailID: String, password: String): Task<AuthResult> =  auth.createUserWithEmailAndPassword(emailID, password)

    fun signIn(emailID: String, password: String): Task<AuthResult> = auth.signInWithEmailAndPassword(emailID, password)
}