package com.learningwithmanos.uniexercise.heroes.utils.constants



import android.util.Log
import com.learningwithmanos.uniexercise.MyApplication
import java.math.BigInteger
import java.security.MessageDigest
import com.learningwithmanos.uniexercise.heroes.repo.UserKeysRepository
import kotlinx.coroutines.runBlocking

class Constants {

    companion object {

        val timeStamp = System.currentTimeMillis()
        const val limit = 20

       var PUBLIC_KEY = MyApplication.preferences.getPublicKey()
       var PRIVATE_KEY =  MyApplication.preferences.getOldPrivateKey()


        fun hash(): String {

            runBlocking {
                PUBLIC_KEY = MyApplication.preferences.getPublicKey()
                PRIVATE_KEY =  MyApplication.preferences.getOldPrivateKey()
            }



            //a9dbf8017fb93f3ed7c2d8c44aeb4ef9
           //7e9f55c459bb0e0c78e17a72fc922d1e38c4d53f

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