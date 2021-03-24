package com.example.translator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.translator.databinding.FragmentHomeBinding


class Home : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment



        val binding : FragmentHomeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)

        binding.signin.setOnClickListener{ view: View ->
            Navigation.findNavController(view).navigate(R.id.action_home2_to_signIn2)
        }
        binding.signupButton.setOnClickListener{view: View ->
            Navigation.findNavController(view).navigate(R.id.action_home2_to_signUp)
        }
       return binding.root
    }



}