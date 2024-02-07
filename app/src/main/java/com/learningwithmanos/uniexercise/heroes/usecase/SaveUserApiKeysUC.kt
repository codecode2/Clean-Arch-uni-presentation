package com.learningwithmanos.uniexercise.heroes.usecase


import com.learningwithmanos.uniexercise.heroes.repo.UserKeysRepository
import javax.inject.Inject


interface SaveUserApiKeys  {
    suspend fun saveUserApiKeys(publicKey:String,privateKey: String)
}

class SaveUserApiKeysImpl @Inject constructor(
    private val userKeysRepository: UserKeysRepository
): SaveUserApiKeys {

    override suspend fun saveUserApiKeys(publicKey:String, privateKey: String)
    {
       userKeysRepository.setPublicAndPrivateKey(publicKey,privateKey)
    }




}