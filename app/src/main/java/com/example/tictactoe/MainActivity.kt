package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var player1 = 'X'
    private var player2 = 'O'
    private var activePlayer = player1
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.mipmap.tac)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val reset = findViewById<Button>(R.id.new_game)
        val buttons = arrayOf(
            findViewById<Button>(R.id.button1),
            findViewById<Button>(R.id.button2),
            findViewById<Button>(R.id.button3),
            findViewById<Button>(R.id.button4),
            findViewById<Button>(R.id.button5),
            findViewById<Button>(R.id.button6),
            findViewById<Button>(R.id.button7),
            findViewById<Button>(R.id.button8),
            findViewById<Button>(R.id.button9)
        )

        for(button in buttons){
            button.setOnClickListener(this)
        }



    }

    override fun onClick(v: View?) {
        var btnClicked = findViewById<Button>(v!!.id)

        if(btnClicked.text != "X" && btnClicked.text != "O") {
            if (activePlayer.equals(player1)) {
                btnClicked.setText("X")
                activePlayer = player2
            } else {
                btnClicked.setText("O")
                activePlayer = player1
            }
        }
    }
}






