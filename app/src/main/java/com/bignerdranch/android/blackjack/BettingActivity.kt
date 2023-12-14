package com.bignerdranch.android.blackjack

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Button
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class BettingActivity: AppCompatActivity() {
    private var playerBalance: Int = 100
    private var currentBet: Int = 0

    private lateinit var balanceTextView: TextView
    private lateinit var betEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        playerBalance = intent.getIntExtra("CURRENT_AMOUNT", 100)

        setContentView(R.layout.betting_layout)

        balanceTextView = findViewById(R.id.textCurrentAmount)
        betEditText = findViewById(R.id.editBetAmount)

        updateBalanceText()

        val minBetButton: Button = findViewById(R.id.btnDecrement)
        val maxBetButton: Button = findViewById(R.id.btnIncrement)
        val betButton: Button = findViewById(R.id.btnBet)

        var min = playerBalance * 0.1
        minBetButton.setOnClickListener { setBetAmount(min.toInt()) }
        maxBetButton.setOnClickListener { setBetAmount(playerBalance) }
        betButton.setOnClickListener { lockInBet() }
    }

    private fun updateBalanceText() {
        balanceTextView.text = "Balance: $playerBalance"
    }

    private fun setBetAmount(amount: Int) {
        currentBet = amount
        betEditText.setText(amount.toString())
    }

    private fun lockInBet() {
        val betInput = betEditText.text.toString().toIntOrNull()

        if (betInput != null && betInput >= playerBalance * 0.1 && betInput <= playerBalance) {
            currentBet = betInput

            val intent = Intent()
            intent.putExtra("BET_AMOUNT", currentBet)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}