package com.learningwithmanos.uniexercise.heroes.ui

import com.learningwithmanos.uniexercise.heroes.usecase.GetUserApiKeys
import com.learningwithmanos.uniexercise.heroes.usecase.KeysChanged
import com.learningwithmanos.uniexercise.heroes.usecase.SaveUserApiKeys
import com.learningwithmanos.uniexercise.heroes.vm.keys.KeysViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class KeysViewModelTest {

    private lateinit var saveUserApiKeys: SaveUserApiKeys
    private lateinit var keysChanged: KeysChanged
    private lateinit var getUserApiKeys: GetUserApiKeys
    private lateinit var keysViewModel: KeysViewModel

    @Before
    fun setUp() {
        saveUserApiKeys = mock()
        keysChanged = mock()
        getUserApiKeys = mock()
        keysViewModel = KeysViewModel(saveUserApiKeys, keysChanged, getUserApiKeys)
    }

    @Test
    fun testSaveApiKeys() = runBlockingTest {
        val publicKey = "publicKey123"
        val privateKey = "privateKey456"

        keysViewModel.saveApiKeys(publicKey, privateKey)
        verify(saveUserApiKeys).saveUserApiKeys(publicKey, privateKey)
    }

    @Test
    fun testIfTheKeysChanged() = runBlockingTest {
        val publicKey = "publicKey123"
        val privateKey = "privateKey456"

        keysViewModel.ifTheKeysChanged(publicKey, privateKey)
        verify(keysChanged).checkKeysIfChanged(publicKey, privateKey)
    }

    @Test
    fun testGetPublicKey() {
        keysViewModel.getPublicKey()
        verify(getUserApiKeys).getPublicKey()
    }

    @Test
    fun testGetPrivateKey() {
        keysViewModel.getPrivateKey()
        verify(getUserApiKeys).getPrivateKey()
    }
}

