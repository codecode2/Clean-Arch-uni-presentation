package com.learningwithmanos.uniexercise.heroes.ui

import android.content.Context


class MyPreferences(context: Context)  {
    private val sharedPreferences = context.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)

    fun savePublicKey(publicKey: String) {
        val editor = sharedPreferences.edit()
        editor.putString("publicKey", publicKey)
        editor.apply()
    }

    fun savePrivateKey(privateKey: String) {
        val editor = sharedPreferences.edit()
        editor.putString("privateKey", privateKey)
        editor.apply()
    }


    fun getPublicKey(): String? {
        return sharedPreferences.getString("publicKey", null)
    }

    fun getPrivateKey(): String? {
        return sharedPreferences.getString("privateKey", null)
    }

}
