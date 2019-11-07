package com.chatapp

import android.app.Activity
import android.content.Intent
import android.content.pm.ResolveInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import kotlinx.android.synthetic.main.activity_main.*
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.speech_to_text.*
import java.util.*


class SpeechToTextActivity : AppCompatActivity() {


    val TAG=this.javaClass.simpleName
    val REQUEST_CODE=1234
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.speech_to_text)
        Log.d(TAG, Locale.getDefault().getDisplayLanguage().toString())
        var pm=packageManager
        val activities:List<ResolveInfo> = pm.queryIntentActivities(Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH),0)
        if(activities.size==0){
            speakButton.isEnabled=false
            speakButton.text="Recognizer not present"

            editText1.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    // TODO Auto-generated method stub
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    // TODO Auto-generated method stub
                }

                override fun afterTextChanged(s: Editable) {
                    // TODO Auto-generated method stub
                    speakButton.setEnabled(false)
                }
            })
        }
    }

    /**
     * Handle the action of the button being clicked
     */
    fun speakButtonClicked(v: View) {
        startVoiceRecognitionActivity()
    }

    /**
     * Fire an intent to start the voice recognition activity.
     */
    private fun startVoiceRecognitionActivity() {

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "gu-IN") // Gujrathi language set
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Voice searching...")
        startActivityForResult(intent, REQUEST_CODE)
    }

    /**
     * Handle the results from the voice recognition activity.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // Populate the wordsList with the String values the recognition engine thought it heard
            val matches = data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            if (!matches!!.isEmpty()) {
                val Query = matches[0]
                editText1.setText(Query)
//                speakButton.setEnabled(false)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun translateToEnglish(inputString:String){


    }
}
