package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.Array as Array1

class MainActivity : AppCompatActivity(), View.OnClickListener {
    // Created a player class to handle player information

    private val player1 = Player("Player X", "X", 0)
    private val player2 = Player("Player O", "O", 1)

    lateinit var playerPosition : IntArray
    lateinit var tv : TextView

    // Keeps track of which player's turn it is
    private var activePlayer = player1

    var gameOn = true


    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.mipmap.tac)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // -1 = empty space
        playerPosition = intArrayOf(-1, -1, -1, -1, -1, -1, -1, -1, -1)

        // Couldn't quite figure out how to hand this as a generic type
        // or use as a variable
        tv = findViewById(R.id.textView)
        tv.text = getString(R.string.players_turn, player1.name)

        val reset = findViewById<Button>(R.id.new_game)

        // Creates an array of buttons
        val buttons = arrayOf(
            findViewById(R.id.button0),
            findViewById(R.id.button1),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4),
            findViewById(R.id.button5),
            findViewById(R.id.button6),
            findViewById(R.id.button7),
            findViewById<Button>(R.id.button8)
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
    private fun newGame(buttons: Array1<Button>, resetButton: Button){
        resetButton.setOnClickListener {
            playerPosition = intArrayOf(-1, -1, -1, -1, -1, -1, -1, -1, -1)
            for (button in buttons) {
                button.text = ""
            }
            tv.text = getString(R.string.players_turn, player1.name)
            activePlayer = player1
            gameOn = true
        }
    }

    override fun onClick(v: View?) {
        if (!gameOn){
            return
        }
        // stores the button in the variable btnClicked
        val btnClicked = findViewById<Button>(v!!.id)
        var clickedTag = Integer.parseInt(btnClicked.tag.toString())

        // If there is no text present in the button add either an X or an O
        // and change player, else do nothing
        if(btnClicked.text != player1.symbol && btnClicked.text != player2.symbol) {
            if (activePlayer == player1) {
                // sets the text in the button to player 1's symbol
                btnClicked.text = player1.symbol
                // changes the player position array index from empty(-1), to the players position
                playerPosition[clickedTag] = player1.position
                tv.text = getString(R.string.players_turn, player2.name)
                activePlayer = player2

            } else {
                // sets the text in the button to player 2's symbol
                btnClicked.text = player2.symbol
                // changes the player position array index from empty(-1), to the players position
                playerPosition[clickedTag] = player2.position
                tv.text = getString(R.string.players_turn, player1.name)
                activePlayer = player1

            }
        }

        winOrTie()
    }

    private fun winOrTie() {
        var winningPosition = arrayOf(intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8), // row
                                    intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8), // column
                                    intArrayOf(0, 4, 8), intArrayOf(2, 4, 6)) // diagonal

        for (i in 0 until winningPosition.size){
            var val0 = winningPosition[i][0]
            var val1 = winningPosition[i][1]
            var val2 = winningPosition[i][2]

            // all three values have the same player position
            if (playerPosition[val0] == playerPosition[val1] && playerPosition[val1] == playerPosition[val2]){
                if (playerPosition[val0] != -1) {
                    gameOn = false
                    // if player one has three in a row
                    if (playerPosition[val0] == player1.position) {
                        tv.text = getString(R.string.player_wins, player1.name)
                    } else { // else player two has three in a row
                        tv.text = getString(R.string.player_wins, player2.name)
                    }
                    return
                }
            }
        }
        draw()
    }

    private fun draw() {
        var count = 0
        // if square is blank, increment count
        for (i in 0 until playerPosition.size) {
            if (playerPosition[i] == -1) {
                count++
            }
        }
        // if count is 0 all squares have a player symbol and it's a draw
        if (count == 0) {
            tv.text = getString(R.string.draw)
            return
        }
    }
}






