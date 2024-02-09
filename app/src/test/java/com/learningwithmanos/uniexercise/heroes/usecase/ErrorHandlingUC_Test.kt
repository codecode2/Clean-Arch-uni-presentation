package com.learningwithmanos.uniexercise.heroes.usecase

import com.learningwithmanos.uniexercise.heroes.repo.HeroRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`


@OptIn(ExperimentalCoroutinesApi::class)
class ErrorHandlingImplTest {

    private lateinit var errorHandlingImpl: ErrorHandlingImpl
    private val heroRepositoryMock: HeroRepository = mock(HeroRepository::class.java)

    @Before
    fun setUp() {
        errorHandlingImpl = ErrorHandlingImpl(heroRepositoryMock)
    }

    @Test
    fun `test execute calls errorHandlingHeros`() = runBlockingTest {
        // Given
        val expectedResource = Resource.Success(Unit)
        `when`(heroRepositoryMock.errorHandlingHeros()).thenReturn(expectedResource)

        // When
        val actualResource = errorHandlingImpl.execute()

        // Then
        verify(heroRepositoryMock).errorHandlingHeros()
        assert(actualResource == expectedResource)
    }
}
