package com.example.rock_paper_scissors

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var playerChoice: ImageView
    private lateinit var computerChoice: ImageView
    private lateinit var results: TextView

    private lateinit var imgBtnRock: Button
    private lateinit var imgBtnPaper: Button
    private lateinit var imgBtnScissors: Button
    private lateinit var btnReset: Button

    private var userScore: Int = 0
    private var computerScore: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerChoice = findViewById(R.id.playerChoice)
        computerChoice = findViewById(R.id.computerChoice)
        results = findViewById(R.id.results)

        val btnRock = findViewById<ImageButton>(R.id.imgBtnRock)
        val btnPaper = findViewById<ImageButton>(R.id.imgBtnPaper)
        val btnScissors = findViewById<ImageButton>(R.id.imgBtnScissors)

        btnRock.setOnClickListener { play("rock") }
        btnPaper.setOnClickListener { play("paper") }
        btnScissors.setOnClickListener { play("scissors") }
        btnReset.setOnClickListener {reset()}

        btnReset.visibility = View.GONE
    }

    private fun play(playerMove: String) {
        val playerImage = getImages(playerMove)
        playerChoice.setImageResource(playerImage)

        val moves = listOf("rock", "paper", "scissors")
        val computerMove = moves[Random.nextInt(3)]
        val computerImage = getImages(computerMove)
        computerChoice.setImageResource(computerImage)

        val result = winner(playerMove, computerMove)
        results.text = result

        checkWinner()
    }

    private fun getImages(choice: String): Int {
        return when (choice) {
            "rock" -> R.drawable.rock
            "paper" -> R.drawable.paper
            "scissors" -> R.drawable.scissors
            else -> R.drawable.ic_launcher_background
        }
    }

    private fun winner(playerMove: String, computerMove: String): String {
        return if (playerMove == computerMove) {
            "It's a Tie!"
        } else if ((playerMove == "rock" && computerMove == "scissors") ||
            (playerMove == "scissors" && computerMove == "paper") ||
            (playerMove == "paper" && computerMove == "rock")) {
            userScore++
            "You Win!"

        } else {
            computerScore++
            "You Lose!"
        }
    }

    private fun checkWinner() {
        if (userScore == 10 || computerScore == 10) {
            results.text = if (userScore == 10) {
                "Great job, you beat the computer!"
            } else {
                "Sorry, the computer got the better of you."
            }
            imgBtnRock.isEnabled = false
            imgBtnPaper.isEnabled = false
            imgBtnScissors.isEnabled = false

            btnReset.visibility = View.VISIBLE
        }
    }

    private fun reset() {
        userScore = 0
        computerScore = 0

        imgBtnRock.isEnabled = true
        imgBtnPaper.isEnabled = true
        imgBtnScissors.isEnabled = true

        btnReset.visibility = View.GONE

    }
    /*reset button
    counter for wins for computer and player
    disable (rock/paper/scissor) after game end
    visible reset button during game (false)
     */

}
