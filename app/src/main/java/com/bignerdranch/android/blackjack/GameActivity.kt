package com.bignerdranch.android.blackjack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/*  TODO
    Bet function
    Create function that takes in players and dealers card to display to layout
    A function to flip dealer's card
    Dealer AI
    Calculate results
    Update balance*/

class GameActivity: AppCompatActivity(){
    private var cardDeck:Deck = Deck()
    private var playerCards = mutableListOf<Card>()
    private var dealerCards = mutableListOf<Card>()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_layout)
    }

    private fun dealCards(){
        //Draw 2 player cards
        playerCards.add(cardDeck.drawCard())
        playerCards.add(cardDeck.drawCard())

        //Draw 2 dealer cards
        dealerCards.add(cardDeck.drawCard())
        dealerCards.add(cardDeck.drawCard())
    }
}