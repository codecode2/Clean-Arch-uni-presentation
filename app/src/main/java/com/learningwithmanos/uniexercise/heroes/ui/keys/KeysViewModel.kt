package com.learningwithmanos.uniexercise.heroes.ui.keys


import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.learningwithmanos.uniexercise.heroes.source.local.database.MarvelDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class KeysViewModel @Inject constructor(
    val marvelDao: MarvelDao

): ViewModel() {

    }



