package com.bignerdranch.android.blackjack

import android.os.Bundle
import android.widget.Button
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class GameOverActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_over)

        val mainMenu: Button = findViewById(R.id.returnToMainMenuButton)
        val playAgain: Button = findViewById(R.id.playAgainButton)

        mainMenu.setOnClickListener { returnMainMenu() }
        playAgain.setOnClickListener { returnPlayAgain() }
    }

    private fun returnMainMenu(){
        val intent = Intent()
        intent.putExtra("OPTION", 0)
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun returnPlayAgain(){
        val intent = Intent()
        intent.putExtra("OPTION", 1)
        setResult(RESULT_OK, intent)
        finish()
    }
}