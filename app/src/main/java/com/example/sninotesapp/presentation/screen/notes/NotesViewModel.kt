package com.example.sninotesapp.presentation.screen.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

}