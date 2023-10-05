package com.example.roomdatabase

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomdatabase.components.BloodGroupDropDown
import com.example.roomdatabase.roomDataBase.data_base.UserDataBase
import com.example.roomdatabase.roomDataBase.entity.UserDetails
import com.example.roomdatabase.ui.theme.RoomDataBaseTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomDataBaseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Greeting() {
    val context = LocalContext.current

    val database = UserDataBase.getDatabase(context)

    val myDao = database.userDao()

    val scope = rememberCoroutineScope()

    var username by remember {
        mutableStateOf("")
    }

    var selectedBloodGroup by remember { mutableStateOf("A+") }

    var filterBloodGroup by remember { mutableStateOf("") }

    var searchName by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }

    var isFilterExpanded by remember { mutableStateOf(false) }

    var filteredList by remember {
        mutableStateOf<List<UserDetails>?>(null)
    }

    fun addUser() {
        scope.launch {
            try {
                myDao.insertUserDetails(
                    UserDetails(null, username, selectedBloodGroup)
                )
            } catch (ex: Exception) {
                println("cancelled")
            }
        }
    }

    fun getUserDetails() {
        scope.launch {
            try {
                filteredList = myDao.getUserDetailsByBloodGroup(filterBloodGroup)
                myDao.getUserDetailsByBloodGroup(filterBloodGroup).forEach {
                    Log.d("getUserDetails", "User Name : ${it.name} Age : ${it.bloodGroup}")
                }
            } catch (e: java.lang.Exception) {
                println("cancelled")
            }
        }

    }

    fun deleteUserByName() {
        scope.launch {

            try {
                myDao.deleteUserByName(searchName)
                getUserDetails()
            } catch (e: Exception) {
                println("cancelled")
            }

        }
    }

    Column(
        modifier = Modifier
            .padding(35.dp)
    ) {

        OutlinedTextField(value = username,
            onValueChange = { username = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Name") })

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Select Blood Group",
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp, textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))

        Card(
            shape = RoundedCornerShape(15.dp),
            border = BorderStroke(
                1.dp, color = Color.Black,
            ),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = selectedBloodGroup,
                    modifier = Modifier
                        .weight(1F)
                        .clickable { expanded = true },
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                Icon(
                    painter = painterResource(id = R.drawable.baseline_drop_down),
                    contentDescription = "bloodGroup"
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        if (expanded) BloodGroupDropDown(onBloodGroupSelected = {
            selectedBloodGroup = it
            expanded = false
        })

        Button(
            onClick = { addUser() }, modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Add User",
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Select Blood Group to get user details",
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp, textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(5.dp))

        Card(
            shape = RoundedCornerShape(15.dp),
            border = BorderStroke(
                1.dp, color = Color.Black,
            ),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = filterBloodGroup,
                    modifier = Modifier
                        .weight(1F)
                        .clickable { isFilterExpanded = true },
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                Icon(
                    painter = painterResource(id = R.drawable.baseline_drop_down),
                    contentDescription = "bloodGroup"
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        if (isFilterExpanded) BloodGroupDropDown(onBloodGroupSelected = {
            filterBloodGroup = it
            isFilterExpanded = false
        })

        Spacer(modifier = Modifier.height(5.dp))

        Button(
            onClick = { getUserDetails() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Get User Details",
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        filteredList?.let {
            LazyColumn {
                items(filteredList!!.size) {
                    Text(
                        text = "User Name: ${filteredList!![it].name} Blood Group: ${filteredList!![it].bloodGroup} "
                    )
                }
            }
        } ?: Text(
            text = "No Data Available",
            textAlign = TextAlign.Center,
            fontSize = 16.sp, modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Delete User Details by Name",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()

        )

        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(value = searchName,
            onValueChange = { searchName = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Search User") })

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = { deleteUserByName() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Delete User",
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RoomDataBaseTheme {
        Greeting()
    }
}