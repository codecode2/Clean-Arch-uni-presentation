package com.learningwithmanos.uniexercise.heroes.usecase



import android.util.Log
import com.learningwithmanos.uniexercise.heroes.repo.UserKeysRepository
import com.learningwithmanos.uniexercise.heroes.source.local.DBWrapper
import javax.inject.Inject

interface KeysChanged
{
    suspend fun checkKeysIfChanged(currentPublic:String? , currentPrivateKey: String?)

}

class KeysChangedImpl @Inject constructor(
    val userKeysRepository: UserKeysRepository,
    val dbWrapper: DBWrapper

): KeysChanged

{
    override suspend fun checkKeysIfChanged(currentPublic:String? , currentPrivateKey: String?)
    {
            val currentPublicKey: String? = currentPublic
            val currentPrivateKey: String? = currentPrivateKey
            val oldPublicKey : String? = userKeysRepository.getOldpublicKey().toString()
            val oldPrivateKey: String? = userKeysRepository.getOldPrivateKey().toString()



        if (oldPublicKey==null || oldPrivateKey==null){

            userKeysRepository.setOldPublicAndPrivateKey(currentPublicKey,currentPrivateKey)

        }

        else if(currentPublicKey!=oldPublicKey || currentPrivateKey!= oldPrivateKey)
        {
            userKeysRepository.setOldPublicAndPrivateKey(currentPublicKey,currentPrivateKey)
            dbWrapper.deleteHeroes()
            Log.e("DELETING HEROES","is empty")

        }
    }
}