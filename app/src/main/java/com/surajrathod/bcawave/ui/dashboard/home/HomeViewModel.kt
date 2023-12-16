package com.surajrathod.bcawave.ui.dashboard.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import dagger.hilt.android.lifecycle.HiltViewModel

class HomeViewModel : ViewModel() {

    val db = Firebase.firestore

    val semList = listOf("Sem 1", "Sem 2", "Sem 3", "Sem 4", "Sem 5", "Sem 6")
    val unitList = listOf("Unit 1", "Unit 2", "Unit 3", "Unit 4")
    val subjectmutableMap = mutableMapOf<Int, List<String>>(
        1 to listOf("IPLC", "HTML"),
        2 to listOf("ACP", "DBMS 1", "HTML/JS"),
        3 to listOf("OOCP", "DS_ALGO"),
        4 to listOf("CJ", "DBMS 2", "WPC#"),
        5 to listOf("PYTHON", "ASP.NET"),
        6 to listOf("WEB APP DEV")
    )

    var currentSem = mutableStateOf("Sem 1")
    var currentSubject = mutableStateOf("IPLC")
    var currentUnit = mutableStateOf("Unit 1")

    var subjectList = mutableStateOf(subjectmutableMap[1])

    var myPrograms: MutableState<MutableList<Program>> = mutableStateOf(mutableListOf())
    var isLoading = mutableStateOf(false)


    private fun createSubjectsMutableMap() {
        subjectmutableMap[1] = listOf("IPLC", "HTML")
        subjectmutableMap[2] = listOf("ACP", "DBMS 1", "HTML/JS")
        subjectmutableMap[3] = listOf("OOCP", "DS_ALGO")
        subjectmutableMap[4] = listOf("CJ", "DBMS 2", "WPC#")
        subjectmutableMap[5] = listOf("PYTHON", "ASP.NET")
        subjectmutableMap[6] = listOf("WEB APP DEV")
    }

    fun loadData() {
        isLoading.value = true
        val programs = db.collection("newPrograms")
        programs.get().addOnSuccessListener { documents ->
            val programs = documents.toObjects(Program::class.java)
            myPrograms.value = programs
            isLoading.value=false
        }.addOnFailureListener {
            Log.d("SURAJFIRE", it.message.toString())
        }
    }

}