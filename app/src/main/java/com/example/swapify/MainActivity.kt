package com.example.swapify

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.swapify.ui.theme.SwapifyTheme

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;

import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse

class MainActivity : ComponentActivity() {
    val REQUEST_CODE = 1337
    val REDIRECT_URI = "http://localhost:8080"
    val CLIENT_ID = "338e7ddbbb344761b87776da268adf5f"

    val connectionParams = ConnectionParams.Builder(CLIENT_ID)
        .setRedirectUri(REDIRECT_URI)
        .showAuthView(true)
        .setRequiredFeatures(arrayListOf("app-remote-control"))
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwapifyTheme {
                var spotifyAppRemote: SpotifyAppRemote

                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ConnectButton {
//                        initiateSpotifyAuthorization()

                        SpotifyAppRemote.connect(this, connectionParams, object : Connector.ConnectionListener {
                            override fun onConnected(appRemote: SpotifyAppRemote) {
                                spotifyAppRemote = appRemote
                                println("MainActivity" + "Connected! Yay!")
                                // Now you can start interacting with App Remote
                                connected()
                            }

                            override fun onFailure(throwable: Throwable) {
                                println("MainActivity error ${throwable.message} $throwable")
                                // Something went wrong when attempting to connect! Handle errors here
                            }
                        })
                    }
                }
            }
        }
    }

    private fun initiateSpotifyAuthorization() {
        val builder = AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI)
        builder.setScopes(arrayOf("streaming"))
        val request = builder.build()

        AuthorizationClient.openLoginActivity(this, REQUEST_CODE, request)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            val response = AuthorizationClient.getResponse(resultCode, intent)
            when (response.type) {
                AuthorizationResponse.Type.TOKEN -> {}
                AuthorizationResponse.Type.ERROR -> {}
                else -> {}
            }
        }
    }


    override fun onStart() {
        super.onStart()
        // We will start writing our code here.
    }

    private fun connected() {
        // Then we will write some more code here.
    }

    override fun onStop() {
        super.onStop()
        // Aaand we will finish off here.
    }
}

@Composable
fun ConnectButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "Connect to Spotify")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConnectButton() {
    SwapifyTheme {
        ConnectButton {}
    }
}
