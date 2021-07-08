package com.example.investors.firestore

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.investors.Constants
import com.example.investors.login.Login
import com.example.investors.login.SignUp
import com.example.investors.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FirestoreClass {

    private val mFirestore = FirebaseFirestore.getInstance()

    fun registerUser(activity:SignUp, userInfo : User){
        //the users is a collection name. if collection is already created it wont create another one
        mFirestore.collection(Constants.USERS)
            //Document ID for user fields. Here the document it is the UserId
            .document(userInfo.id)
            //Here the userInfo are Field and the setOption is set to merge
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {

                // call the function register successful
                activity.registerSuccessful()
            }
            .addOnFailureListener {
                Log.d("SignUp","failed to register u user ${it.message}")

            }


    }

    fun getCurrentUserId(): String {
        //instance of current user using firebase
       val currentUser = FirebaseAuth.getInstance().currentUser

        // a variable to assign currentUserID if it is not null
        var currentUserID = ""
        if (currentUser!= null) {
            currentUserID = currentUser.uid
        }

       return currentUserID
    }

    fun getUserDetails(activity: Activity){
        //pass the collection name were we get the data
        mFirestore.collection(Constants.USERS)
        // document id to get the fields
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener {  document ->
                Log.i(activity.javaClass.simpleName,document.toString())

                // here we have received document snapshot which is converted into the User Data Model Object
                val user = document.toObject(User::class.java)

                val sharedPreferences =
                    activity.getSharedPreferences(
                        Constants.PREFERENCES,
                        Context.MODE_PRIVATE
                    )

                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                // storing in key value
                if (user != null) {
                    editor.putString(
                        Constants.LOGGED_IN_USERNAME,
                        "${user.firstname} ${user.surname}"
                    )
                    editor.apply()
                }


                when (activity) {
                    is Login -> {
                        if (user != null) {
                            activity.userLoggedInSuccess(user)
                        }


                    }
                    is SignUp -> {
                        if (user!= null){
                            activity.Details(user)
                        }
                    }
                }
            }


    }



}