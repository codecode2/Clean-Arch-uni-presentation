package com.learningwithmanos.uniexercise.heroes.ui.keys

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.learningwithmanos.uniexercise.MyApplication
import com.learningwithmanos.uniexercise.heroes.utils.sharedpreferences.MyPreferences
import com.learningwithmanos.uniexercise.heroes.vm.keys.KeysViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeysInputScreen(

    navController: NavController,
    viewModel: KeysViewModel = hiltViewModel()
) {


    val publicKeyInput = viewModel.getPublicKey()
    val privateKeyInput = viewModel.getPrivateKey()

    var publicKey by remember { mutableStateOf("$publicKeyInput") }
    var privateKey by remember { mutableStateOf("$privateKeyInput") }

    Log.d("VIEW MODEL", "$publicKey with $publicKeyInput  $privateKeyInput $privateKey")


    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("heroesScreen") }) {
                        Icon(Icons.Filled.Home, contentDescription = "Home")
                    }
                },
                title = {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("Api Keys", textAlign = TextAlign.Center)
                    }
                },
                actions = {
                    IconButton(onClick = {
                            viewModel.saveApiKeys("","")
                            publicKey =""
                            privateKey=""

                    }) {
                        Icon(Icons.Filled.Delete, contentDescription = "Localized description")

                    }


                }
            )
        }

    )
 { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {



            OutlinedTextField(
                value = publicKey,
                onValueChange = { newValue ->
                    publicKey = newValue
                },
                label = { Text("Public Key") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))




            OutlinedTextField(
                value = privateKey,
                onValueChange = { newValue ->
                    privateKey = newValue
                },
                label = { Text("Private Key") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(6.dp))




            Button(
                onClick = {


                    viewModel.saveApiKeys(publicKey, privateKey)
                    viewModel.ifTheKeysChanged(publicKey, privateKey)

                      navController.navigate("heroesScreen")


                },
                modifier = Modifier.fillMaxWidth(),
                enabled = publicKey.isNotEmpty() && privateKey.isNotEmpty() // Disable the button if publicKey or privateKey is empty
            ) {
                Text("Save")
            }


            viewModel.ifTheKeysChanged(publicKey, privateKey)
        }
    }
}

