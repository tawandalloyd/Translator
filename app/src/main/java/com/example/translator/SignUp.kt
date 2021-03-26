package com.example.translator

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.translator.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.sign_up.*


class SignUp : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var fireStore:FirebaseFirestore;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth= FirebaseAuth.getInstance();
        fireStore= FirebaseFirestore.getInstance();
        val name=view?.findViewById<EditText>(R.id.editTextName);
        val surname=view?.findViewById<EditText>(R.id.editTextsurname);
        val email=view?.findViewById<EditText>(R.id.editTextEmail);
        val password=view?.findViewById<EditText>(R.id.editTextPassword);
        val phonenumber=view?.findViewById<EditText>(R.id.editTextMobile)
        val confirmpassword=view?.findViewById<EditText>(R.id.editTextConfirmPassword);

        val signup=view?.findViewById<Button>(R.id.cirLoginButton);

        signup?.setOnClickListener {
            if(checkInputs(name?.text.toString(),surname?.text.toString(),phonenumber?.toString().toString(),email?.text.toString() ,password?.text.toString(),confirmpassword?.text.toString())){
              if(doPasswordsmatch(password?.text.toString(),confirmpassword?.text.toString())){
                  signUp(email?.text.toString(),password?.text.toString());
              }else{
                  Toast.makeText(context,"Passwords do not match",Toast.LENGTH_SHORT).show();
              }


            }else{
                Toast.makeText(context,"Make sure you fill all fields",Toast.LENGTH_SHORT).show();
            }


        }





    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.sign_up, container, false)
    }



    fun doPasswordsmatch(s1:String ,s2:String): Boolean {
          return s1.equals(s2);
    }

    fun checkInputs(name:String ,surname:String,phonenumber:String,email:String,password: String,confirmpassword:String ):Boolean{
        if(name.equals("")||surname.equals("")||phonenumber.equals("")||email.equals("")||password.equals("")||confirmpassword.equals("")){
            Toast.makeText(context,"Please make sure all fields are filled",Toast.LENGTH_SHORT).show();
            return  false
        }
        return true;
    }

    fun signUp(email:String ,password:String ){
       auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener (){
           task->
           if(task.isSuccessful){
               verifyNewUser();
               addUser();

           }else{
              Toast.makeText(context,"SignUp Failed",Toast.LENGTH_SHORT).show();
           }
       }
    }



    fun verifyNewUser(){
        val currentUser=auth.currentUser;
        currentUser?.sendEmailVerification()?.addOnCompleteListener(){ task->
            if(task.isSuccessful){
                Toast.makeText(context,"Verification email sent check your email",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context,"Failed to send verification email sent",Toast.LENGTH_SHORT).show();
            }
        }
    }


    fun redirectScreen(){
        val  intent =Intent(context,Home::class.java);
        startActivity(intent);

    }


    fun addUser(){

        val userId=auth.currentUser.uid;
        val userModel=User();
        userModel.userid=userId;
        userModel.firstname=editTextsurname.text.toString();
        userModel.lastname=editTextsurname.text.toString();
        userModel.phonenumber=editTextMobile.text.toString();

        fireStore.collection("Users").document(auth.currentUser.uid).set(userModel);
        auth.signOut();
        redirectScreen();




    }
}