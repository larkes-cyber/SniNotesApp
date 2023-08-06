package com.example.sninotesapp.presentation.screen.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sninotesapp.domain.model.Note
import com.example.sninotesapp.domain.repository.NoteRepository
import com.example.sninotesapp.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val userRepository: UserRepository
):ViewModel() {

    private val _notesUiState = MutableStateFlow(NotesUiState())
    val notesUiState:StateFlow<NotesUiState> = _notesUiState

    private val _userUiState = MutableStateFlow(UserUiState())
    val userUiState:StateFlow<UserUiState> = _userUiState


    init {
        observeNotes()
        observeUserData()
    }

    private fun observeNotes(){
        viewModelScope.launch {
            _notesUiState.value = NotesUiState(isLoading = true)
            _notesUiState.value = NotesUiState(notesList = noteRepository.observeNotes(), isLoading = false)
        }
    }

    private fun observeUserData(){
        val user = userRepository.getUserData()
        _userUiState.value = userUiState.value.copy(user = user)
    }

    fun switchSelectingMode(bool:Boolean){
        _notesUiState.value = notesUiState.value.copy(selectingMode = bool)
    }

    fun selectNote(note:Note){
        val currentSelectedNotes = notesUiState.value.selectedNotes.toMutableList()
        currentSelectedNotes.add(note)
        _notesUiState.value = notesUiState.value.copy(selectedNotes = currentSelectedNotes)
    }
    fun unselectNote(note:Note){
        val currentSelectedNotes = notesUiState.value.selectedNotes.toMutableList()
        currentSelectedNotes.remove(note)
        _notesUiState.value = notesUiState.value.copy(selectedNotes = currentSelectedNotes, selectingMode = currentSelectedNotes.size != 0)
    }

    fun deleteNotes(){
        viewModelScope.launch {
            notesUiState.value.selectedNotes.forEach { note ->
                noteRepository.deleteNote(note)
            }
            _notesUiState.value = NotesUiState()
            observeNotes()
        }
    }

    fun quitApp(){
        userRepository.deleteUser()
        _userUiState.value = userUiState.value.copy(hasBeenQuit = true)
    }

}