package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel:ViewModel() {
    var word = ""
     var score = 0
    lateinit var wordList: MutableList<String>
    init {
        Log.i("GameViewModel class","viewmodel is created")
        resetList()
        nextWord()
    }
    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            //gameFinished()
        } else {
            word = wordList.removeAt(0)
        }

    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel class","viewmodel is destroyed")
    }
  fun onSkip() {
        score--
        nextWord()
    }

  fun onCorrect() {
        score++
        nextWord()
    }

}