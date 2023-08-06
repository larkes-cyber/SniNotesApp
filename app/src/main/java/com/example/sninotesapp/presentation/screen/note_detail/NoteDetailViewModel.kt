package com.example.sninotesapp.presentation.screen.note_detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sninotesapp.domain.model.Note
import com.example.sninotesapp.domain.repository.NoteRepository
import com.example.sninotesapp.presentation.screen.login.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val noteRepository: NoteRepository
):ViewModel() {
    private val _uiState = MutableStateFlow(NoteDetailUiState())
    val uiState:StateFlow<NoteDetailUiState> = _uiState

    init {
        viewModelScope.launch {
            if(savedStateHandle.get<String>("id") != "null"){
                val note = noteRepository.observeNoteById(savedStateHandle.get<String>("id")!!.toInt())
                _uiState.value = NoteDetailUiState(
                    title = note.title,
                    text = note.text,
                    color = note.color
                )
            }else{
                _uiState.value = NoteDetailUiState(
                    title = "Title",
                    text = "Write your note here...",
                    color = Note.generateRandom()
                )
            }
        }
    }

    fun onTitleFieldChange(title:String){
        _uiState.value = uiState.value.copy(title = title)
    }

    fun onTextFieldChange(text:String){
        _uiState.value = uiState.value.copy(text = text)
    }

    fun databaseSync(){
        viewModelScope.launch {
            noteRepository.insertNote(
                Note(
                    id = if(savedStateHandle.get<String>("id") == "null") null else savedStateHandle.get<String>("id")!!,
                    title = _uiState.value.title,
                    text = _uiState.value.text,
                    color = _uiState.value.color,
                    online_sync = false
                )
            )
            _uiState.value = uiState.value.copy(noteHasBeenEdited = true)
        }
    }

}