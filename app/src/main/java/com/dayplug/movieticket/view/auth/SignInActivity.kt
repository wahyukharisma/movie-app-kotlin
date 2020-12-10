package com.dayplug.movieticket.view.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dayplug.movieticket.view.HomeActivity
import com.dayplug.movieticket.databinding.ActivitySignInBinding
import com.dayplug.movieticket.utils.Preferences
import com.dayplug.movieticket.viewmodel.SignInViewModel

class SignInActivity : AppCompatActivity() {
    private lateinit var _binding : ActivitySignInBinding
    private lateinit var _viewModel : SignInViewModel
    private lateinit var _preferences: Preferences

    private lateinit var _username : String
    private lateinit var _password : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignInBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)

        _viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        _preferences = Preferences(this)

        if(_preferences.getValue("status").equals("1")){
            finishAffinity()
            startActivity(Intent(this, HomeActivity::class.java))
        }

        with(_binding){
            btnSign.setOnClickListener {
                _username = tieUsername.text.toString()
                _password = tiePassowrd.text.toString()

                if(_username == ""){
                    tieUsername.error = "Masukkan username"
                    tieUsername.requestFocus()
                }
                else if(_password == ""){
                    tiePassowrd.error = "Masukkan password"
                    tiePassowrd.requestFocus()
                }
                else{
                    _viewModel.pushLogin(_username,_password)
                }
            }

            btnRegister.setOnClickListener {
                startActivity(Intent(applicationContext, SignUpActivity::class.java))
            }

            _viewModel.user.observe(this@SignInActivity, Observer {
                if(it.password == _password){

                    _preferences.setValues("nama", it.nama.toString())
                    _preferences.setValues("username", it.username.toString())
                    _preferences.setValues("url", it.url.toString())
                    _preferences.setValues("email", it.email.toString())
                    _preferences.setValues("saldo", it.saldo.toString())
                    _preferences.setValues("status", "1")

                    startActivity(Intent(applicationContext, HomeActivity::class.java))
                }else{
                    tiePassowrd.error = "Password tidak sesuai"
                    tiePassowrd.requestFocus()
                }
            })
        }
    }
}