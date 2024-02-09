package com.learningwithmanos.uniexercise.heroes.usecase

import com.learningwithmanos.uniexercise.heroes.repo.UserKeysRepository
import com.learningwithmanos.uniexercise.heroes.source.local.DBWrapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

@OptIn(ExperimentalCoroutinesApi::class)
class KeysChangedTest {

    private lateinit var keysChangedImpl: KeysChangedImpl
    private val userKeysRepositoryMock: UserKeysRepository = mock(UserKeysRepository::class.java)
    private val dbWrapperMock: DBWrapper = mock(DBWrapper::class.java)

    @Before
    fun setUp() {
        keysChangedImpl = KeysChangedImpl(userKeysRepositoryMock, dbWrapperMock)
    }

    @Test
    fun `test checkKeysIfChanged when old keys are null`() = runBlockingTest {
        // Given
        val currentPublicKey = "publicKey"
        val currentPrivateKey = "privateKey"
        `when`(userKeysRepositoryMock.getOldpublicKey()).thenReturn(null)
        `when`(userKeysRepositoryMock.getOldPrivateKey()).thenReturn(null)

        // When
        keysChangedImpl.checkKeysIfChanged(currentPublicKey, currentPrivateKey)

        // Then
        verify(userKeysRepositoryMock).setOldPublicAndPrivateKey(currentPublicKey, currentPrivateKey)
        suspend {  verifyNoMoreInteractions(dbWrapperMock) }
    }

    @Test
    fun `test checkKeysIfChanged when keys are changed`() = runBlockingTest {
        // Given
        val currentPublicKey = "newPublicKey"
        val currentPrivateKey = "newPrivateKey"
        val oldPublicKey = "oldPublicKey"
        val oldPrivateKey = "oldPrivateKey"
        `when`(userKeysRepositoryMock.getOldpublicKey()).thenReturn(oldPublicKey)
        `when`(userKeysRepositoryMock.getOldPrivateKey()).thenReturn(oldPrivateKey)

        // When
        keysChangedImpl.checkKeysIfChanged(currentPublicKey, currentPrivateKey)

        // Then
        verify(userKeysRepositoryMock).setOldPublicAndPrivateKey(currentPublicKey, currentPrivateKey)
        verify(dbWrapperMock).deleteHeroes()
    }
}
