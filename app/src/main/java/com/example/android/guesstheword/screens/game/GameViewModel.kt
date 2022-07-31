package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
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

    private val _eventGameFinished = MutableLiveData<Boolean>()
    val eventGameFinished:LiveData<Boolean>
    get()=_eventGameFinished
    lateinit var wordList: MutableList<String>
    companion object {
        // These represent different important times in the game, such as game length.

        // This is when the game is over
        private const val DONE = 0L

        // This is the number of milliseconds in a second
        private const val ONE_SECOND = 1000L

        // This is the total time of the game
        private const val COUNTDOWN_TIME = 10000L

    }
    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime
    private val timer: CountDownTimer
    init {
        Log.i("GameViewModel class","viewmodel is created")
        _eventGameFinished.value=false
        resetList()
        nextWord()
        _score.value=0
         timer=object :CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
             override fun onTick(p0: Long) {
                 _currentTime.value = (p0 / ONE_SECOND)
             }

             override fun onFinish() {
                 _currentTime.value = DONE
                 _eventGameFinished.value = true
             }
         }
        timer.start()



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
            _eventGameFinished.value=true
            //gameFinished()
        } else {
            _word.value = wordList.removeAt(0)
        }

    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
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

    fun onGameFinished()
    {
        _eventGameFinished.value=false
    }
}