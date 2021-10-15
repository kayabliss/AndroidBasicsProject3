package com.ana.androidbasicsproject3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val dataFromMainActivity = intent.getStringExtra(INTENT_DATA_NAME)
        Log.i("ana", "Data from main activity" + dataFromMainActivity)
        val secEditText = findViewById<EditText>(R.id.second_editTextView)
        val secBtn = findViewById<Button>(R.id.submit_button)

        val bundle: Bundle? = intent.extras
        val input = bundle!!.getString(INTENT_DATA_NAME)

        secEditText.setText(dataFromMainActivity)

        findViewById<Button>(R.id.submit_button).setOnClickListener {
            val newUserInput = findViewById<EditText>(R.id.second_editTextView).text.toString()
            //Grab the text in the editText Field
            //Store the editText String in an intent
            //close the activity
            val i = Intent()
            i.putExtra(INTENT_DATA_NAME, newUserInput)

            //Pass the intent back to MainActivity
            setResult(RESULT_OK, i)

            //Exit/Finish this activity
            finish()


        }


    }
}