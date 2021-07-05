package com.example.investors.firestore

import android.util.Log
import com.example.investors.login.SignUp
import com.example.investors.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FirestoreClass {

    private val mFirestore = FirebaseFirestore.getInstance()

    fun registerUser(activity:SignUp, userInfo : User){
        //the users is a collection name. if collection is already created it wont create another one
        mFirestore.collection("users")
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


}