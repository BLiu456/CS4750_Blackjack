package com.bignerdranch.android.blackjack

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import android.widget.LinearLayout
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler

class GameActivity : AppCompatActivity() {
    private lateinit var player_recycler: RecyclerView
    private lateinit var player_adapter: PlayerAdapter
    private lateinit var dealer_recycler: RecyclerView
    private lateinit var dealer_adapter: DealerAdapter
    private lateinit var playButton: Button
    private lateinit var quitButton: Button
    private lateinit var hitButton: Button
    private lateinit var standButton: Button
    private lateinit var doubleDownButton: Button
    private lateinit var balanceTxt: TextView
    private var blackjack = 21

    private val gameViewModel:GameViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_layout)

        // Retrieve bet amount
        gameViewModel.setBet(intent.getIntExtra("CURRENT_BET", 0))

        player_recycler = findViewById(R.id.playerCardsRv)
        player_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        player_adapter = PlayerAdapter(gameViewModel.playerCards)
        player_recycler.adapter = player_adapter

        dealer_recycler = findViewById(R.id.dealerCardsRv)
        dealer_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        dealer_adapter = DealerAdapter(gameViewModel.dealerCards)
        dealer_recycler.adapter = dealer_adapter

        playButton = findViewById(R.id.playBtn)
        quitButton = findViewById(R.id.quitBtn)
        hitButton = findViewById(R.id.btnHit)
        standButton = findViewById(R.id.btnStand)
        doubleDownButton = findViewById(R.id.btnDoubleDown)
        balanceTxt = findViewById((R.id.balance_txt))

        playButton.setOnClickListener { play() }
        quitButton.setOnClickListener { quit() }
        hitButton.setOnClickListener { hit() }
        standButton.setOnClickListener { stand() }
        doubleDownButton.setOnClickListener { doubleDown() }
        balanceTxt.text = "$${gameViewModel.getBalance()}"

        hitButton.visibility = View.GONE
        standButton.visibility = View.GONE
        doubleDownButton.visibility = View.GONE
    }

    fun play() {

        //Implement asking user to place their bets here

        playButton.visibility = View.GONE
        quitButton.visibility = View.GONE
        hitButton.visibility = View.VISIBLE
        standButton.visibility = View.VISIBLE
        doubleDownButton.visibility = View.VISIBLE

        gameViewModel.cleanup()
        gameViewModel.roundStart()
        player_adapter.notifyDataSetChanged()
        dealer_adapter.notifyDataSetChanged()
    }

    fun quit() {
        //Save highest balance as high score
        finish()
    }

    fun hit() {
        gameViewModel.playerHit()

        if (gameViewModel.playerVal > blackjack) {
            loser()
        } else if (gameViewModel.playerVal == blackjack) {
            stand()
        }

        player_adapter.notifyDataSetChanged()
        dealer_adapter.notifyDataSetChanged()
    }

    fun stand() {
        gameViewModel.playerStand()

        if (gameViewModel.dealerVal > blackjack) {
            winner()
            dealer_adapter.notifyDataSetChanged()
            return
        }

        //See who won
        if (gameViewModel.playerVal > gameViewModel.dealerVal) {
           winner()

        }
        else if (gameViewModel.playerVal < gameViewModel.dealerVal) {
            loser()
        }
        else if (gameViewModel.playerVal == gameViewModel.dealerVal){
            tie()
        }

        dealer_adapter.notifyDataSetChanged()
    }

    fun doubleDown() {
        gameViewModel.playerDoubleDown()
        hit()

        if (gameViewModel.playerVal >= blackjack) {
            return
        }
        stand()
    }

    fun winner() {
        gameViewModel.playerWin()
        balanceTxt.text = "$${gameViewModel.getBalance()}"

        Toast.makeText(this, R.string.win_toast, Toast.LENGTH_SHORT).show()
        playButton.visibility = View.VISIBLE
        quitButton.visibility = View.VISIBLE
        hitButton.visibility = View.GONE
        standButton.visibility = View.GONE
        doubleDownButton.visibility = View.GONE
    }

    fun loser() {
        gameViewModel.playerLose()
        balanceTxt.text = "$${gameViewModel.getBalance()}"

        Toast.makeText(this, R.string.lose_toast, Toast.LENGTH_SHORT).show()
        playButton.visibility = View.VISIBLE
        quitButton.visibility = View.VISIBLE
        hitButton.visibility = View.GONE
        standButton.visibility = View.GONE
        doubleDownButton.visibility = View.GONE
    }

    fun tie() {
        Toast.makeText(this, R.string.tie_toast, Toast.LENGTH_SHORT).show()
        playButton.visibility = View.VISIBLE
        quitButton.visibility = View.VISIBLE
        hitButton.visibility = View.GONE
        standButton.visibility = View.GONE
        doubleDownButton.visibility = View.GONE

        gameViewModel.dealerCards[0].flipped = true
    }

    //Implement a game over function
}
