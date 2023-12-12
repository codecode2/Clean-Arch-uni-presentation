package com.learningwithmanos.uniexercise.heroes.utils

import retrofit2.http.Url
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constants {

    companion object
    {
        const val URL: String = "https://gateway.marvel.com"
        val timeStamp = System.currentTimeMillis()
        const val API_KEY ="a9dbf8017fb93f3ed7c2d8c44aeb4ef9"
        const val PRIVATE_KEY="7e9f55c459bb0e0c78e17a72fc922d1e38c4d53f"
        const val limit = 20
        const val offset= 0

        fun hash():String {
            val input = "$timeStamp$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1,md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }


    }


}