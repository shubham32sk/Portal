package com.example.portal

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.portal.R
import com.example.portal.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class sign_in : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    private var db = Firebase.firestore
    lateinit var Ename: String
    lateinit var Epassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val id: TextView = findViewById(R.id.tvMail)
        val pass: TextView = findViewById(R.id.tvPassword)
        val submit: Button = findViewById(R.id.signIn)

        firebaseAuth = FirebaseAuth.getInstance()

        submit.setOnClickListener {
            val email = id.text.toString()
            val passwd = pass.text.toString()

            if (email.isNotEmpty() && passwd.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, passwd).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, SecondActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Please fill empty fields!!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } else {
                Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}




//        db.collection("user").document(firebaseAuth.toString())
//            .get()
//            .addOnSuccessListener {
//            if(it != null) {
//                    val name = it.data?.get("lib_id")?.toString()
//                    val password = it.data?.get("password")?.toString()
//                    Ename = name.toString()
//                    Epassword = password.toString()
//
//              }
//
//        }
//            .addOnFailureListener{
//                Toast.makeText(this,"Failed!",Toast.LENGTH_SHORT).show()
//        }

//        submit.setOnClickListener {
//            val email = id.text.toString()
//            val passwd = pass.text.toString()
//
//            if(email.isNotEmpty() && passwd.isNotEmpty()) {
//                try {
//                   if(email==Ename && passwd==Epassword){
//                       val intent = Intent(this, SecondActivity::class.java)
//                       startActivity(intent)
//                   }else{
//                       Toast.makeText(this,"Email or password is incorrect!!",Toast.LENGTH_SHORT).show()
//                   }
//                }catch (e : Exception){
//                    Log.d("emailInput", "onCreate: ${e.message}")
//                }

//                    else{
//                        Toast.makeText(this, "Please fill empty fields!!", Toast.LENGTH_SHORT).show()
//                    }








