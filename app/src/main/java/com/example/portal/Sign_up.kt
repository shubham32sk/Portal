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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class Sign_up : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var firebaseAuth: FirebaseAuth
    private var db= Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val id:TextView=findViewById(R.id.emailId)
        val pass:TextView=findViewById(R.id.password)
        val cofm_pass:TextView=findViewById((R.id.confirm_password))
        val submit:Button=findViewById(R.id.signUp)

        firebaseAuth = FirebaseAuth.getInstance()

        submit.setOnClickListener{
            var mail_id=id.text.toString()
            var password=pass.text.toString()
            val conf_pass=cofm_pass.text.toString()

            var user = hashMapOf(
                "lib_id" to mail_id,
                "password" to password,
                "confirm_password" to conf_pass
            )
            if(mail_id.isNotEmpty()  && password.isNotEmpty() && conf_pass.isNotEmpty()) {

                if (password == conf_pass) {
                    db.collection("user").add(user)
                        //.document("firebaseAuth").set(user)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Succesfully signed up!!", Toast.LENGTH_SHORT)
                                .show()
                            id.text = ""
                            pass.text = ""
                            Log.d(TAG, "DocumentSnapshot added with ID: ${firebaseAuth}")
                            val intent = Intent(this, sign_in::class.java)
                            startActivity(intent)

                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
                            Log.w(TAG, "Error adding document")
                        }
                    submit.postDelayed({
                    }, 3000)
                } else {
                    Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this, "Please fill empty fields", Toast.LENGTH_SHORT).show()
            }
        }



    }
}