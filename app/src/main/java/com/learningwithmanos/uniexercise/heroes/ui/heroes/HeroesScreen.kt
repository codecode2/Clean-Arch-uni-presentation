package com.learningwithmanos.uniexercise.heroes.ui.heroes

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.learningwithmanos.uniexercise.MyApplication
import com.learningwithmanos.uniexercise.heroes.data.Tab
import com.learningwithmanos.uniexercise.heroes.vm.heroes.HeroTileModel
import com.learningwithmanos.uniexercise.heroes.vm.heroes.HeroesViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroesScreen (
    navController: NavController,
    viewModel: HeroesViewModel = hiltViewModel()
) {


    val selectedTab = viewModel.selectedTabStateFlow.collectAsState()
    val heroesList = viewModel.heroesStateFlow.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Marvel Application") },
                actions = {
                    IconButton(onClick = { navController.navigate("keysInputScreen") }) {
                        Icon(Icons.Filled.Build, contentDescription = "Settings")
                    }
                }
            )
        }

    )
    { innerPadding ->
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

            LaunchedEffect(heroesList.value) {
                viewModel.errorHandler()
            }



            val errorMessage = viewModel.message.toString()
            if (errorMessage!=""){

                Text(
                    text = errorMessage,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center

                )

            }

        }
    }


}





@Composable
fun ShowHeroes(heroes: List<HeroTileModel>) {
    Column {
        heroes.forEach { hero ->
            // Row for each hero
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
            ) {
                // AsyncImage for hero image
                AsyncImage(
                    model = hero.imageUrl,
                    contentDescription = null,
                    modifier = Modifier.size(260.dp)
                )
                Spacer(modifier = Modifier.width(8.dp)) // Spacing between image and text
                // Text for hero title
                Text(text = hero.title)
            }
            Spacer(modifier = Modifier.height(60.dp)) // Spacing between each Row
        }
    }
}


