package com.example.roomdatabase.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun BloodGroupDropDown(
    onBloodGroupSelected: (String) -> Unit,
 ) {
    val bloodGroups = listOf("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-")
    var expanded by remember { mutableStateOf(false) }
    //Log.d("BloodGroupDropDown", expanded.toString())
    DropdownMenu(
        expanded = true,
        onDismissRequest = { expanded = false },
        modifier = Modifier.fillMaxWidth()
    ) {
        bloodGroups.forEach { bloodGroup ->
            DropdownMenuItem(
                text = { Text(text = bloodGroup, color = Color.Black) },
                onClick = {
                    onBloodGroupSelected(bloodGroup)
                    expanded = false
                })
        }
}}
