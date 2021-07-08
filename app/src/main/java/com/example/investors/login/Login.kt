package com.example.investors.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.investors.R
import com.example.investors.firestore.FirestoreClass
import com.example.investors.models.User
import com.example.investors.quizz.QuizzHome
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.editTextEmail
import kotlinx.android.synthetic.main.activity_login.editTextPassword
import kotlinx.android.synthetic.main.activity_sign__up.*

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth
        no_account.setOnClickListener {
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }
        signinbutton.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "email and password can't be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Log.d("SignUp","Email is "+email)
            Log.d("SignUp","password: $password")
            //authenticating with firebase email and password

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {

                        task ->
                    if (task.isSuccessful) {
                       // getting the user details
                        FirestoreClass().getUserDetails(this@Login)

                        Toast.makeText(
                            this,
                            "user logged in",
                            Toast.LENGTH_SHORT
                        ).show()
                        // passing user details to another activity using intents
                       // val intent = Intent( this, Home::class.java)
                        //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent. FLAG_ACTIVITY_CLEAR_TASK
                        //intent.putExtra("user_id ",FirebaseAuth.getInstance().currentUser!!.uid)
                        //intent.putExtra("email", email)
                        //startActivity(intent)
                        //finish()
                    } else{

                        Toast.makeText(
                            this@Login,
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

    fun userLoggedInSuccess(user: User){
        // print user details in the log as of now
        Log.i("first Name", user.firstname)
        Log.i("surname", user.surname)
        Log.i("Email", user.email)

        val intent= Intent(this, QuizzHome::class.java)
        startActivity(intent)


       /* if (user.profileCompleted== 0){
            val intent= Intent(this, Profile::class.java)
            intent.putExtra(Constants.USER_DETAILS, user)
            startActivity(intent)
        } else {

            startActivity(Intent(this@Login, Home::class.java))
        }*/

    }

}