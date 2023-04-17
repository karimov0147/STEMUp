package com.hackerstreet.stemup.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.hackerstreet.stemup.R
import com.hackerstreet.stemup.databinding.ScreenLessonCompletedBinding
import com.hackerstreet.stemup.ui.presenters.CompleteViewModel
import com.hackerstreet.stemup.utils.RecyclerUtils

class CompletedScreen : Fragment(R.layout.screen_lesson_completed) {
    private val binding by viewBinding(ScreenLessonCompletedBinding::bind)
    private val viewModel by viewModels<CompleteViewModel>()
    var a = arrayListOf(
        12,
        23,
        34,
        45,
        56,
        67,
        78,
        89,
        100,
        111,
        122,
        133,
        144,
        155,
        166,
        177,
        188,
        199,
        210,
        221,
        232,
        243,
        254,
        265,
        276,
        287,
        298,
        309,
        320,
        331,
        342
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvPoints.text = "+" + (RecyclerUtils.userProgress * 2).toString();
            tvAccuracy.text = (RecyclerUtils.userProgress * 20).toString() + "%"
            RecyclerUtils.userProgress = 0
            tvContinue.setOnClickListener {
                findNavController().navigateUp()
//                Toast.makeText(requireContext(), RecyclerUtils.lessonId.toString(), Toast.LENGTH_SHORT).show()
                if (a.contains(RecyclerUtils.lessonId + 1)) {
                    viewModel.updateAnswer(RecyclerUtils.lessonId * 5 + 10)
                } else {
                    viewModel.updateAnswer(RecyclerUtils.lessonId * 5 + 5)
                }
            }

        }
    }
}