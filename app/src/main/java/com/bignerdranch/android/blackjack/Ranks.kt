package com.bignerdranch.android.blackjack

enum class Ranks {
    ACE {
        override fun getValue() = 1
        override fun getAltValue() = 11
        override fun getStr() = "ace"
        },
    TWO {
        override fun getValue() = 2
        override fun getAltValue() = 2
        override fun getStr() = "two"
       },
    THREE {
        override fun getValue() = 3
        override fun getAltValue() = 3
        override fun getStr() = "three"
    },
    FOUR {
        override fun getValue() = 4
        override fun getAltValue() = 4
        override fun getStr() = "four"
    },
    FIVE {
        override fun getValue() = 5
        override fun getAltValue() = 5
        override fun getStr() = "five"
    },
    SIX {
        override fun getValue() = 6
        override fun getAltValue() = 6
        override fun getStr() = "six"
    },
    SEVEN {
        override fun getValue() = 7
        override fun getAltValue() = 7
        override fun getStr() = "seven"
    },
    EIGHT {
        override fun getValue() = 8
        override fun getAltValue() = 8
        override fun getStr() = "eight"
    },
    NINE {
        override fun getValue() = 9
        override fun getAltValue() = 9
        override fun getStr() = "nine"
    },
    TEN {
        override fun getValue() = 10
        override fun getAltValue() = 10
        override fun getStr() = "ten"
    },
    JACK {
        override fun getValue() = 10
        override fun getAltValue() = 10
        override fun getStr() = "jack"
    },
    QUEEN {
        override fun getValue() = 10
        override fun getAltValue() = 10
        override fun getStr() = "queen"
    },
    KING {
        override fun getValue() = 10
        override fun getAltValue() = 10
        override fun getStr() = "king"
    };

    abstract fun getValue(): Int
    abstract fun getAltValue(): Int
    abstract fun getStr(): String
}