package com.bignerdranch.android.blackjack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.blackjack.ui.theme.BlackjackTheme
import android.content.Intent
import android.view.View
import android.app.Dialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)
    }
    private fun showRulesPopup() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.how_to_play)
        dialog.show()
    }
    // Called when the "Play" button is clicked
    fun onPlayButtonClick(view: View) {
        // Start the Play activity or perform the Play action
    }

    // Called when the "Scoreboard" button is clicked
    fun onScoreboardButtonClick(view: View) {
        // Start the Scoreboard activity
    }

    // Called when the "Rules" button is clicked
    fun onRulesButtonClick(view: View) {
        showRulesPopup()
    }
    // Close the pop-up
    fun onClosePopupButtonClick(view: View) {
        val dialog = view.parent as Dialog
        dialog.dismiss()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BlackjackTheme {
        Greeting("Android")
    }
}