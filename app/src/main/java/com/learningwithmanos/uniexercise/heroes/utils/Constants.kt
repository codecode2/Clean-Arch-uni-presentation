package com.learningwithmanos.uniexercise.heroes.utils



import com.learningwithmanos.uniexercise.MyApplication
import com.learningwithmanos.uniexercise.heroes.ui.MyPreferences
import java.math.BigInteger
import java.security.MessageDigest


class Constants {

    companion object {
        val URL: String = "https://gateway.marvel.com"
        val timeStamp = System.currentTimeMillis()
        val limit = 20
        val offset = 0


        fun hash(): String {
            val PUBLIC_KEY = MyApplication.preferences.getPublicKey()
            val PRIVATE_KEY = MyApplication.preferences.getPrivateKey()



            if (PUBLIC_KEY != null && PRIVATE_KEY != null) {
                val input = "$timeStamp$PRIVATE_KEY$PUBLIC_KEY"
                val md = MessageDigest.getInstance("MD5")
                return BigInteger(1, md.digest(input.toByteArray())).toString(16)
                    .padStart(32, '0')
            }
            return ""


        }
    }
}