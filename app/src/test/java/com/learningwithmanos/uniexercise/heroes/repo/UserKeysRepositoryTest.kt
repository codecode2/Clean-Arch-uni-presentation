package com.learningwithmanos.uniexercise.heroes.repo

import com.learningwithmanos.uniexercise.heroes.utils.sharedpreferences.MyPreferences
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations



@ExperimentalCoroutinesApi
class UserKeysRepositoryImplTest {

    @Mock
    private lateinit var myPreferencesMock: MyPreferences

    private lateinit var userKeysRepository: UserKeysRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        userKeysRepository = UserKeysRepositoryImpl(myPreferencesMock)
    }

    @Test
    fun `test setPublicAndPrivateKey`() = runBlockingTest {
        // given
        val publicKey = "publicKey"
        val privateKey = "privateKey"

        // when
        userKeysRepository.setPublicAndPrivateKey(publicKey, privateKey)

        // then
        verifySetPublicKeyCalled(publicKey)
        verifySetPrivateKeyCalled(privateKey)
    }

    @Test
    fun `test setOldPublicAndPrivateKey`() = runBlockingTest {
        // given
        val oldPublicKey = "oldPublicKey"
        val oldPrivateKey = "oldPrivateKey"

        // when
        userKeysRepository.setOldPublicAndPrivateKey(oldPublicKey, oldPrivateKey)

        // then
        verifySetOldPublicKeyCalled(oldPublicKey)
        verifySetOldPrivateKeyCalled(oldPrivateKey)
    }

    private fun verifySetPublicKeyCalled(publicKey: String) {
        verify(myPreferencesMock).savePublicKey(publicKey)
    }

    private fun verifySetPrivateKeyCalled(privateKey: String) {
        verify(myPreferencesMock).savePrivateKey(privateKey)
    }

    private fun verifySetOldPublicKeyCalled(oldPublicKey: String) {
        verify(myPreferencesMock).saveOldPublicKey(oldPublicKey)
    }

    private fun verifySetOldPrivateKeyCalled(oldPrivateKey: String) {
        verify(myPreferencesMock).saveoldPrivateKey(oldPrivateKey)
    }

    @Test
    fun `test getStoredPublicAndPrivateKeys`() = runTest {
        // given
        val publicKey = "publicKey"
        val privateKey = "privateKey"

        // mock the behavior of MyPreferences
        `when`(myPreferencesMock.getPublicKey()).thenReturn(publicKey)
        `when`(myPreferencesMock.getPrivateKey()).thenReturn(privateKey)

        // when
        val storedPublicKey = userKeysRepository.getpublicKey()
        val storedPrivateKey = userKeysRepository.getPrivateKey()

        // then
        assertEquals(publicKey, storedPublicKey)
        assertEquals(privateKey, storedPrivateKey)
    }

}