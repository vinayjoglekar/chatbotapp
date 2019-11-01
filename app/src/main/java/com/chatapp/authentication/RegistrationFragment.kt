package com.chatapp.authentication

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.chatapp.MainActivity
import com.chatapp.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.sign_up_layout.*

class RegistrationFragment : Fragment() {

    lateinit var viewModel: AuthenticationViewModel
    // Access a Cloud Firestore instance from your Activity
    val db = FirebaseFirestore.getInstance()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.setTitle("Register")
        viewModel = ViewModelProviders.of(activity!!).get(AuthenticationViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.sign_up_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lblLogin.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, SignInFragment())?.commit()
        }

        btnSignUp.setOnClickListener {
            val userName = edtUserName.text.toString()
            val nickname = edtNickName.text.toString()
            val password = edtPassword.text.toString()

            if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)){
                Toast.makeText(context, "Please enter the details",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            viewModel.signUp(userName, password)
                .addOnCompleteListener(activity!!) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("RegistrationFragment", "signUpWithEmail:success")
                        val user = viewModel.auth.currentUser
                        // Create a new user with a first and last name
                        val currentUser = hashMapOf(
                            "emailId" to userName,
                            "password" to password,
                            "userID" to user?.uid
                        )
                        db.collection("users")
                            .add(currentUser)
                            .addOnSuccessListener {
                                val intent = Intent(activity!!, MainActivity::class.java)
                                startActivity(intent)
                            }.addOnFailureListener {
                                Log.i("RegistrationFragment",it.message)
                            }
                        //updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("RegistrationFragment", "signUpWithEmail:failure", task.exception)
                        Toast.makeText(
                            context, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        //updateUI(null)
                    }
                }
        }
    }
}