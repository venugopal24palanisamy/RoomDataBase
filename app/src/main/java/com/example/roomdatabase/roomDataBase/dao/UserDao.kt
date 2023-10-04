package com.example.roomdatabase.roomDataBase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roomdatabase.roomDataBase.entity.UserDetails

@Dao
interface UserDao {
    @Insert
    suspend fun insertUserDetails(userDetails: UserDetails)

    @Query("SELECT * FROM user_details")
    suspend fun getUserDetails(): List<UserDetails>

    @Query("SELECT * FROM USER_DETAILS WHERE bloodGroup = :bloodGroup")
    suspend fun getUserDetailsByBloodGroup(bloodGroup: String): List<UserDetails>
}