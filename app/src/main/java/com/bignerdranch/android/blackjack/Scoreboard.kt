package com.bignerdranch.android.blackjack

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class Scoreboard: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scoreboard_layout)

        var scoreSetter: TextView = findViewById(R.id.highscore)
        var savedScore = getSharedPreferences("highScoreSave", 0);
        var highScore = savedScore.getInt("highScore", 69420)
        scoreSetter.setText(Integer.toString(highScore))
    }
}