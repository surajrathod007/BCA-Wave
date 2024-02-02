package com.surajrathod.bcawave.ui.dashboard.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.surajrathod.bcawave.db.dao.ProgramDao
import com.surajrathod.bcawave.db.models.ProgramEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val programDb: ProgramDao) : ViewModel() {

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

    var myPrograms: MutableState<MutableList<ProgramItemData>> = mutableStateOf(mutableListOf())
    var isLoading = mutableStateOf(false)

    val localPrograms = programDb.getAllProgram()


    private fun createSubjectsMutableMap() {
        subjectmutableMap[1] = listOf("IPLC", "HTML")
        subjectmutableMap[2] = listOf("ACP", "DBMS 1", "HTML/JS")
        subjectmutableMap[3] = listOf("OOCP", "DS_ALGO")
        subjectmutableMap[4] = listOf("CJ", "DBMS 2", "WPC#")
        subjectmutableMap[5] = listOf("PYTHON", "ASP.NET")
        subjectmutableMap[6] = listOf("WEB APP DEV")
    }


    fun updateData() {
        getFirestorePrograms(currentSem.value, currentSubject.value, currentUnit.value)
    }

    private fun getFirestorePrograms(sem: String, sub: String, unit: String) {
        try {
            isLoading.value = true
            val programCol = db.collection("newPrograms")
            programCol.whereEqualTo("sem", sem).whereEqualTo("sub", sub)
                .whereEqualTo("unit", unit)
                .get().addOnSuccessListener {
                    viewModelScope.launch(Dispatchers.IO) {
                        val mList = mutableListOf<ProgramItemData>()
                        for (j in it.documents) {
                            val isFav = programDb.isFav(j!!["id"].toString().toInt())
                            val p = ProgramItemData(
                                id = j!!["id"].toString().toInt(),
                                title = j["title"].toString(),
                                content = j["content"].toString(),
                                sem = j["sem"].toString(),
                                sub = j["sub"].toString(),
                                unit = j["unit"].toString(),
                                isFav = isFav
                            )
                            mList.add(p)
                        }

                        isLoading.value = false
                        myPrograms.value = mList
                    }

                }.addOnFailureListener {
//                    msg.postValue("Failure"+it.message.toString())
//                    clearPrograms()
//                    refresh()
//                    _loading.postValue(false)
                }
        } catch (e: Exception) {
            //msg.postValue(e.message)
        }

    }

    fun addToFavourite(program: ProgramItemData) {
        viewModelScope.launch(Dispatchers.IO) {
            programDb.insert(program.toProgramEntity())
        }
    }

    fun removeFromFav(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            programDb.removeFav(id)
        }
    }

}