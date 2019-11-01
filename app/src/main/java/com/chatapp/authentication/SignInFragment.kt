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
import kotlinx.android.synthetic.main.sign_in_layout.*

class SignInFragment : Fragment() {

    lateinit var viewModel: AuthenticationViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.setTitle("Log In")
        viewModel = ViewModelProviders.of(activity!!).get(AuthenticationViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.sign_in_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lblSignUp.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, RegistrationFragment())?.commit()
        }

        btnLogin.setOnClickListener {
            val userName = edtUserName.text.toString()
            val password = edtPassword.text.toString()
            if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)){
                Toast.makeText(context, "Please enter the details", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            viewModel.signIn(userName, password)
                .addOnCompleteListener(activity!!) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("SignInFragment", "signInWithEmail:success")
                        val user = viewModel.auth.currentUser
                        val intent = Intent(activity!!, MainActivity::class.java)
                        startActivity(intent)
                        //updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("SignInFragment", "signInWithEmail:failure", task.exception)
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