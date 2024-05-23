package com.example.jhojho

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class RegisterActivity : AppCompatActivity() {

    lateinit var usernameInput: EditText
    lateinit var emailInput: EditText
    lateinit var passwordInput: EditText
    lateinit var repasswordInput: EditText
    lateinit var signupBtn: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicializar vistas
        usernameInput = findViewById(R.id.username)
        emailInput = findViewById(R.id.email)
        passwordInput = findViewById(R.id.password)
        repasswordInput = findViewById(R.id.repassword)
        signupBtn = findViewById(R.id.signupbtn)

        signupBtn.setOnClickListener {
            val newUsername = usernameInput.text.toString()
            val newEmail = emailInput.text.toString()
            val newPassword = passwordInput.text.toString()
            val newRePassword = repasswordInput.text.toString()

            // Verificar que los campos no estén vacíos
            if (newUsername.isEmpty() || newEmail.isEmpty() || newPassword.isEmpty() || newRePassword.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Verificar que las contraseñas coincidan
            if (newPassword != newRePassword) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Crear un nuevo usuario con la información proporcionada
            val newUser = User(newUsername, newEmail, newPassword)

            // Agregar el usuario a la base de datos
            val dbHelper = ConfiguracionBd(this)
            dbHelper.addUser(newUser)

            // Mostrar un mensaje de éxito
            Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()

            // Limpiar los campos de entrada
            usernameInput.text.clear()
            emailInput.text.clear()
            passwordInput.text.clear()
            repasswordInput.text.clear()
        }
    }
}

// Definir la clase User
data class User(val username: String, val email: String, val password: String)
