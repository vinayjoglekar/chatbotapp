package com.chatapp.authentication

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.chatapp.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.mongodb.stitch.android.core.auth.providers.userpassword.UserPasswordAuthProviderClient
import kotlinx.android.synthetic.main.sign_up_layout.*


class RegistrationFragment : Fragment() {

    lateinit var viewModel: AuthenticationViewModel
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
            val password = edtPassword.text.toString()

            if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
                Toast.makeText(context, "Please enter the details", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val emailPassClient = viewModel.client.auth.getProviderClient(
                    UserPasswordAuthProviderClient.factory
            )
            emailPassClient.registerWithEmail(userName,password)
                    .addOnCompleteListener(object : OnCompleteListener<Void> {
                        override fun onComplete(task: Task<Void>) {
                            if (task.isSuccessful()) {
                                Log.d("stitch", "Successfully sent account confirmation email");
                            } else {
                                Log.e("stitch", "Error registering new user:", task.getException());
                            }
                        }
                    })

//            viewModel.signUp(userName, password)
//                .addOnCompleteListener(activity!!) { task ->
//                    if (task.isSuccessful) {
//                        // Sign in success, update UI with the signed-in user's information
//                        val user = viewModel.auth.currentUser
//                        // Create a new user with a first and last name
//                        val currentUser = hashMapOf(
//                            "emailId" to userName,
//                            "password" to password,
//                            "userID" to user?.uid
//                        )
//                        viewModel.preferencesEditor?.putString("EMAIL_ID", userName)
//                        viewModel.preferencesEditor?.putBoolean("IS_LOGGEDIN", true)
//                        viewModel.preferencesEditor?.apply()
//                        db.collection("users")
//                            .add(currentUser)
//                            .addOnSuccessListener {
//                                val intent = Intent(activity!!, MainActivity::class.java)
//                                startActivity(intent)
//                            }.addOnFailureListener {
//                            }
//                    } else {
//                        viewModel.preferencesEditor?.putString("EMAIL_ID", "")
//                        viewModel.preferencesEditor?.putBoolean("IS_LOGGEDIN", false)
//                        viewModel.preferencesEditor?.apply()
//                        // If sign in fails, display a message to the user.
//                        Toast.makeText(
//                            context, "Authentication failed.",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                }
        }
    }
}