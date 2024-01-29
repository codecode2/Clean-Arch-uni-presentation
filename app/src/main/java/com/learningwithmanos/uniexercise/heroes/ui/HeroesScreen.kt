package com.learningwithmanos.uniexercise.heroes.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.learningwithmanos.uniexercise.heroes.data.Tab





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroesScreen(
    viewModel: HeroesViewModel = hiltViewModel()
) {
    val selectedTab = viewModel.selectedTabStateFlow.collectAsState()
    val heroesList = viewModel.heroesStateFlow.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Heroes") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.Build,
                        contentDescription = "Menu",
                        modifier = Modifier.clickable { /* Handle navigation icon click */ }
                    )
                }

            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            TabRow(selectedTabIndex = viewModel.getSelectedIndex(selectedTab.value)) {
                Text(
                    modifier = Modifier.clickable { viewModel.selectTab(Tab.Heroes) },
                    textAlign = TextAlign.Center,
                    text = "Heroes"
                )
                Text(
                    modifier = Modifier.clickable { viewModel.selectTab(Tab.SortedByNameHeroes) },
                    textAlign = TextAlign.Center,
                    text = "A-Z Heroes"
                )
                Text(
                    modifier = Modifier.clickable { viewModel.selectTab(Tab.SortedByComicHeroes) },
                    textAlign = TextAlign.Center,
                    text = "Heroes by Comic"
                )
            }

            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                ShowHeroes(heroes = heroesList.value)
            }
        }
    }


}





@Composable
fun ShowHeroes(heroes: List<HeroTileModel>) {
    Column {
        heroes.forEach { hero ->
            Row(

                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
            ) {
                AsyncImage(

                    model = hero.imageUrl,
                    contentDescription = null,
                    modifier = Modifier.size(260.dp)// Adjust this value to make the image bigger or smaller
                )
                Spacer(modifier = Modifier.width(8.dp)) // This adds space between the image and the text
                Text(text = hero.title+"")
            }
            Spacer(modifier = Modifier.height(60.dp)) // This adds space between each Row
        }
    }
}
