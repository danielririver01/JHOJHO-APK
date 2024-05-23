// HomeActivity.kt
package com.example.jhojho

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    lateinit var welcomeText: TextView
    lateinit var logoutBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Inicializar vistas
        welcomeText = findViewById(R.id.welcome_text)
        logoutBtn = findViewById(R.id.logout_btn)

        // Obtener el nombre de usuario o correo del intent
        val userEmail = intent.getStringExtra("USER_EMAIL")
        welcomeText.text = "Hola $userEmail"

        // Configurar el botón de cerrar sesión
        logoutBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
