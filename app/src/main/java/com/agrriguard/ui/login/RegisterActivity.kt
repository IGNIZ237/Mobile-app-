package com.agrriguard.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.agrriguard.AgriGuardApp
import com.agrriguard.data.model.User
import com.agrriguard.data.repository.UserRepository
import com.agrriguard.databinding.ActivityRegisterBinding
import com.agrriguard.ui.home.HomeActivity
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userRepository = UserRepository((application as AgriGuardApp).database.userDao())

        binding.btnRegister.setOnClickListener {
            val name = binding.editName.text.toString()
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                val newUser = User(email, name, password)
                lifecycleScope.launch {
                    userRepository.register(newUser)
                    saveUserAndNavigate(newUser)
                }
            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnBackToLogin.setOnClickListener {
            finish()
        }
    }

    private fun saveUserAndNavigate(user: User) {
        val sharedPref = getSharedPreferences("AgriGuardPrefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("USER_NAME", user.name)
            putString("USER_EMAIL", user.email)
            apply()
        }
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
