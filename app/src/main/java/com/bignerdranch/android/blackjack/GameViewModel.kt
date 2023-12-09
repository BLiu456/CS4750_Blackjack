package com.bignerdranch.android.blackjack

import android.util.Log
import androidx.compose.material3.contentColorFor
import androidx.lifecycle.ViewModel

class GameViewModel:ViewModel() {
    private var cardDeck = Deck()

    var playerCards = mutableListOf<Card>()
    var dealerCards = mutableListOf<Card>()
    var playerVal = 0
    var dealerVal = 0
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
    }

    fun playerStand(){
        //Go to dealer phase
        //Flip up faced down card --- Still need to implement

        dealerVal = calcHand(dealerCards)

        while (dealerVal <= 16) {
            dealerCards.add(cardDeck.drawCard())
            dealerVal = calcHand(dealerCards)
        }
    }

    fun playerDoubleDown(){ //Player doubles bet, hit and then stand
        ddFlag = true
    }

    fun calcHand(hand:MutableList<Card>):Int{
        var total:Int = 0
        var aceCounter:Int = 0

        for (card in hand) {
            if (card.rank.getStr() == "ace") {
                aceCounter++
                continue
            }
            total += card.rank.getValue()
        }

        //Determine if ace should be counted as 1 or 11 after other cards have been calculated
        if (aceCounter != 0) {
            for (ace in 1..aceCounter) {
                if ((total + 11) <= 21) {
                    total += 11
                    continue
                }
                total += 1
            }
        }

        return total
    }

    fun playerWin() {
        balance.calcResults(true, ddFlag, playerVal == 21)
        dealerCards[0].flipped = true
    }

    fun playerLose() {
        balance.calcResults(false, ddFlag, dealerVal == 21)
        dealerCards[0].flipped = true
    }

    fun getBalance():Int {
        return balance.getScore()
    }

    fun cleanup() {
        if (playerCards.isNotEmpty() || dealerCards.isNotEmpty()) {
            dealerCards[0].flipped = false
            playerCards.clear()
            dealerCards.clear()
            playerVal = 0
            dealerVal = 0
            ddFlag = false
            cardDeck.shuffle()
        }
    }
}