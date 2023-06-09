package com.hackerstreet.stemup.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.hackerstreet.stemup.R
import com.hackerstreet.stemup.data.models.LessonModel
import com.hackerstreet.stemup.data.storage.LocaleStorage
import com.hackerstreet.stemup.databinding.ScreenLessonBinding
import com.hackerstreet.stemup.ui.presenters.LessonViewModel
import com.hackerstreet.stemup.utils.RecyclerUtils

class LessonScreen : Fragment(R.layout.screen_lesson) {
    private val binding by viewBinding(ScreenLessonBinding::bind)
    private val viewModel by viewModels<LessonViewModel>()
    private val data = ArrayList<LessonModel>()
    var counter = 0

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.lessonListLivedata.observe(viewLifecycleOwner, lessonListObserver)
        viewModel.getLesson(RecyclerUtils.lessonId * 5 - 4, RecyclerUtils.lessonId * 5)
        binding.tvPoints.text = LocaleStorage.getPoints().toString()
        binding.tvContinue.setOnClickListener { generateLesson() }
        binding.sbProgress.setOnTouchListener { _, _ -> true }
        binding.ivClose.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private val lessonListObserver = Observer<List<LessonModel>> {
        data.clear()
        data.addAll(it)
        binding.apply {
            tvTitle.text = it[counter].termin
            tvDescription.text = it[counter].description
            ++counter
            sbProgress.progress = counter
        }
    }

    private fun generateLesson() {
        if (counter > 4) {
            RecyclerUtils.data.apply {
                clear()
                addAll(data)
            }
            findNavController().navigate(R.id.action_lessonScreen_to_lessonQuizScreen)
        } else {
            binding.apply {
                tvTitle.text = data[counter].termin
                tvDescription.text = data[counter].description
                ++counter
                sbProgress.progress = counter
            }
        }
    }

}