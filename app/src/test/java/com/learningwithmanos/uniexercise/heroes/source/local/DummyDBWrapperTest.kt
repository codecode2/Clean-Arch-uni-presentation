package com.learningwithmanos.uniexercise.heroes.source.local

import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.source.local.DummyDBWrapper
import com.learningwithmanos.uniexercise.heroes.source.local.data.LHero
import com.learningwithmanos.uniexercise.heroes.source.local.database.MarvelDao
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class DummyDBWrapperTest {

    private lateinit var marvelDao: MarvelDao
    private lateinit var dummyDBWrapper: DummyDBWrapper

    @Before
    fun setUp() {
        marvelDao = mock(MarvelDao::class.java)
        dummyDBWrapper = DummyDBWrapper(marvelDao)
    }

    @Test
    fun testIsHeroDataStored() = runBlockingTest {
        val heroes = listOf(LHero(1, "Hero1", 5, "url1"))
        given(marvelDao.getAllHeroes()).willReturn(flowOf(heroes))
        val result = dummyDBWrapper.isHeroDataStored().toList().first()
        assertEquals(true, result)
    }

    @Test
    fun testStoreHeroes() = runBlockingTest {
        val heroes = listOf(Hero(1, "Hero1", 5, "url1"))

        dummyDBWrapper.storeHeroes(heroes)
        verify(marvelDao).insertCharacterList(heroes.map { it.mapToLHero() })
    }

    @Test
    fun testGetHeroes() = runBlockingTest {
        val heroes = listOf(LHero(1, "Hero1", 5, "url1"))
        given(marvelDao.getAllHeroes()).willReturn(flowOf(heroes))
        val result = dummyDBWrapper.getHeroes().toList().first()
        assertEquals(heroes.map { it.mapToHero() }, result)
    }

    @Test
    fun testDeleteHeroes() = runBlockingTest {
        dummyDBWrapper.deleteHeroes()
        verify(marvelDao).deleteAllHeroes()
    }
}
