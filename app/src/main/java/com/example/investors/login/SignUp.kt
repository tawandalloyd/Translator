package com.example.investors.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.investors.Home
import com.example.investors.R
import com.example.investors.firestore.FirestoreClass
import com.example.investors.models.User
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign__up.*

class SignUp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign__up)


        auth = Firebase.auth

        have_account.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        cirSignUpButton.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "email and password can't be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Log.d("SignUp","Email is "+email)
            Log.d("SignUp","password: $password")
            //authenticating with firebase email and password

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {

                            task ->
                        if (task.isSuccessful) {

                            val firebaseUser : FirebaseUser = task.result!!.user!!

                            val user = User(
                                firebaseUser.uid,
                                editTextName.text.toString().trim(),
                                editTextsurname.text.toString().trim(),
                                editTextEmail.text.toString().trim(),
                            )
                             FirestoreClass().registerUser(this,user)

                            Toast.makeText(
                                this,
                                "user registered",
                                Toast.LENGTH_SHORT
                            ).show()

                            val intent = Intent( this, Home::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent. FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id ", firebaseUser.uid)
                            intent.putExtra("email", email)
                            startActivity(intent)
                            finish()
                        } else{

                            Toast.makeText(
                                this@SignUp,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
                .addOnFailureListener {
                    Log.d("SignUp","failed to create user ${it.message}")
                }
        }

    }

    fun registerSuccessful(){

        Toast.makeText(this@SignUp, "you are registered successfully", Toast.LENGTH_SHORT).show()
    }


}