package com.joseortale.ortalesoft.tui.view.fragments

import android.os.Bundle
import android.provider.Settings.Global.putInt
import android.provider.Settings.Global.putString
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.joseortale.ortalesoft.tui.model.CodeChallenge
import kotlinx.coroutines.DelicateCoroutinesApi

class CodeChallengeDetailsFragment : Fragment() {
    private var codeChallenge: CodeChallenge? = null
    private val TAG = "CodeChallengeDT"

    companion object {
        private val CodeChallengeKey = "CODE_CHALLENGE_KEY"

        fun newInstance(codeChallenge: CodeChallenge): CodeChallengeDetailsFragment {
            val args = Bundle()
            args.putSerializable(CodeChallengeKey, codeChallenge)

            val fragment = CodeChallengeDetailsFragment();
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        codeChallenge = requireArguments().getSerializable(CodeChallengeKey) as CodeChallenge
    }

    @DelicateCoroutinesApi
    @ExperimentalFoundationApi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = ComposeView(requireContext())

        view.apply {
            setContent {
                AppLayout()
            }
        }

        return view
    }

    @ExperimentalFoundationApi
    @Preview
    @Composable
    fun AppLayout() {
        //Log.v(TAG, "codeChallenge: " + codeChallenge!!.name)

        val name = codeChallenge!!.name
        val description = codeChallenge!!.description

        Column(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .verticalScroll(rememberScrollState())) {
            Text(text = "Challenge Name:", color = Color.White,
                    fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = name, color = Color.White,
                    fontSize = 14.sp)
            Text(text = "Challenge Description:", color = Color.White,
                    fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = description, color = Color.White,
                    fontSize = 14.sp)
        }
    }
}