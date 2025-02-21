package com.example.rock_paper_scissors

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var playerChoice: ImageView
    private lateinit var computerChoice: ImageView
    private lateinit var results: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerChoice = findViewById(R.id.playerChoice)
        computerChoice = findViewById(R.id.computerChoice)
        results = findViewById(R.id.results)

        val btnRock = findViewById<Button>(R.id.btnRock)
        val btnPaper = findViewById<Button>(R.id.btnPaper)
        val btnScissors = findViewById<Button>(R.id.btnScissors)

        btnRock.setOnClickListener { play("rock") }
        btnPaper.setOnClickListener { play("paper") }
        btnScissors.setOnClickListener { play("scissors") }
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
            "You Win!"
        } else {
            "You Lose!"
        }
    }
    /*reset button
    counter for wins for computer and player
    disable (rock/paper/scissor) after game end
    visible reset button during game (false)
     */

}
