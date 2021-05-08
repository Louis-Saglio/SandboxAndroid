package com.example.sandboxandroid.controllers

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.sandboxandroid.R


class MainActivity : AppCompatActivity() {
    private val nameInput: EditText by lazy { findViewById(R.id.activity_main_name_input) }
    private val playButton: Button by lazy { findViewById(R.id.activity_main_play_btn) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playButton.isEnabled = false

        nameInput.addTextChangedListener {
            playButton.isEnabled = !it.isNullOrBlank()
        }
        playButton.setOnClickListener {
            nameInput.text.clear()
            startActivity(Intent(this, GameActivity::class.java))
        }
    }
}
