package com.bignerdranch.android.blackjack
import android.content.Context
import com.bignerdranch.android.blackjack.Card
import com.bignerdranch.android.blackjack.Suites
import com.bignerdranch.android.blackjack.Ranks


class Deck() {
    private var cardList = mutableMapOf<Int, Card>() //Key is id of card, and the value is the card itself
    private var allowDraw = mutableListOf<Int>() //What is currently in deck (what is allowed to be drawn)
    private var discardPile = mutableListOf<Int>() //Cards not allowed to be drawn
    private var cardBack = R.drawable.card_back_red //Refers to the id of the img
    private var ctx = App.getAppContext()
    init {
        var id: Int = 0
        for (s in Suites.values()) {
            for (r in Ranks.values()) {
                cardList[id] = Card(s, r, getFront(s.str, r.getStr()))
                allowDraw.add(id)
                id++
            }
        }
    }

    private fun getFront(suite:String, rank:String): Int {
        var uri:String = "@drawable/" + rank + "_of_" + suite
        return ctx.resources.getIdentifier(uri, null, ctx.packageName)
    }

    public fun getCardBack():Int {
        return cardBack
    }

    public fun setCardBack(res:Int) { //Expects id of the new img
        cardBack = res
    }

    public fun shuffle() { //Places cards in discard pile back into to deck to be drawn again
        for (card in discardPile) {
            allowDraw.add(card)
        }
        discardPile.clear()
    }

    public fun drawCard():Card {
        if (allowDraw.isEmpty()) //If deck is empty, reshuffle and do the draw
        {
            shuffle()
        }

        var pick = allowDraw.random()
        discardPile.add(pick)
        allowDraw.remove(pick)
        return cardList.getValue(pick)
    }
}