package com.example.roomdatabase

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.roomdatabase.roomDataBase.UserDataBase
import com.example.roomdatabase.roomDataBase.entity.UserDetails
import com.example.roomdatabase.ui.theme.RoomDataBaseTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomDataBaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val database = UserDataBase.getDatabase(context)
    val myDao = database.userDao()

    var ins by remember { mutableStateOf(false) }
    if (ins) {
    }

    val scope = rememberCoroutineScope()
    fun addUser() {
        scope.launch {
            try {
                myDao.insertUserDetails(
                    UserDetails(null, "Abc", 1)
                )
            } catch (ex: Exception) {
                println("cancelled")
            }
        }
    }

    fun getUserDetails() {
        scope.launch {

            try {
                myDao.getUserDetails().forEach {
                    Log.d("getUserDetails", "User Name : ${it.name} Age : ${it.age}")
                }
            } catch (e: java.lang.Exception) {
                println("cancelled")
            }
        }

    }
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(35.dp)
    ) {
        Button(
            onClick = { addUser() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Add User",
            )
        }


        Button(
            onClick = { getUserDetails() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Get User Details",
            )
        }
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RoomDataBaseTheme {
        Greeting("Android")
    }
}