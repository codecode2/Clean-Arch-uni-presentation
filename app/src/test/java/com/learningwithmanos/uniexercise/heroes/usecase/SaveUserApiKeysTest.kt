package com.learningwithmanos.uniexercise.heroes.usecase

import com.learningwithmanos.uniexercise.heroes.repo.UserKeysRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify



class SaveUserApiKeysTest {

    private lateinit var saveUserApiKeys: SaveUserApiKeysImpl
    private val userKeysRepositoryMock: UserKeysRepository = mock(UserKeysRepository::class.java)

    @Before
    fun setUp() {
        saveUserApiKeys = SaveUserApiKeysImpl(userKeysRepositoryMock)
    }

    @Test
    fun `test saveUserApiKeys saves keys`() {
        // Given
        val publicKey = "publicKey"
        val privateKey = "privateKey"

        // When
        saveUserApiKeys.saveUserApiKeys(publicKey, privateKey)

        // Then
        verify(userKeysRepositoryMock).setPublicAndPrivateKey(publicKey, privateKey)
    }
}