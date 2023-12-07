package com.bignerdranch.android.blackjack

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Button
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class BettingActivity: AppCompatActivity() {
    private var playerBalance: Double = 100.0
    private var currentBet: Double = 0.0

    private lateinit var balanceTextView: TextView
    private lateinit var betEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.betting_layout)

        balanceTextView = findViewById(R.id.textCurrentAmount)
        betEditText = findViewById(R.id.editBetAmount)

        updateBalanceText()

        val minBetButton: Button = findViewById(R.id.btnDecrement)
        val maxBetButton: Button = findViewById(R.id.btnIncrement)
        val betButton: Button = findViewById(R.id.btnBet)

        minBetButton.setOnClickListener { setBetAmount(playerBalance * 0.1) }
        maxBetButton.setOnClickListener { setBetAmount(playerBalance) }
        betButton.setOnClickListener { lockInBet() }
    }

    private fun updateBalanceText() {
        balanceTextView.text = "Balance: $playerBalance"
    }

    private fun setBetAmount(amount: Double) {
        currentBet = amount
        betEditText.setText(amount.toString())
    }

    private fun lockInBet() {
        val betInput = betEditText.text.toString().toDoubleOrNull()

        if (betInput != null && betInput >= playerBalance * 0.1 && betInput <= playerBalance) {
            currentBet = betInput

            playerBalance -= currentBet
            updateBalanceText()

            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("CURRENT_BET", currentBet)
            startActivity(intent)
        }
    }
}