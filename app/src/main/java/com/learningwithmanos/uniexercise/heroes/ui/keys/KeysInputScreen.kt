package com.learningwithmanos.uniexercise.heroes.presenatation.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.learningwithmanos.uniexercise.MyApplication
import com.learningwithmanos.uniexercise.heroes.source.local.DBWrapper
import com.learningwithmanos.uniexercise.heroes.source.local.DummyDBWrapper
import com.learningwithmanos.uniexercise.heroes.source.local.database.MarvelDao
import com.learningwithmanos.uniexercise.heroes.source.remote.RestFrameworkWrapper

import com.learningwithmanos.uniexercise.heroes.ui.keys.KeysViewModel
import com.learningwithmanos.uniexercise.heroes.utils.MyPreferences
import com.learningwithmanos.uniexercise.heroes.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeysInputScreen(

    navController: NavController,
    viewModel: KeysViewModel = hiltViewModel()
) {

    val context = LocalContext.current // Get the current context
    val myPreferences = MyPreferences(context) // Create an instance of MyPreferences
    val publicKeyInput = myPreferences.getPublicKey()
    val privateKeyInput = myPreferences.getPrivateKey()
    val dbWrapper: DBWrapper = DummyDBWrapper(viewModel.marvelDao)




    Scaffold(

        topBar = {
            TopAppBar(
                title = { Text("Keys") },
                actions = {
                IconButton(onClick = { navController.navigate("heroesScreen") }) {
                    Icon(Icons.Filled.Settings, contentDescription = "Settings")
                }
            }
        )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            var publicKey by remember { mutableStateOf("$publicKeyInput") }

            OutlinedTextField(
                value = publicKey,
                onValueChange = { newValue -> publicKey = newValue },
                label = { Text("Public Key") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )


            Spacer(modifier = Modifier.height(4.dp))


            var privateKey by remember { mutableStateOf("$privateKeyInput") }

            OutlinedTextField(
                value = privateKey,
                onValueChange = { newValue -> privateKey = newValue },
                label = { Text("Private Key") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Button(
                onClick = {
                    val publicKeyStored =  MyApplication.preferences.getPublicKey()
                    val privateKeyStored =  MyApplication.preferences.getPrivateKey()

                    if(publicKey.toString() != publicKeyStored.toString() || privateKey.toString() != privateKeyStored.toString()) {
                        CoroutineScope(Dispatchers.IO).launch {
                            dbWrapper.deleteHeroes()
                        }
                    }

                    myPreferences.savePublicKey("$publicKey")
                    myPreferences.savePrivateKey("$privateKey")

                    if (publicKey==publicKeyInput){}

                    if (publicKey !="" && privateKey !="") {
                        Constants.hash()
                    }

                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = publicKey.isNotEmpty() && privateKey.isNotEmpty() // Disable the button if publicKey or privateKey is empty
            ) {
                Text("Save")
            }
        }
    }
}

