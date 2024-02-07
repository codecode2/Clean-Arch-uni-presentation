package com.learningwithmanos.uniexercise.heroes.utils.sharedpreferences

import android.content.Context


class MyPreferences(context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)


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


    fun getOldPublicKey(): String? {
        return sharedPreferences.getString("oldPublicKey", null)
    }

    fun getOldPrivateKey(): String? {
        return sharedPreferences.getString("oldPrivateKey", null)
    }


    fun getPublicKey(): String? {
        return sharedPreferences.getString("publicKey", null)
    }

    fun getPrivateKey(): String? {
        return sharedPreferences.getString("privateKey", null)
    }


    fun getErrorMessage(): String? {
        return sharedPreferences.getString("errorMessage", null)
    }



    fun makeErrorMessageNull ()
    {
        val editor = sharedPreferences.edit()
        editor.putString("errorMessage",null)
        editor.apply()

    }

    fun saveErrorMessage(message: String?) {
        val editor = sharedPreferences.edit()
        editor.putString("errorMessage", message)
        editor.apply()
    }






}