package com.learningwithmanos.uniexercise.heroes.repo


import com.learningwithmanos.uniexercise.heroes.utils.sharedpreferences.MyPreferences
import javax.inject.Inject



interface UserKeysRepository {

     fun setPublicAndPrivateKey(publickey : String,privateKey: String)
     fun setOldPublicAndPrivateKey(publicKey: String,privateKey: String)
     fun getpublicKey():String
     fun getPrivateKey():String
     fun getOldpublicKey():String
     fun getOldPrivateKey():String
}





class UserKeysRepositoryImpl @Inject constructor(

    val myPreferences: MyPreferences


): UserKeysRepository {

   override  fun setPublicAndPrivateKey(publickey : String, privateKey: String)
    {
         setPublicKey(publickey)
         setPrivateKey(privateKey)

    }


    override  fun setOldPublicAndPrivateKey(publickey : String, privateKey: String)
    {
        setOldPublicKey(publickey)
        setOldPrivateKey(privateKey)

    }



   fun setPublicKey(publickey : String)
    {
        myPreferences.savePublicKey(publickey)
    }

    fun setPrivateKey(privatekey : String)
     {
         myPreferences.savePrivateKey(privatekey)
     }

    fun setOldPublicKey(oldPublicKey : String)
    {
        myPreferences.saveOldPublicKey(oldPublicKey)
    }

    fun setOldPrivateKey(privateOldkey : String)
    {
        myPreferences.saveoldPrivateKey(privateOldkey)
    }




   override   fun getpublicKey(): String
      {
          return myPreferences.getPublicKey().toString()
      }

    override   fun getPrivateKey():String
        {
            return   myPreferences.getPrivateKey().toString()
        }


    override  fun getOldpublicKey() : String
    {
        return  myPreferences.getOldPublicKey().toString()
    }

    override  fun getOldPrivateKey():String
    {
        return  myPreferences.getOldPrivateKey().toString()
    }




}