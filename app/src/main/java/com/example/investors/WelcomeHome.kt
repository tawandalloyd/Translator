package com.example.investors

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.investors.login.Login
import com.example.investors.login.SignUp
import com.example.investors.nav_drawer.Welcome
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_welcome_home.*


class WelcomeHome : AppCompatActivity() {

    private companion object {
        private const val TAG = "WelcomeHome"
        private const val RC_GOOGLE_SIGN_IN = 6209
    }

    private lateinit var auth: FirebaseAuth
// ...
// Initialize Firebase Auth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_home)

        auth = Firebase.auth

        // Configure Google Sign In
       // val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
         //   .requestIdToken(getString(R.string.default_web_client_id))
            //.requestEmail()
          //  .build()

         // val client= GoogleSignIn.getClient(this, gso)



        //googlesignin.setOnClickListener {
      //      val signInIntent = client.signInIntent
       //     startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN)
        //}
        signup_button.setOnClickListener {
            val intent= Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        signin.setOnClickListener {
            val intent= Intent(this, Login::class.java)
            startActivity(intent)

        }
    }
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)

    }

    private fun updateUI(user: FirebaseUser?) {
        //navigate to welcome activity
        if (user == null){
            return
        }
        startActivity(Intent(this,Welcome::class.java))
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
    }

}