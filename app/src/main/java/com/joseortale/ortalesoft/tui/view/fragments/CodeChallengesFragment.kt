package com.joseortale.ortalesoft.tui.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import com.joseortale.ortalesoft.tui.viewModel.CodeChallengesViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.joseortale.ortalesoft.tui.model.CodeChallenge
import com.joseortale.ortalesoft.tui.view.activities.MainActivity
import kotlinx.coroutines.DelicateCoroutinesApi

class CodeChallengesFragment : Fragment() {
    private val TAG = "CodeChallengeFragment"

    private var codeChallengesViewModel: CodeChallengesViewModel? = null
    private var codeChallengeStateList by mutableStateOf(mutableStateListOf<CodeChallenge>())

    @DelicateCoroutinesApi
    @ExperimentalFoundationApi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = ComposeView(requireContext())

        view.apply {
            setContent {
                AppLayout()
            }
        }

        loadCodeChallenges()

        return view
    }

    @ExperimentalFoundationApi
    @Preview
    @Composable
    fun AppLayout() {
        var selectedItem by remember{mutableStateOf(CodeChallenge())}
        val context = LocalContext.current as MainActivity
        LazyColumn(
                modifier = Modifier
                        .fillMaxSize(),
                content = {

                    items(codeChallengeStateList) {
                        Text(it.name,
                                color = Color.White,
                                fontSize = 18.sp,
                                modifier = Modifier
                                        .border(
                                                color = Color.White,
                                                shape = RoundedCornerShape(5.dp),
                                                width = 1.dp
                                        )
                                        .fillMaxWidth()
                                        .padding(10.dp)
                                        .selectable(
                                            selected = it == selectedItem,
                                            onClick = {
                                                if (selectedItem != it) {
                                                    selectedItem = it

                                                    context.setFragment(CodeChallengeDetailsFragment.newInstance(selectedItem))
                                                } else {
                                                    selectedItem = CodeChallenge()
                                                }
                                            }
                                        )
                                )
                    }
                }
        )
    }

    private fun loadCodeChallenges() {
        codeChallengesViewModel = ViewModelProvider(this).get(CodeChallengesViewModel::class.java)
        val codeChallengesVariable = codeChallengesViewModel!!.allCodeChallenges
        codeChallengesVariable.observe(viewLifecycleOwner) { codeChallenges: List<CodeChallenge> ->
            run {
                codeChallengeStateList = codeChallenges.toMutableStateList()
            }
        }
    }

}