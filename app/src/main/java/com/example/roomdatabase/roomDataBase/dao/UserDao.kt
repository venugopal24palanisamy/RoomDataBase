package com.example.roomdatabase.roomDataBase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.roomdatabase.roomDataBase.entity.UserDetails

@Dao
interface UserDao {
    @Insert
    suspend fun insertUserDetails(userDetails: UserDetails)

    @Query("SELECT * FROM user_details")
    suspend fun getUserDetails(): List<UserDetails>

    @Query("SELECT * FROM USER_DETAILS")
    suspend fun getUserDetailsByBloodGroup(): List<UserDetails>

    @Query("DELETE FROM USER_DETAILS WHERE name = :name")
    suspend fun deleteUserByName(name:String)


    @Update
    fun update(userDetails: UserDetails)


}