package com.learningwithmanos.uniexercise.heroes.ui.keys


import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class KeysViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences

): ViewModel() {

    }



