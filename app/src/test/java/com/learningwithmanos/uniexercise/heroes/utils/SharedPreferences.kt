package com.learningwithmanos.uniexercise.heroes.utils


import com.learningwithmanos.uniexercise.heroes.utils.sharedpreferences.MyPreferences
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.test.runTest
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`






    class MyPreferencesTest {

        private lateinit var myPreferences: MyPreferences
        private lateinit var sharedPrefs: SharedPreferences
        private lateinit var editor: SharedPreferences.Editor

        @Before
        fun setUp() {
            // Mock the context, SharedPreferences and Editor
            val context = mock(Context::class.java)
            sharedPrefs = mock(SharedPreferences::class.java)
            editor = mock(SharedPreferences.Editor::class.java)

            // When getSharedPreferences is called on the context, return the mocked SharedPreferences
            `when`(context.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPrefs)

            // When edit is called on the sharedPrefs, return the mocked Editor
            `when`(sharedPrefs.edit()).thenReturn(editor)

            // When putString is called on the editor, return the editor
            `when`(editor.putString(anyString(), anyString())).thenReturn(editor)

            myPreferences = MyPreferences(context)
        }


        @Test
    fun testSaveAndRetrieveKeys()=runTest {
        // Save keys
        myPreferences.savePublicKey("publicKey123")
        myPreferences.savePrivateKey("privateKey456")

        // Retrieve keys
        val publicKey = myPreferences.getPublicKey()
        val privateKey = myPreferences.getPrivateKey()

        // Assert that the retrieved values match the saved values
        assertEquals("publicKey123", publicKey)
        assertEquals("privateKey456", privateKey)
    }





    @Test
    fun testSaveAndRetrieveOldKeys() =runTest {
        // Save old keys
        myPreferences.saveOldPublicKey("oldPublicKey789")
        myPreferences.saveoldPrivateKey("oldPrivateKey012")

        // Retrieve old keys
        val oldPublicKey = myPreferences.getOldPublicKey()
        val oldPrivateKey = myPreferences.getOldPrivateKey()

        // Assert that the retrieved values match the saved values
        assertEquals("oldPublicKey789", oldPublicKey)
        assertEquals("oldPrivateKey012", oldPrivateKey)
    }
}

