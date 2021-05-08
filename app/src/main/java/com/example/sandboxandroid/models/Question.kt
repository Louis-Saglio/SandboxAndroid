package com.example.sandboxandroid.models

class Question(val text: String, val answers: List<String>, val correctAnswerIndex: Int)

class QuestionBank(questions: List<Question>) {
    private var nextQuestionIndex = 0
    private val questions = questions.shuffled()

    fun getNextQuestion(): Question? {
        val question = questions.getOrNull(nextQuestionIndex)
        if (nextQuestionIndex < questions.size) {
            nextQuestionIndex++
        }
        return question
    }

    companion object {
        fun generateQuestions(): QuestionBank {
            val question1 = Question(
                "Who is the creator of Android?",
                listOf("Andy Rubin", "Steve Wozniak", "Jake Wharton", "Paul Smith"),
                0
            )
            val question2 = Question(
                "When did the first man land on the moon?",
                listOf("1958", "1962", "1967", "1969"),
                3
            )
            val question3 = Question(
                "What is the house number of The Simpsons?",
                listOf("42", "101", "666", "742"),
                3
            )

            return QuestionBank(listOf(question1, question2, question3))
        }
    }
}
