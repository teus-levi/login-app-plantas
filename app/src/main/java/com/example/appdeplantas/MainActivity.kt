package com.example.appdeplantas

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appdeplantas.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //barra de notificações com coloração
        window.statusBarColor = Color.parseColor("#FFFFFF")

        binding.btEntrar.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()

            when{
                email.isEmpty() -> {
                    binding.editEmail.error = "Preencha o E-mail!"
                }
                senha.isEmpty() -> {
                    binding.editSenha.error = "Preencha a Senha!"
                }
                !email.contains("@gmail.com") -> {
                    val snackbar = Snackbar.make(it, "E-mail inválido!", Snackbar.LENGTH_SHORT)
                    snackbar.show()
                }
                senha.length <= 5 -> {
                    val snackbar = Snackbar.make(it, "A senha precisa ter pelo menos 6 caracteres!", Snackbar.LENGTH_SHORT)
                    snackbar.show()
                }
                else -> {
                    //login do usuário
                    /*
                    val snackbar = Snackbar.make(it, "Logado com sucesso!", Snackbar.LENGTH_SHORT)
                    snackbar.show()

                     */
                    login(it)
                }
            }
        }
    }
    private fun login(view: View){
        val progressbar = binding.progressBar
        progressbar.visibility = View.VISIBLE

        binding.btEntrar.isEnabled = false
        binding.btEntrar.setTextColor(Color.parseColor("#FFFFFF"))

        Handler(Looper.getMainLooper()).postDelayed({
            navegarTelaPrincipal()
            val snackbar = Snackbar.make(view, "Logado com sucesso!", Snackbar.LENGTH_SHORT)
            snackbar.show()
        }, 3000)
    }

    private fun navegarTelaPrincipal(){
        val intent = Intent(this, TelaPrincipal::class.java)
        startActivity(intent)
        finish()
    }

}