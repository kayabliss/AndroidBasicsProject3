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
        secEditText.setText(dataFromMainActivity)

        //val secBtn = findViewById<Button>(R.id.submit_button)

       // val bundle: Bundle? = intent.extras
       // val input = bundle!!.getString(INTENT_DATA_NAME)



        findViewById<Button>(R.id.submit_button).setOnClickListener {
            //Grab the text in the editText Field
            val newUserInput = findViewById<EditText>(R.id.second_editTextView).text.toString()

            //Store the editText String in an intent
            val i = Intent()
            i.putExtra(INTENT_DATA_NAME, newUserInput)

            //Pass the intent back to MainActivity
            setResult(RESULT_OK, i)

            //Exit/Finish this activity//close the activity
            finish()


        }


    }
}