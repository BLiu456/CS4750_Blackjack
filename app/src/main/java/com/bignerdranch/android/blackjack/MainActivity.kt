package com.bignerdranch.android.blackjack

import android.os.Bundle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.blackjack.ui.theme.BlackjackTheme
import android.view.View
import android.app.Dialog
import android.content.Intent
import android.os.PersistableBundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    private var highScore = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        val onPlayButtonClick = findViewById<View>(R.id.btnPlay)
        onPlayButtonClick.setOnClickListener {view: View ->
            onPlayButtonClick(view)
        }

        if (savedInstanceState != null){
            highScore = savedInstanceState.getInt("score")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            if (data != null) {
                val scoreData = data.extras
                if (scoreData != null) {
                    val newScore = scoreData.getInt("MAX_SCORE")

                    if (newScore > highScore) {
                        highScore = newScore
                    }
                }
            }
        }
    }
    // Called when the "Scoreboard" button is clicked
    private fun showScoreboard() {
        val intent = Intent(this, Scoreboard::class.java)
        intent.putExtra("SCORE", highScore)
        startActivity(intent)
    }

    private fun showRulesPopup() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.how_to_play)
        dialog.show()
    }

    // Called when the "Play" button is clicked
    fun onPlayButtonClick(view: View) {
        //Change this to the game activity once the game can loop with the bet ****
        val intent = Intent(this, GameActivity::class.java)
        startActivityForResult(intent, 100)
    }

    // Called when the "Scoreboard" button is clicked
    fun onScoreboardButtonClick(view: View) {
        showScoreboard()
    }

    // Called when the "Rules" button is clicked
    fun onRulesButtonClick(view: View) {
        showRulesPopup()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("score", highScore)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getInt("score")
    }

}