package com.example.investors.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.investors.R
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_sign__up.*

class SignUp : AppCompatActivity() {
    private lateinit var database: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign__up)

        database = FirebaseFirestore.getInstance()
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
                       if ( !it.isSuccessful) return@addOnCompleteListener

                        Log.d("SignUp","user signed up with uid: ${it.result?.user?.uid}")
                       saveToFirestore()

                    }
                .addOnFailureListener {
                    Log.d("SignUp","failed to create user ${it.message}")
                }
        }

    }

    private fun saveToFirestore(){
        Log.d("SignUp","register to firebase")
        var uname = editTextName.text.toString().trim()
        var surname = editTextsurname.text.toString().trim()
        var phoneNumber = editTextMobile.text.toString().trim()
        var email = editTextEmail.text.toString().trim()
        var password = editTextPassword.text.toString().trim()
        var confirmPassword = editTextConfirmPassword.text.toString().trim()

        if (uname.isNotEmpty()&& surname.isNotEmpty()&&phoneNumber.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){

            var mode= DatabaseModel(uname, surname, phoneNumber, email, password, confirmPassword)
            database.collection("Users").add(mode).addOnSuccessListener(object :OnSuccessListener<DocumentReference>{
                override fun onSuccess(p0: DocumentReference?) {
                    editTextName.setText("")
                    editTextsurname.setText("")
                    editTextMobile.setText("")
                    editTextEmail.setText("")
                    editTextPassword.setText("")
                    editTextConfirmPassword.setText("")
                }

            }).addOnFailureListener(object : OnFailureListener {
                override fun onFailure(p0: Exception) {
                    Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }


}