package com.bignerdranch.android.blackjack

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import android.widget.LinearLayout
import android.widget.Button
import androidx.activity.viewModels

class GameActivity : AppCompatActivity() {

    private lateinit var playerCardsLayout: LinearLayout
    private lateinit var dealerCardsLayout: LinearLayout
    private val gameViewModel:GameViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_layout)

        // Retrieve bet amount
        gameViewModel.setBet(intent.getIntExtra("CURRENT_BET", 0))

        playerCardsLayout = findViewById(R.id.playerHandLayout)
        dealerCardsLayout = findViewById(R.id.dealerHandLayout)

        // Deal two cards to the player and the dealer

        val hitButton: Button = findViewById(R.id.btnHit)
        val standButton: Button = findViewById(R.id.btnStand)
        val doubleDownButton: Button = findViewById(R.id.btnDoubleDown)

        hitButton.setOnClickListener {hit()}
        standButton.setOnClickListener {stand()}
        doubleDownButton.setOnClickListener {doubleDown()}

        gameViewModel.roundStart()
        updateCards()
    }

    fun hit() {
        gameViewModel.playerHit()
    }

    fun stand() {
        gameViewModel.playerStand()
        updateCards()
    }

    fun doubleDown() {
        gameViewModel.playerDoubleDown()
        updateCards()
    }
    fun updateCards() {
        var playerCard1 = findViewById<ImageView>(R.id.playerCard1)
        playerCard1.setImageResource(gameViewModel.playerCards[0].front)

        var playerCard2 = findViewById<ImageView>(R.id.playerCard2)
        playerCard2.setImageResource(gameViewModel.playerCards[1].front)

        var dealerCard1 = findViewById<ImageView>(R.id.dealerCard1)
        dealerCard1.setImageResource(gameViewModel.dealerCards[0].front)
    }
}
