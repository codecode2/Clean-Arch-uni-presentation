package com.learningwithmanos.uniexercise.heroes.usecase

import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.repo.HeroRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@OptIn(ExperimentalCoroutinesApi::class)
class GetHeroesSortedByNameUCImplTest {

    private lateinit var getHeroesSortedByNameUCImpl: GetHeroesSortedByNameUCImpl

    private val heroRepositoryMock: HeroRepository = mock()

    @Before
    fun setUp() {
        getHeroesSortedByNameUCImpl = GetHeroesSortedByNameUCImpl(
            heroRepositoryMock
        )
    }

    @Test
    fun `when execute is invoked then verify interactions`()= runTest{
        // given
        val heroesList = dummyHeroList()
        given(heroRepositoryMock.getHeroes()).willReturn(flowOf(heroesList))
        val expectedHeroesList = sortHeroListByName(heroesList)

        // when
        getHeroesSortedByNameUCImpl.execute().collect { actual ->
            // then
            assertThat(actual, equalTo(expectedHeroesList))
            verify(heroRepositoryMock).getHeroes()
        }

    }

    private fun dummyHeroList(): List<Hero> {
        return listOf(
            Hero(
                id=7,
                name = "Absorbing Man",
                availableComics = 99,
                imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/1/b0/5269678709fb7.jpg",
            ),
            Hero(
                id=10,
                name = "3-D Man",
                availableComics = 12,
                imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg",
            ),
            Hero(
                id=7,
                name = "Aaron Stack",
                availableComics = 14,
                imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg",
            ),
            Hero(
                id=8,
                name = "A.I.M.",
                availableComics = 53,
                imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/6/20/52602f21f29ec.jpg",
            )
        )
    }

    private fun sortHeroListByName(heroesList: List<Hero>): List<Hero> {
        return heroesList.sortedBy { it.name }
    }

}