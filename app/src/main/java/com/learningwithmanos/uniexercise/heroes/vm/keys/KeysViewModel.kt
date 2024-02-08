package com.learningwithmanos.uniexercise.heroes.vm.keys



import androidx.lifecycle.ViewModel
import com.learningwithmanos.uniexercise.heroes.repo.HeroRepository
import com.learningwithmanos.uniexercise.heroes.repo.UserKeysRepository
import com.learningwithmanos.uniexercise.heroes.source.local.DBWrapper
import com.learningwithmanos.uniexercise.heroes.usecase.GetUserApiKeys
import com.learningwithmanos.uniexercise.heroes.usecase.GetUserApiKeysImpl
import com.learningwithmanos.uniexercise.heroes.usecase.KeysChanged
import com.learningwithmanos.uniexercise.heroes.usecase.SaveUserApiKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltViewModel
class KeysViewModel @Inject constructor(
    private val saveUserApiKeys: SaveUserApiKeys,
    private val keysChanged: KeysChanged,
    private val getUserApiKeys: GetUserApiKeys
): ViewModel()

{

        fun saveApiKeys(publicKey:String, privateKey: String)
        {

                 saveUserApiKeys.saveUserApiKeys(publicKey,privateKey)

             }


    fun ifTheKeysChanged(publicKey:String , privateKey: String)
    {
       runBlocking {
            launch {
                keysChanged.checkKeysIfChanged(publicKey,privateKey)
            }
        }
    }

      fun getPublicKey():String
    {
      return  getUserApiKeys.getPublicKey().toString()

    }

    fun getPrivateKey():String
    {
        return getUserApiKeys.getPrivateKey().toString()

    }




}



