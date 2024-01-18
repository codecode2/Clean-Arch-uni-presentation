package com.learningwithmanos.uniexercise.heroes.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.learningwithmanos.uniexercise.heroes.data.Tab

@Composable
fun HeroesScreen(
    viewModel: HeroesViewModel = hiltViewModel()
) {

    val selectedTab = viewModel.selectedTabStateFlow.collectAsState()
    val heroesList = viewModel.heroesStateFlow.collectAsState()

    Column {
        TabRow(selectedTabIndex = viewModel.getSelectedIndex(selectedTab.value)) {
            Text(modifier = Modifier.clickable { viewModel.selectTab(Tab.Heroes) }, textAlign = TextAlign.Center, text = "Heroes")
            Text(modifier = Modifier.clickable { viewModel.selectTab(Tab.SortedByNameHeroes) }, textAlign = TextAlign.Center, text = "A-Z Heroes")
            Text(modifier = Modifier.clickable { viewModel.selectTab(Tab.SortedByComicHeroes) }, textAlign = TextAlign.Center, text = "Heroes by Comic")
        }

        Column {
            ShowHeroes(heroes = heroesList.value)

        }
    }
}

@Composable
fun ShowHeroes(heroes: List<HeroTileModel>) {
    heroes.forEach { 
        Text(text = it.title)
    }
}