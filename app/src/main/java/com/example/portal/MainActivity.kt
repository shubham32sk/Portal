package com.example.portal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.portal.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var firebaseAuth: FirebaseAuth
    private var db= Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val id:TextView=findViewById(R.id.library_id)
        val pass:TextView=findViewById(R.id.password)
        val submit:Button=findViewById(R.id.login)

        firebaseAuth = FirebaseAuth.getInstance()

        submit.setOnClickListener{
            var lib_id=id.text.toString()
            var password=pass.text.toString()

            val intent = Intent(this, Login_activity::class.java)
            startActivity(intent)

            var user = hashMapOf(
                "lib_id" to lib_id,
                "password" to password
            )
            db.collection("user").document("firebaseAuth").set(user)
                .addOnSuccessListener {
                    Toast.makeText(this,"Succesfully Added!",Toast.LENGTH_SHORT).show()
                    id.text=""
                    pass.text=""

                }
                .addOnFailureListener{
                    Toast.makeText(this,"Failed!",Toast.LENGTH_SHORT).show()
                }
        }


    }
}