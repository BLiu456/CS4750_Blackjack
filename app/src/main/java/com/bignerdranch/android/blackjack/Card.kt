package com.bignerdranch.android.blackjack
import com.bignerdranch.android.blackjack.Suites
import com.bignerdranch.android.blackjack.Ranks
import android.content.Context
data class Card(val suite:Suites, val rank:Ranks, val front:Int, var flipped:Boolean = false)