package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel:ViewModel() {
    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    private var _score = MutableLiveData<Int>()
    val score: LiveData<Int>
    get()= _score
    lateinit var wordList: MutableList<String>
    init {
        Log.i("GameViewModel class","viewmodel is created")
        resetList()
        nextWord()
        _score.value=0
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
            _word.value = wordList.removeAt(0)
        }

    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel class","viewmodel is destroyed")
    }
  fun onSkip() {
        _score.value= (score.value)?.minus(1)
        nextWord()
    }

  fun onCorrect() {
        _score.value=(score.value)?.plus(1)
        nextWord()
    }

}