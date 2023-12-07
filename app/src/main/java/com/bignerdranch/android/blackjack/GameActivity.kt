package com.bignerdranch.android.blackjack

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import android.widget.LinearLayout
import android.widget.Button

class GameActivity : AppCompatActivity() {

    private lateinit var playerCardsLayout: LinearLayout
    private lateinit var dealerCardsLayout: LinearLayout

    private var playerCards: MutableList<Int> = mutableListOf()
    private var dealerCards: MutableList<Int> = mutableListOf()

    private var currentBet: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_layout)

        // Retrieve bet amount
        currentBet = intent.getDoubleExtra("CURRENT_BET", 0.0)

        playerCardsLayout = findViewById(R.id.playerHandLayout)
        dealerCardsLayout = findViewById(R.id.dealerHandLayout)

        // Deal two cards to the player and the dealer


        updateGameStatus()

        val hitButton: Button = findViewById(R.id.btnHit)
        val standButton: Button = findViewById(R.id.btnStand)
        val doubleDownButton: Button = findViewById(R.id.btnDoubleDown)

        hitButton.setOnClickListener {}
        standButton.setOnClickListener {}
        doubleDownButton.setOnClickListener {}
    }
    private fun updateGameStatus() {

    }

}
