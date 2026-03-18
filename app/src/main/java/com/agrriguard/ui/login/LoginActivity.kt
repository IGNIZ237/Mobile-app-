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
import com.agrriguard.databinding.ActivityLoginBinding
import com.agrriguard.ui.home.HomeActivity
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Vérifier si l'utilisateur est déjà connecté
        val sharedPref = getSharedPreferences("AgriGuardPrefs", Context.MODE_PRIVATE)
        if (sharedPref.contains("USER_NAME")) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
            return
        }

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userRepository = UserRepository((application as AgriGuardApp).database.userDao())

        binding.btnLogin.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                lifecycleScope.launch {
                    val user = userRepository.login(email, password)
                    if (user != null) {
                        saveUserAndNavigate(user)
                    } else {
                        Toast.makeText(this@LoginActivity, "Identifiants incorrects", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnGoToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun saveUserAndNavigate(user: User) {
        val sharedPref = getSharedPreferences("AgriGuardPrefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("USER_NAME", user.name)
            putString("USER_EMAIL", user.email)
            apply()
        }
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}
