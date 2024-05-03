package com.example.portal

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.portal.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sign_in: Button = findViewById(R.id.sign_in_button)
        val sign_up: Button = findViewById(R.id.sign_up_button)

        sign_in.setOnClickListener{signin()}
        sign_up.setOnClickListener{signup()}


    }
        private fun signup() {
            val intent = Intent(this, Sign_up::class.java)
            startActivity(intent)

        }

        private fun signin() {
            val intent = Intent(this, sign_in::class.java)
            startActivity(intent)

        }

}
