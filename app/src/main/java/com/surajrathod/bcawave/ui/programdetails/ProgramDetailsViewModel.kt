package com.surajrathod.bcawave.ui.programdetails

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.surajrathod.bcawave.db.dao.ProgramDao
import com.surajrathod.bcawave.ui.dashboard.home.ProgramItemData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay
import javax.inject.Inject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

@HiltViewModel
class ProgramDetailsViewModel @Inject constructor(private val programDb: ProgramDao) : ViewModel() {

    val db = Firebase.firestore
    var isLoading = mutableStateOf(false)
    var currentProgram = MutableLiveData<ProgramItemData>()


    fun clearCurrentProgram() {
        currentProgram.postValue(ProgramItemData())
    }

    fun getProgramById(programId: String) {
        Log.d("SURAJPROGRAM", "First call")
        try {
            isLoading.value = true
            val programCollection = db.collection("newPrograms")
            programCollection.whereEqualTo("id", programId.toInt()).get().addOnSuccessListener {
                val myProgram = it.documents.firstOrNull()
                if (myProgram != null) {
                    viewModelScope.launch(Dispatchers.IO) {
                        val isFav = programDb.isFav(myProgram["id"].toString().toInt())
                        val p = ProgramItemData(
                            id = myProgram!!["id"].toString().toInt(),
                            title = myProgram["title"].toString(),
                            content = myProgram["content"].toString(),
                            sem = myProgram["sem"].toString(),
                            sub = myProgram["sub"].toString(),
                            unit = myProgram["unit"].toString(),
                            isFav = isFav
                        )
                        currentProgram.postValue(p)
                        Log.d("SURAJPROGRAM", "Program: $p")
                        isLoading.value = false
                    }

                }
            }.addOnFailureListener {
                isLoading.value = false
                Log.d("SURAJPROGRAM", "Failed: ${it.message}")
            }
        } catch (e: Exception) {
            isLoading.value = false
            Log.d("SURAJPROGRAM", "Exception: ${e.message}")
        }
    }

    fun getFirestorePrograms(sem: String, sub: String, unit: String) {
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

                        Log.d("SURAJPROGRAM", "Program: Success ")
                        viewModelScope.launch {
                            kotlinx.coroutines.delay(3000)
                            isLoading.value = false
                        }

                        //myPrograms.value = mList
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

}