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
class MainActivity : ComponentActivity() {
    val REQUEST_CODE = 1337
    val REDIRECT_URI = "http://localhost:8080"
    val CLIENT_ID = "338e7ddbbb344761b87776da268adf5f"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwapifyTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                }
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
        Text(text = "Connect to Spotify",
            style = MaterialTheme.typography.displayMedium)

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConnectButton() {
    SwapifyTheme {
        ConnectButton {}
    }
}
