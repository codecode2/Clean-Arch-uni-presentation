package com.learningwithmanos.uniexercise.heroes.usecase


import com.learningwithmanos.uniexercise.heroes.repo.UserKeysRepository
import javax.inject.Inject


interface SaveUserApiKeys  {
     fun saveUserApiKeys(publicKey:String,privateKey: String)
}

class SaveUserApiKeysImpl @Inject constructor(
    private val userKeysRepository: UserKeysRepository
): SaveUserApiKeys {

    override  fun saveUserApiKeys(publicKey:String, privateKey: String)
    {
       userKeysRepository.setPublicAndPrivateKey(publicKey,privateKey)
    }




}