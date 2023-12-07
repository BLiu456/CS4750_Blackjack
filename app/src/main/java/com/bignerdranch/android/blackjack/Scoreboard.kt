package com.bignerdranch.android.blackjack

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Scoreboard: AppCompatActivity() {
    private var highscore = 0;
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scoreboard_layout)


        if (savedInstanceState != null){
            highscore = savedInstanceState.getInt("score")
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("score", highscore)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getInt("score")
    }
}