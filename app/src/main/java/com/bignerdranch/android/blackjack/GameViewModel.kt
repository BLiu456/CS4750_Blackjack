package com.bignerdranch.android.blackjack

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel:ViewModel() {
    private var cardDeck = Deck()

    var playerCards = mutableListOf<Card>()
    var dealerCards = mutableListOf<Card>()
    private var playerVal = 0
    private var dealerVal = 0

    private var ddFlag = false

    private var balance = Score()

    fun setBet(bet:Int) {
        balance.setBet(bet)
    }
   fun roundStart() {
        //Draw two cards for player and dealer
        playerCards.add(cardDeck.drawCard())
        playerCards.add(cardDeck.drawCard())

        dealerCards.add(cardDeck.drawCard())
        dealerCards.add(cardDeck.drawCard())

        playerVal = calcHand(playerCards)
        dealerVal = calcHand(dealerCards)
    }
    fun playerHit(){
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
            Log.d("Game", "Player Lose(Over 21) $playerVal $dealerVal ${balance.getScore()}")
            roundEnd()
        }
    }
    fun playerStand(){
        //Go to dealer phase
        dealerAI()
        roundEnd()
    }
    fun playerDoubleDown(){ //Player doubles bet, hit and then stand
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
            Log.d("Game", "Player Win(Dealer Over) $playerVal $dealerVal ${balance.getScore()}")
            return
        }
        //See who won
        if (playerVal > dealerVal) {
            balance.calcResults(true, ddFlag, playerVal == 21)
            Log.d("Game", "Player Win $playerVal $dealerVal ${balance.getScore()}")
        }
        else if (playerVal < dealerVal) {
            balance.calcResults(false, ddFlag, dealerVal == 21)
            Log.d("Game", "Player Lose $playerVal $dealerVal ${balance.getScore()}")
        }
        else if (playerVal == dealerVal){
            //Tie, player gains nothing
            Log.d("Game", "Tie $playerVal $dealerVal ${balance.getScore()}")
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
        ddFlag = false
        cardDeck.shuffle()
        roundStart()
    }
}