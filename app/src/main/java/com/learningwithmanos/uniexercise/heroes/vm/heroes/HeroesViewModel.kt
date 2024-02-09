package com.learningwithmanos.uniexercise.heroes.vm.heroes

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.data.Tab
import com.learningwithmanos.uniexercise.heroes.usecase.ErrorHandling
import com.learningwithmanos.uniexercise.heroes.usecase.ErrorHandlingImpl
import com.learningwithmanos.uniexercise.heroes.usecase.GetHeroesSortedByHighestNumberOfComicsUC
import com.learningwithmanos.uniexercise.heroes.usecase.GetHeroesSortedByNameUC
import com.learningwithmanos.uniexercise.heroes.usecase.GetHeroesUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroesViewModel @Inject constructor(
    private val getHeroesUC: GetHeroesUC,
    private val getHeroesSortedByNameUC: GetHeroesSortedByNameUC,
    private val getHeroesSortedByHighestNumberOfComicsUC: GetHeroesSortedByHighestNumberOfComicsUC,
    private val ErrorHandlingImpl: ErrorHandlingImpl

) : ViewModel() {

    var list by mutableStateOf("")
        private set

    var message by mutableStateOf<String?>(null)




    private var _selectedTabStateFlow: MutableStateFlow<Tab> = MutableStateFlow(Tab.Heroes)

    val selectedTabStateFlow: StateFlow<Tab> = _selectedTabStateFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = _selectedTabStateFlow.value
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    val heroesStateFlow: StateFlow<List<HeroTileModel>> = selectedTabStateFlow.flatMapLatest { selectedTab ->
        when (selectedTab) {
            Tab.Heroes -> getHeroesUC.execute().map { list -> list.map { it.mapHeroToHeroTileModel() }}
            Tab.SortedByNameHeroes -> getHeroesSortedByNameUC.execute()
                .map { list -> list.map { it.mapHeroToHeroTileModel() }}

            Tab.SortedByComicHeroes -> getHeroesSortedByHighestNumberOfComicsUC.execute()
                .map { list -> list.map { it.mapHeroToHeroTileModel() }}
        }
    }.catch {e->

        Log.e("HeroesViewModel", "Error getting heroes: ${e.message}")
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = listOf()
    )

    /**
     * Utilises corresponding UC to retrieve data based on the selectedTab.
     * @param selectedTab
     */
    fun getSelectedIndex(selectedTab: Tab): Int {
        return when (selectedTab) {
            Tab.Heroes -> 0
            Tab.SortedByNameHeroes -> 1
            Tab.SortedByComicHeroes -> 2
        }
    }

    /**
     * Sets the selected tab
     */
    fun selectTab(tab: Tab) {
        _selectedTabStateFlow.value = tab
    }



    fun errorHandler() {
        viewModelScope.launch {
            val result = ErrorHandlingImpl.execute()
            message = when(result) {
                is Resource.Success -> ({
                    Resource.Success("")
                }).toString()
                is Resource.Error -> {
                    result.message.toString()
                }
                else -> ({"Error handler"}).toString()
            }
        }
    }
}


data class HeroTileModel(
    val title: String,
    val imageUrl: String,
)

fun Hero.mapHeroToHeroTileModel(): HeroTileModel {
    return HeroTileModel(
        title = "$name,\n comics - $availableComics",
        imageUrl = imageUrl


    )
}