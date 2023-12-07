package com.bignerdranch.android.blackjack

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import android.widget.LinearLayout
import android.widget.Button

class GameActivity : AppCompatActivity() {

    private lateinit var playerCardsLayout: LinearLayout
    private lateinit var dealerCardsLayout: LinearLayout

    private var cardDeck = Deck()

    private var playerCards = mutableListOf<Card>()
    private var dealerCards = mutableListOf<Card>()
    private var playerVal = 0
    private var dealerVal = 0

    private var ddFlag = false
    private var playerBlackFlag = false
    private var dealerBlackFlag = false

    private var balance = Score()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_layout)

        // Retrieve bet amount
        balance.setBet(intent.getIntExtra("CURRENT_BET", 0))

        playerCardsLayout = findViewById(R.id.playerHandLayout)
        dealerCardsLayout = findViewById(R.id.dealerHandLayout)

        // Deal two cards to the player and the dealer

        val hitButton: Button = findViewById(R.id.btnHit)
        val standButton: Button = findViewById(R.id.btnStand)
        val doubleDownButton: Button = findViewById(R.id.btnDoubleDown)

        hitButton.setOnClickListener {playerHit()}
        standButton.setOnClickListener {playerStand()}
        doubleDownButton.setOnClickListener {playerDoubleDown()}

        roundStart()
    }
    private fun roundStart() {
        //Draw two cards for player and dealer
        playerCards.add(cardDeck.drawCard())
        playerCards.add(cardDeck.drawCard())

        dealerCards.add(cardDeck.drawCard())
        dealerCards.add(cardDeck.drawCard())

        playerVal = calcHand(playerCards)
        dealerVal = calcHand(dealerCards)
    }
    private fun playerHit(){
        playerCards.add(cardDeck.drawCard())

        playerVal = calcHand(playerCards)

        if (playerVal < 21) {
            return
        }
        else if (playerVal == 21) {
            playerStand()
        }
        else { //Player is now over 21, so its a lost
            balance.calcResults(false, ddFlag, false)
            Log.d("Game", "Player Lose(Over 21) $playerVal $dealerVal")
            roundEnd()
        }
    }
    private fun playerStand(){
        //Go to dealer phase
        dealerAI()
        roundEnd()
    }
    private fun playerDoubleDown(){ //Player doubles bet, hit and then stand
        ddFlag = true
        playerHit()
        playerStand()
    }

    private fun dealerAI(){
        //Flip up faced down card
        dealerVal = calcHand(dealerCards)

        while (dealerVal <= 16) {
            dealerCards.add(cardDeck.drawCard())
            dealerVal = calcHand(dealerCards)
        }

        if (dealerVal > 21) {
            balance.calcResults(true, ddFlag, playerVal == 21)
            Log.d("Game", "Player Win(Dealer Over) $playerVal $dealerVal")
            return
        }
        //See who won
        if (playerVal > dealerVal) {
            balance.calcResults(true, ddFlag, playerVal == 21)
            Log.d("Game", "Player Win $playerVal $dealerVal")
        }
        else if (playerVal < dealerVal) {
            balance.calcResults(false, ddFlag, dealerVal == 21)
            Log.d("Game", "Player Lose $playerVal $dealerVal")
        }
        else if (playerVal == dealerVal){
            //Tie, player gains nothing
            Log.d("Game", "Tie $playerVal $dealerVal")
        }
    }

    private fun calcHand(hand:MutableList<Card>):Int{
        var total:Int = 0
        hand.forEach{x -> total += x.rank.getValue()}
        return total
    }

    private fun roundEnd() {
        playerCards.clear()
        dealerCards.clear()
        playerVal = 0
        dealerVal = 0
        cardDeck.shuffle()
        roundStart()
    }

}
