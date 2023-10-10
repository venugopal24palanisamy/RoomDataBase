package com.example.roomdatabase.viewModel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdatabase.roomDataBase.data_base.UserDataBase
import com.example.roomdatabase.roomDataBase.entity.UserDetails
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(context: Context) : ViewModel() {

    private val appDataBase = UserDataBase.getDatabase(context)

    private val userDao = appDataBase.userDao()

    var username by mutableStateOf("")

    var selectedBloodGroup by mutableStateOf("A+")

    var filterBloodGroup by mutableStateOf("")

    var searchName by mutableStateOf("")

    var updateName by mutableStateOf("")

    var updateBloodGroup by mutableStateOf("")

    var userId by mutableIntStateOf(0)

    var expanded by mutableStateOf(false)

    var isFilterExpanded by mutableStateOf(false)

    var updatedName = MutableLiveData<String>()

    var updatedBloodGroup = MutableLiveData<String>()

    var updatedUserId = MutableLiveData<Int>()

    var filteredListData = MutableLiveData<List<UserDetails>>()
    val filteredLiveData: LiveData<List<UserDetails>> get() = filteredListData
    val updatedUserIdLiveData: LiveData<Int> get() = updatedUserId
    val updatedNameLiveData: LiveData<String> get() = updatedName
    val updatedBloodGroupLiveData: LiveData<String> get() = updatedBloodGroup
    internal fun addUser() {
        viewModelScope.launch {
            try {
                userDao.insertUserDetails(
                    UserDetails(null, username, selectedBloodGroup)
                )
            } catch (ex: Exception) {
                println("cancelled")
            }
        }
    }

    internal fun getUserDetails() {
        viewModelScope.launch {
            try {
                filteredListData.value = userDao.getUserDetails()
                userDao.getUserDetailsByBloodGroup().forEach {
                    Log.d("getUserDetails", "User Name : ${it.name} Age : ${it.bloodGroup}")
                }
            } catch (e: java.lang.Exception) {
                println("cancelled")
            }
        }

    }

    fun setData(name: String, bloodGroup: String, userId: Int) {
        updatedName.value = name
        updatedBloodGroup.value = bloodGroup
        updatedUserId.value = userId
        Log.d("setData", "${updatedName.value} ${updatedBloodGroup.value} ${updatedUserId.value}")
    }

    fun deleteUserByName() {
        viewModelScope.launch {
            try {
                userDao.deleteUserByName(searchName)
                getUserDetails()
            } catch (e: Exception) {
                println("cancelled")
            }
        }
    }

    fun updateUserName(name: String) {
        updatedName.value = name
    }

    fun updateUserById(
        updateName: String,
        selectedBloodGroup: String?,
        selectedUserId: Int?
    ) {

        GlobalScope.launch {
            try {
                userDao.update(
                    UserDetails(
                        selectedUserId,
                        updateName,
                        selectedBloodGroup
                    )
                )
                //Log.d("updateUserById", "${updatedUserIdLiveData.value}")
                getUserDetails()
            } catch (e: Exception) {
                Log.d("updateUserById", e.localizedMessage.toString())
            }
        }


    }

}