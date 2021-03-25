package com.example.translator

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth


class SignIn : Fragment() {
    private lateinit var auth:FirebaseAuth;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.sign_in, container, false);
        auth= FirebaseAuth.getInstance();
        val email=view?.findViewById<EditText>(R.id.editTextEmail);
        val password=view?.findViewById<EditText>(R.id.editTextPassword);
        val signInBtn=view?.findViewById<Button>(R.id.signinbutton);



        signInBtn?.setOnClickListener {
            val emailText:String=email?.text.toString();
            val passwordText:String=password?.text.toString();

            signIn(emailText,passwordText);

        }






    }



    fun  signIn(email:String ?,password:String){

       auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(){  task->

           if(task.isSuccessful){
               val intent= Intent(context,MainActivity::class.java);
               Toast.makeText(context,"Welcome",Toast.LENGTH_SHORT).show();

           }else{
               Toast.makeText(context,"Sign In Failed",Toast.LENGTH_SHORT).show();
           }

       }




    }




}

