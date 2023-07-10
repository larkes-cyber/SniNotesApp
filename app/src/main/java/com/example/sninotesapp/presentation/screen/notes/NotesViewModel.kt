package com.example.sninotesapp.presentation.screen.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sninotesapp.domain.model.Note
import com.example.sninotesapp.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteRepository: NoteRepository
):ViewModel() {

    private val _uiState = MutableStateFlow(NotesUiState())
    val uiState:StateFlow<NotesUiState> = _uiState

    init {
        observeNotes()
    }

    private fun observeNotes(){
        viewModelScope.launch {
            _uiState.value = NotesUiState(isLoading = true)
            _uiState.value = NotesUiState(notesList = noteRepository.observeNotes(), isLoading = false)
        }

    }

    fun switchSelectingMode(bool:Boolean){
        _uiState.value = uiState.value.copy(selectingMode = bool)
    }

    fun selectNote(note:Note){
        val currentSelectedNotes = uiState.value.selectedNotes.toMutableList()
        currentSelectedNotes.add(note)
        _uiState.value = uiState.value.copy(selectedNotes = currentSelectedNotes)
    }
    fun unselectNote(note:Note){
        val currentSelectedNotes = uiState.value.selectedNotes.toMutableList()
        currentSelectedNotes.remove(note)
        _uiState.value = uiState.value.copy(selectedNotes = currentSelectedNotes, selectingMode = currentSelectedNotes.size != 0)
    }

    fun deleteNotes(){
        viewModelScope.launch {
            uiState.value.selectedNotes.forEach {note ->
                noteRepository.deleteNote(note)
            }
            _uiState.value = NotesUiState()
            observeNotes()
        }
    }

}