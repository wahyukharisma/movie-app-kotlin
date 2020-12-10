package com.dayplug.movieticket.view.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dayplug.movieticket.databinding.ActivitySignUpBinding
import com.dayplug.movieticket.service.model.User
import com.dayplug.movieticket.view.HomeActivity
import com.dayplug.movieticket.viewmodel.SignUpViewModel

class SignUpActivity : AppCompatActivity() {
    private lateinit var _viewModel : SignUpViewModel
    private lateinit var _binding : ActivitySignUpBinding

    private lateinit var _username : String
    private lateinit var _password : String
    private lateinit var _name : String
    private lateinit var _email : String
    private lateinit var user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)

        _viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        with(_binding){

            ivBack.setOnClickListener {
                finish()
            }

            btnNext.setOnClickListener {
                _username = tieUsername.text.toString()
                _password = tiePassowrd.text.toString()
                _name = tieName.text.toString()
                _email = tieEmail.text.toString()

                if(_username == ""){
                    tieUsername.error = "Masukkan username"
                    tieUsername.requestFocus()
                }else if(_password == ""){
                    tiePassowrd.error = "Masukkan password"
                    tiePassowrd.requestFocus()
                }else if(_name == ""){
                    tieName.error = "Masukkan nama"
                    tieName.requestFocus()
                }else if(_email == ""){
                    tieEmail.error = "Masukkan email"
                    tieEmail.requestFocus()
                }else{
                    _viewModel.pushUser(_username, _password, _name, _email)
                }
            }

            _viewModel.user.observe(this@SignUpActivity, Observer {
                startActivity(Intent(this@SignUpActivity, SignUpPhotoActivity::class.java)
                    .putExtra("name", it.username))
            })
        }
    }
}