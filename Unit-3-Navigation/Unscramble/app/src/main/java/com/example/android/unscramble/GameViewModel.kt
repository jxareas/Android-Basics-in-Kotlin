package com.example.android.unscramble

import androidx.lifecycle.ViewModel
import com.example.android.unscramble.ui.game.allWordsList

class GameViewModel : ViewModel() {

    private var score = 0
    private var _currentWordCount = 0
    private lateinit var _currentScrambledWord : String
    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    val currentScrambledWord : String
        get() = _currentScrambledWord

    init {
        getNextWord()
    }

    private fun getNextWord() {
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()

        while (String(tempWord).equals(currentWord, false)) {
            tempWord.shuffle()
        }
        if (wordsList.contains(currentWord)) {
            getNextWord()
        } else {
            _currentScrambledWord = String(tempWord)
            ++_currentWordCount
            wordsList.add(currentWord)
        }
    }


}