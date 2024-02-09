package com.learningwithmanos.uniexercise.heroes.usecase

import com.learningwithmanos.uniexercise.heroes.repo.UserKeysRepository
import javax.inject.Inject

interface GetUserApiKeys
{
     fun getPublicKey():String?
     fun getPrivateKey():String?

}

class GetUserApiKeysImpl @Inject constructor(
   val userKeysRepository: UserKeysRepository


): GetUserApiKeys
{
    override   fun getPublicKey():String? {

        return userKeysRepository.getpublicKey()

    }



    override  fun getPrivateKey():String? {
      return userKeysRepository.getPrivateKey()

    }



}