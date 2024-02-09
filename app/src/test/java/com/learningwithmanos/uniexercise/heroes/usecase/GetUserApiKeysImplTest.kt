package com.learningwithmanos.uniexercise.heroes.usecase

import com.learningwithmanos.uniexercise.heroes.repo.UserKeysRepository
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`


class GetUserApiKeysImplTest {

    private lateinit var getUserApiKeys: GetUserApiKeysImpl
    private val userKeysRepositoryMock: UserKeysRepository = mock(UserKeysRepository::class.java)

    @Before
    fun setUp() {
        getUserApiKeys = GetUserApiKeysImpl(userKeysRepositoryMock)
    }

    @Test
    fun `test getPublicKey returns correct key`() {
        // Given
        val expectedKey = "publicKey"
        `when`(userKeysRepositoryMock.getpublicKey()).thenReturn(expectedKey)

        // When
        val actualKey = getUserApiKeys.getPublicKey()

        // Then
        assertEquals(expectedKey, actualKey)
    }

    @Test
    fun `test getPrivateKey returns correct key`() {
        // Given
        val expectedKey = "privateKey"
        `when`(userKeysRepositoryMock.getPrivateKey()).thenReturn(expectedKey)

        // When
        val actualKey = getUserApiKeys.getPrivateKey()

        // Then
        assertEquals(expectedKey, actualKey)
    }
}
