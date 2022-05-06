package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    // Created a player class to handle player information
    private val player1 = Player("Player X", "X")
    private val player2 = Player("Player O", "O")
    // Keeps track of which player's turn it is
    private var activePlayer = player1

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.mipmap.tac)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Couldn't quite figure out how to hand this as a generic type
        // or use as a variable
        findViewById<TextView>(R.id.textView).text = getString(R.string.players_turn, player1.name)

        val reset = findViewById<Button>(R.id.new_game)

        // Creates an array of buttons
        val buttons = arrayOf(
            findViewById(R.id.button1),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4),
            findViewById(R.id.button5),
            findViewById(R.id.button6),
            findViewById(R.id.button7),
            findViewById(R.id.button8),
            findViewById<Button>(R.id.button9)
        )

        //Send the buttons in the array to the onClick function
        for(button in buttons){
            button.setOnClickListener(this)
        }

        // Calls the newGame function
        // to pass it the required parameters
        newGame(buttons, reset)
    }

    // takes the array of buttons and New Game button
    // and clears the buttons of text and sets the player turn to player1
    // effectively resetting the game
    private fun newGame(buttons: Array<Button>, resetButton: Button){
        resetButton.setOnClickListener {
            for (button in buttons) {
                button.text = ""
            }
            findViewById<TextView>(R.id.textView).text = getString(R.string.players_turn, player1.name)
            activePlayer = player1
        }
    }

    override fun onClick(v: View?) {
        // stores the button in the variable btnClicked
        val btnClicked = findViewById<Button>(v!!.id)

        // If there is no text present in the button add either an X or an O
        // and change player, else do nothing
        if(btnClicked.text != player1.symbol && btnClicked.text != player2.symbol) {
            if (activePlayer == player1) {
                btnClicked.text = player1.symbol
                findViewById<TextView>(R.id.textView).text = getString(R.string.players_turn, player2.name)
                activePlayer = player2

            } else {
                btnClicked.text = player2.symbol
                findViewById<TextView>(R.id.textView).text = getString(R.string.players_turn, player1.name)
                activePlayer = player1

            }
        }
    }
}






