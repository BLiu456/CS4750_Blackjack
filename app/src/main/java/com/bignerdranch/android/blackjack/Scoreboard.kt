package com.bignerdranch.android.blackjack

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class Scoreboard: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scoreboard_layout)

        var scoreSetter: TextView = findViewById(R.id.highscore)
        var nameSetter: TextView = findViewById(R.id.playername)
        var savedScore = getSharedPreferences("highScoreSave", 0);
        var highScore = intent.getIntExtra("SCORE", -1)
        var playerName = savedScore.getString("savedName", "Player")
        scoreSetter.setText(Integer.toString(highScore))
        nameSetter.setText(playerName)


    }
}