package com.example.sandboxandroid.controllers

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.sandboxandroid.R
import com.example.sandboxandroid.models.Question
import com.example.sandboxandroid.models.QuestionBank

class GameActivity : AppCompatActivity(), View.OnClickListener {
    private val questionTextView: TextView by lazy { findViewById(R.id.activity_game_question_text_view) }
    private val answerButtons: List<Button> by lazy { listOf(
        findViewById(R.id.activity_game_answer_button_0),
        findViewById(R.id.activity_game_answer_button_1),
        findViewById(R.id.activity_game_answer_button_2),
        findViewById(R.id.activity_game_answer_button_3),
    ) }
    private val bank = QuestionBank.generateQuestions()
    private var question: Question? = bank.getNextQuestion()
    private var score = 0

    private fun displayQuestion(question: Question) {
        questionTextView.text = question.text
        answerButtons.zip(question.answers).map { (button, answer) -> button.text = answer }
    }

    override fun onClick(v: View?) {
        if (v != null && v is Button) {
            question?.let {
                Toast.makeText(this, if (it.answers[it.correctAnswerIndex] == v.text) {
                    score++
                    "Right"
                } else "wrong", Toast.LENGTH_SHORT).show()
            }
            question = bank.getNextQuestion()
            question?.let { displayQuestion(it) }
            if (question == null) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Your score").setMessage(score.toString()).setPositiveButton("OK") { _, _ -> finish()}
                builder.show()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        answerButtons.map { it.setOnClickListener(this) }

        question?.let { displayQuestion(it) }

    }
}
