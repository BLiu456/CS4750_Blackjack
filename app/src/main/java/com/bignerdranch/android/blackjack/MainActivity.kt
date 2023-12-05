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

class MainActivity : AppCompatActivity() {
    private var highScore = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)
    }

    private fun showScoreboard() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.scoreboard_layout)
        dialog.show()
    }

    private fun showRulesPopup() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.how_to_play)
        dialog.show()
    }
    // Called when the "Scoreboard" button is clicked

    // Called when the "Play" button is clicked
    fun onPlayButtonClick(view: View) {
        // Start the Play activity or perform the Play action
    }

    // Called when the "Scoreboard" button is clicked
    fun onScoreboardButtonClick(view: View) {
        showScoreboard()
    }

    // Called when the "Rules" button is clicked
    fun onRulesButtonClick(view: View) {
        showRulesPopup()
    }
}