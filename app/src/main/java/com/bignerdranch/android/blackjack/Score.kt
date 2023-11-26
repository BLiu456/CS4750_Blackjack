package com.bignerdranch.android.blackjack

class Score {
    private var startScore = 100 //100 will be default value
    private var currScore:Int = startScore
    private var bet:Int = 10
    private var doubleDownModifier:Int = 1
    private var blackJackModifier:Int = 1

    public fun getScore():Int {
        return currScore
    }

    public fun setScore(newScore:Int) { //Use calcResults() to change the score during a game
        currScore = newScore            //setScore() is for development purposes.
    }

    public fun resetScore() {
        currScore = startScore
    }

    public fun changeStartScore(newStart:Int) {
        startScore = newStart
    }

    //Returns a boolean to indicate if the bet is valid or not
    public fun setBet(newBet:Int):Boolean {
        //Bet must be positive and nonzero. Also must be <= currScore.
        if (newBet <= 0 || newBet > currScore) {
            return false
        }

        bet = newBet
        return true
    }

    public fun setDoubleModifier(newMod:Int) {
        doubleDownModifier = newMod
    }

    public fun setBlackJackModifier(newMod:Int) {
        blackJackModifier = newMod
    }

    //Calculate the result based on if user has won or lost.
    //Amount is determined by bet and if user has doubled down and won/lost by black jack
    public fun calcResults(win:Boolean, db:Boolean, bljk:Boolean) {
        var dbMod:Int = if (db) doubleDownModifier else 0
        var bkjkMod:Int = if (bljk) blackJackModifier else 0

        if (win) {
            currScore += bet * (1 + dbMod + bkjkMod)
        }
        else {
            currScore -= bet * (1 + dbMod + bkjkMod)
        }
    }
}