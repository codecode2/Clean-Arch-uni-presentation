package com.learningwithmanos.uniexercise.heroes.utils

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


    fun saveOldPublicKey(oldPublicKey: String) {
        val editor = sharedPreferences.edit()
        editor.putString("oldPublicKey", oldPublicKey)
        editor.apply()
    }

    fun saveoldPrivateKey(oldPrivateKey: String) {
        val editor = sharedPreferences.edit()
        editor.putString("oldPrivateKey", oldPrivateKey)
        editor.apply()
    }





    fun getPublicKey(): String? {
        return sharedPreferences.getString("publicKey", null)
    }

    fun getPrivateKey(): String? {
        return sharedPreferences.getString("privateKey", null)
    }


    fun checkIfHasChanged(publicKey: String,privateKey: String)
    {



    }



}
