package com.bignerdranch.android.blackjack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class GameActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_layout)
    }
}