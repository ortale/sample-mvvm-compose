package com.joseortale.ortalesoft.tui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.joseortale.ortalesoft.tui.viewModel.CodeChallengesViewModel

class CodeChallengesFragment : Fragment() {
    private val codeChallengesViewModel: CodeChallengesViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = ComposeView(requireContext())

        view.apply {
            setContent {

            }
        }

        return view
    }
}