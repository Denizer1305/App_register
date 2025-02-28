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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val userLogin: EditText = findViewById(R.id.user_login)
        val userEmail: EditText = findViewById(R.id.user_email)
        val userPsw: EditText = findViewById(R.id.user_psw)
        val buttonReg: Button = findViewById(R.id.btn_reg)
        val linkAuth: TextView = findViewById(R.id.link_auth)

        linkAuth.setOnClickListener{
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }

        buttonReg.setOnClickListener{
            val login = userLogin.text.toString().trim()
            val email = userEmail.text.toString().trim()
            val psw = userPsw.text.toString().trim()

            if(login == "" || email == "" || psw == "")
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_LONG).show()
            else {
                val user = User(login, email, psw)

                val db = DB(this, null)
                db.addUsers(user)
                Toast.makeText(this, "Пользователь $login успешно добавлен!", Toast.LENGTH_LONG).show()

                userLogin.text.clear()
                userEmail.text.clear()
                userPsw.text.clear()
            }
        }
    }
}