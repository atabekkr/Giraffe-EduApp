package com.imax.giraffe.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.imax.giraffe.databinding.FragmentListeningTestBinding
import com.imax.giraffe.presentation.fragment.ListeningTestFragment

@Composable
fun ListeningTestScreen() {
    AndroidViewBinding(
        FragmentListeningTestBinding::inflate,
        Modifier.fillMaxSize()
    ) {
        val listeningTestFragment = this.myFragment.getFragment<ListeningTestFragment>()
    }
}