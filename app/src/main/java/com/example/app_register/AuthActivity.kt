package com.example.app_register

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_auth)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val userLogin: EditText = findViewById(R.id.user_login_auth)
        val userPsw: EditText = findViewById(R.id.user_psw_auth)
        val buttonAuth: Button = findViewById(R.id.btn_auth)
        val linkReg: TextView = findViewById(R.id.link_reg)

        linkReg.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        buttonAuth.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val psw = userPsw.text.toString().trim()

            if (login == "" || psw == "")
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_LONG).show()
            else {
                val db = DB(this, null)
                val auth = db.getUsers(login, psw)
                if (auth) {
                    Toast.makeText(this, "Пользователь $login авторизован!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Пользователь $login не авторизован!", Toast.LENGTH_LONG).show()
                    userLogin.text.clear()
                    userPsw.text.clear()
                }
            }
        }
    }
}