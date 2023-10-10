package com.example.roomdatabase

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomdatabase.ui.theme.RoomDataBaseTheme
import com.example.roomdatabase.viewModel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomDataBaseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    UpdateValueView()
                    //Greeting()
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
    val mainViewModel = MainViewModel(context)
    val filteredDataList by mainViewModel.filteredLiveData.observeAsState()
    val updatedName by mainViewModel.updatedBloodGroupLiveData.observeAsState()
    val updatedBloodGroup by mainViewModel.filteredLiveData.observeAsState()
    var updateName by remember { mutableStateOf("") }
    var updateBloodGroup by remember { mutableStateOf("") }
    var userId by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .padding(35.dp)
    ) {
        /*OutlinedTextField(value = mainViewModel.username,
            onValueChange = { mainViewModel.username = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Name") })
*/
        //Spacer(modifier = Modifier.height(15.dp))

        /*Text(
            text = "Select Blood Group",
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp, textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))
*/
        /*Card(
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
                    text = mainViewModel.selectedBloodGroup,
                    modifier = Modifier
                        .weight(1F)
                        .clickable { mainViewModel.expanded = true },
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                Icon(
                    painter = painterResource(id = R.drawable.baseline_drop_down),
                    contentDescription = "bloodGroup"
                )
            }
        }*/

        //Spacer(modifier = Modifier.height(25.dp))

        /*if (mainViewModel.expanded) BloodGroupDropDown(onBloodGroupSelected = {
            mainViewModel.selectedBloodGroup = it
            mainViewModel.expanded = false
        })*/

        /*Button(
            onClick = { mainViewModel.addUser() }, modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Add User",
            )
        }*/

        //Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = { mainViewModel.getUserDetails() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Get User Details",
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        filteredDataList?.let {
            LazyColumn {
                items(filteredDataList!!.size) {
                    Text(
                        modifier = Modifier.clickable {
                            mainViewModel.setData(
                                filteredDataList!![it].name!!,
                                filteredDataList!![it].bloodGroup!!,
                                filteredDataList!![it].userId!!
                            )
                        },
                        text = "User Name: ${filteredDataList!![it].name} Blood Group: ${filteredDataList!![it].bloodGroup} "
                    )
                }
            }
        } ?: Text(
            text = "No Data Available",
            textAlign = TextAlign.Center,
            fontSize = 16.sp, modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(5.dp))

        /*OutlinedTextField(value = searchName,
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
        }*/


        Text(
            text = "Update User Details by Blood Group",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(value = updateName,
            onValueChange = {
                updateName = it
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Change User Name") })

        Spacer(modifier = Modifier.height(5.dp))

        /*Card(
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
*/
        // Spacer(modifier = Modifier.height(25.dp))

        /*if (isFilterExpanded) BloodGroupDropDown(onBloodGroupSelected = {
            filterBloodGroup = it
            isFilterExpanded = false
        })*/

        Button(
            onClick = {
                //mainViewModel.updateUserName(updateName)
                //mainViewModel.updateUserById(updateName, selectedBloodGroup, selectedUserId)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Update User",
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition")

@Composable
fun UpdateValueView() {

    val context = LocalContext.current
    val mainViewModel = MainViewModel(context)
    val filteredDataList by mainViewModel.filteredLiveData.observeAsState()

    val selectedName by mainViewModel.updatedNameLiveData.observeAsState()
    val selectedBloodGroup by mainViewModel.updatedBloodGroupLiveData.observeAsState()
    val selectedUserId by mainViewModel.updatedUserIdLiveData.observeAsState()

    var updateName by remember { mutableStateOf("") }
    var updateBloodGroup by remember { mutableStateOf("") }
    var userId by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .padding(35.dp)
    ) {

        Button(
            onClick = { mainViewModel.getUserDetails() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Get User Details",
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        filteredDataList?.let {
            LazyColumn {
                items(filteredDataList!!.size) {
                    Text(
                        modifier = Modifier.clickable {
                            mainViewModel.setData(
                                filteredDataList!![it].name!!,
                                filteredDataList!![it].bloodGroup!!,
                                filteredDataList!![it].userId!!
                            )
                            updateName = filteredDataList!![it].name!!
                        },
                        text = "User Name: ${filteredDataList!![it].name} Blood Group: ${filteredDataList!![it].bloodGroup} "
                    )
                }
            }
        } ?: Text(
            text = "No Data Available",
            textAlign = TextAlign.Center,
            fontSize = 16.sp, modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = stringResource(R.string.update_user_details_by_blood_group),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(5.dp))


        OutlinedTextField(value = updateName,
            onValueChange = {
                updateName = it
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Change User Name") })

        Spacer(modifier = Modifier.height(5.dp))



        Button(
            onClick = {
                //mainViewModel.updateUserName(updateName)
                mainViewModel.updateUserById(updateName,selectedBloodGroup,selectedUserId)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Update User",
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